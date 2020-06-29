package br.com.bluesoft.desafio.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoItem;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.model.bo.Cotacao;
import br.com.bluesoft.desafio.model.bo.NovoPedido;
import br.com.bluesoft.desafio.repository.FornecedorRepository;
import br.com.bluesoft.desafio.repository.PedidoItemRepository;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.repository.ProdutoRepository;
import br.com.bluesoft.desafio.service.CotacaoService;
import br.com.bluesoft.desafio.service.CotacaoServiceException;
import br.com.bluesoft.desafio.service.CriadorPedidoService;
import br.com.bluesoft.desafio.service.PedidoServiceException;
import br.com.bluesoft.desafio.util.Lists;
import br.com.bluesoft.desafio.util.Maps;

@Service
public class CriadorPedidoServiceImpl implements CriadorPedidoService {

    private PedidoRepository pedidoRepository;
    private CotacaoService cotacaoService;
    private FornecedorRepository fornecedorRepository;
    private PedidoItemRepository pedidoItemRepository;
    private ProdutoRepository produtoRepository;

    public CriadorPedidoServiceImpl() {

    }

    @Autowired
    public CriadorPedidoServiceImpl(PedidoRepository pedidoRepository, FornecedorRepository fornecedorRepository,
	    ProdutoRepository produtoRepository, PedidoItemRepository pedidoItemRepository,
	    CotacaoService cotacaoService) {
	this.pedidoRepository = pedidoRepository;
	this.fornecedorRepository = fornecedorRepository;
	this.produtoRepository = produtoRepository;
	this.pedidoItemRepository = pedidoItemRepository;
	this.cotacaoService = cotacaoService;
    }

    @Override
    public List<Pedido> criarNovosPedidos(List<NovoPedido> novosPedidos) throws PedidoServiceException {
	try {
	    List<NovoPedido> novosPedidosValidados = validarNovosPedidos(novosPedidos);
	    Map<Fornecedor, List<PedidoItem>> pedidosPorFornecedor = geraPedidosPorFornecedor(novosPedidosValidados);
	    List<Pedido> pedidos = Lists.newArrayList();
	    for (Fornecedor fornecedor : pedidosPorFornecedor.keySet()) {
		Pedido pedido = pedidoRepository.criarNovo(Pedido.Builder.novoBuilder()
			.comFornecedor(fornecedorRepository.criarUmNovoFornecedorSeNaoExistir(fornecedor)).constroi());
		for (PedidoItem pedidoItem : pedidosPorFornecedor.get(fornecedor)) {
		    pedido.addItem(pedidoItemRepository.criarNovo(PedidoItem.Builder.novoBuilder()
			    .comProduto(pedidoItem.getProduto()).comQuantidade(pedidoItem.getQuantidade())
			    .comValor(pedidoItem.getValor()).comPedido(pedido).constroi()));
		}
		pedidos.add(pedido);
	    }
	    return pedidos;
	} catch (Exception e) {
	    throw new PedidoServiceException(e.getMessage());
	}
    }

    private List<NovoPedido> validarNovosPedidos(List<NovoPedido> novosPedidos) throws PedidoServiceException {
	if (Lists.allMatch(novosPedidos, novoPedido -> novoPedido.getQuantidade() == 0)) {
	    throw new PedidoServiceException(
		    "Ao menos um produto deve ser solicitado para a criação de um novo pedido");
	}
	return novosPedidos.stream().filter(novoPedido -> novoPedido.getQuantidade() > 0)
		.map(novoPedido -> novoPedido.setProduto(buscarProduto(novoPedido.getProduto())))
		.collect(Collectors.toList());
    }

    private Produto buscarProduto(Produto produto) {
	try {
	    return produtoRepository.buscarProdutoPorGtin(produto.getGtin())
		    .orElseThrow(() -> new PedidoServiceException("Não foi possível encontrar produto com gtin"));
	} catch (PedidoServiceException e) {
	    throw new RuntimeException(e);
	}
    }

    private Map<Fornecedor, List<PedidoItem>> geraPedidosPorFornecedor(List<NovoPedido> novosPedidos)
	    throws CotacaoServiceException {

	// Como a lista de itens pode ser muito longa o ideal é executá-la em paralelo
	// e concorrentemente. A premissa de execução nesse caso é positivista e
	// assume-se que não atender a quantidades mínimas sejam exceção e não a regra.
	// No melhor caso o tempo total de execucao é O(n)/numThreads e no pior caso a
	// execucao é O((n^3) + 9000)/numThreads considerando 3 retries com delay de 3
	// segs, sendo O o tempo médio de request para cotacao em ms e n número de itens
	// a serem cotados
	return novosPedidos.parallelStream().map(novoPedido -> calcularMelhorFornecedor(novoPedido))
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (old, latest) -> {
		    old.addAll(latest);
		    return old;
		}));
    }

    private Entry<Fornecedor, List<PedidoItem>> calcularMelhorFornecedor(NovoPedido novoPedido) {
	try {
	    Integer quantidade = novoPedido.getQuantidade();
	    Produto produto = novoPedido.getProduto();
	    Cotacao cotacao = cotacaoService.melhorCotacao(produto, quantidade);
	    BigDecimal melhorPreco = cotacao.getMelhorPreco(produto, quantidade);
	    Fornecedor fornecedor = cotacao.getFornecedor();
	    PedidoItem pedidoItem = PedidoItem.Builder.novoBuilder().comProduto(produto).comQuantidade(quantidade)
		    .comValor(melhorPreco).constroi();
	    return Maps.entry(fornecedor, Lists.newArrayList(pedidoItem));
	} catch (CotacaoServiceException e) {
	    throw new RuntimeException(e);
	}
    }

}
