package br.com.bluesoft.desafio.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import br.com.bluesoft.desafio.service.PedidoServiceException;
import br.com.bluesoft.desafio.util.Lists;
import br.com.bluesoft.desafio.util.Maps;

@Component
public class CriadorPedido {

    private CotacaoService cotacaoService;
    private ProdutoRepository produtoRepository;
    private PedidoRepository pedidoRepository;
    private PedidoItemRepository pedidoItemRepository;
    private FornecedorRepository fornecedorRepository;

    public CriadorPedido() {

    }

    @Autowired
    public CriadorPedido(CotacaoService cotacaoService, ProdutoRepository produtoRepository,
	     PedidoRepository pedidoRepository,PedidoItemRepository pedidoItemRepository,
	     FornecedorRepository fornecedorRepository) {
	this.cotacaoService = cotacaoService;
	this.produtoRepository = produtoRepository;
	this.pedidoRepository = pedidoRepository;
	this.pedidoItemRepository = pedidoItemRepository;
	this.fornecedorRepository = fornecedorRepository;
    }

    public List<Pedido> criarNovosPedidos(List<NovoPedido> novosPedidos) throws PedidoServiceException {

	Map<NovoPedido, List<Cotacao>> cotacoesDeProdutos = verificaSeAlgumFornecedorNaoAtendeQuantidadeMinima(
		cotacoesDeProdutos(novosPedidos));

	/*
	Map<Fornecedor,Pedido> pedidosAgrupados = Maps.newHashMap();
	for (Entry<NovoPedido, List<Cotacao>> cotacaoProduto : cotacoesDeProdutos.entrySet()) {
	    List<Cotacao> cotacoes = cotacaoProduto.getValue();
	    NovoPedido solicitacaoCompraItem = cotacaoProduto.getKey();
	    Integer quantidade = solicitacaoCompraItem.getQuantidade();
	    Produto produto = solicitacaoCompraItem.getProduto();
	    Cotacao cotacao = selecionarMelhorCotacao(solicitacaoCompraItem, cotacoes);
	    System.out.println("Melhor cotacao para o produto " + produto.getGtin() + " => " + cotacao.getCnpj() + " = "
		    + menorValor(cotacao, quantidade, produto));
	}
	
	
	for(Entry<Fornecedor,Pedido> entry : pedidosAgrupados.entrySet()) {
	    Fornecedor fornecedor = entry.getKey();
	    Pedido pedido = entry.getValue();
	    pedido.setFornecedor(fornecedor);
	}
	*/
	Pedido pedido = pedidoRepository.save(new Pedido(null, fornecedorRepository.criarUmNovoFornecedorSeNaoExistir(new Fornecedor(null, "00.000.000/0000-01", "razao1")), null));
	pedido.addItem(pedidoItemRepository.save(new PedidoItem(null, BigDecimal.TEN, 10, buscarProduto(new Produto(null, "7894900011517", null)), pedido)));
	pedido.addItem(pedidoItemRepository.save(new PedidoItem(null, BigDecimal.valueOf(15.4), 5, buscarProduto(new Produto(null, "7891910007110", null)), pedido)));
	return Lists.newArrayList(pedido);
    }
    
    private Produto buscarProduto(Produto produto) throws PedidoServiceException {
	return produtoRepository
		.buscarProdutoPorGtin(produto.getGtin()).orElseThrow(() -> new PedidoServiceException(
		"Não foi possível carregar dados do produto " + produto.getGtin()));
    }

    private Cotacao selecionarMelhorCotacao(NovoPedido novoPedido, List<Cotacao> cotacoes)
	    throws PedidoServiceException {
	Integer quantidadeRequisitada = novoPedido.getQuantidade();
	Produto produto = novoPedido.getProduto();
	return cotacoes.stream()
		.sorted((c1, c2) -> Double.compare(menorValor(c1, quantidadeRequisitada, produto),
			menorValor(c2, quantidadeRequisitada, produto)))
		.findFirst().orElseThrow(() -> new PedidoServiceException(
			"Não foi possível calcular a menor cotação para o produto " + produto.getId()));
    }

    private Double menorValor(Cotacao cotacao, Integer quantidadeRequisitada, Produto produto) throws RuntimeException {
	return cotacao.getPrecos().stream()
		.filter(cotacaoPreco -> quantidadeRequisitada >= cotacaoPreco.getQuantidadeMinima())
		.mapToDouble(cotacaoPreco -> cotacaoPreco.getPreco().doubleValue()).min()
		.orElseThrow(() -> new RuntimeException(
			"Não foi possível calcular o menor valor para o produto " + produto.getId()));
    }

    private Map<NovoPedido, List<Cotacao>> cotacoesDeProdutos(List<NovoPedido> novosPedidos)
	    throws PedidoServiceException {
	Map<NovoPedido, List<Cotacao>> cotacoesDeProdutos = novosPedidos.parallelStream()
		.filter(novoPedido -> novoPedido.getQuantidade() > 0)
		.map(novoPedido -> Maps.entry(novoPedido, realizaCotacoesParaProduto(novoPedido.getProduto())))
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	if (Maps.isEmpty(cotacoesDeProdutos)) {
	    throw new PedidoServiceException(
		    "Ao menos um produto deve ser solicitado para a criação de um novo pedido");
	}
	return cotacoesDeProdutos;
    }

    private Map<NovoPedido, List<Cotacao>> verificaSeAlgumFornecedorNaoAtendeQuantidadeMinima(
	    Map<NovoPedido, List<Cotacao>> cotacoesDeProdutos) throws PedidoServiceException {
	for (Entry<NovoPedido, List<Cotacao>> entry : cotacoesDeProdutos.entrySet()) {
	    NovoPedido novoPedido = entry.getKey();
	    Integer quantidadeRequisitada = novoPedido.getQuantidade();
	    Produto produto = novoPedido.getProduto();
	    List<Cotacao> cotacoes = entry.getValue();
	    if (!algumFornecedorAtendeQuantidadeMinima(quantidadeRequisitada, cotacoes)) {
		throw new PedidoServiceException(
			"Nenhum fornecedor encontrado para a quantidade solicitada do produto " + produtoRepository
				.buscarProdutoPorGtin(produto.getGtin()).orElseThrow(() -> new PedidoServiceException(
					"Não foi possível carregar dados do produto " + produto.getGtin())));
	    }
	}
	return cotacoesDeProdutos;
    }

    private boolean algumFornecedorAtendeQuantidadeMinima(Integer quantidadeRequisitada, List<Cotacao> cotacoes) {
	return cotacoes.stream().flatMap(cotacao -> cotacao.getPrecos().stream())
		.map(cotacaoPreco -> cotacaoPreco.getQuantidadeMinima())
		.anyMatch(quantidadeMinima -> quantidadeRequisitada >= quantidadeMinima);
    }

    private List<Cotacao> realizaCotacoesParaProduto(Produto produto) {
	try {
	    return cotacaoService.realizaCotacoesParaProduto(produto);
	} catch (CotacaoServiceException e) {
	    throw new RuntimeException(e);
	}
    }

}