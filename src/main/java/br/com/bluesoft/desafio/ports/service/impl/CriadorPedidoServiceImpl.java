package br.com.bluesoft.desafio.ports.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.ports.domain.Cotacao;
import br.com.bluesoft.desafio.ports.domain.Fornecedor;
import br.com.bluesoft.desafio.ports.domain.Pedido;
import br.com.bluesoft.desafio.ports.domain.PedidoItem;
import br.com.bluesoft.desafio.ports.domain.Produto;
import br.com.bluesoft.desafio.ports.domain.SolicitacaoCompraItem;
import br.com.bluesoft.desafio.ports.domain.decorator.HashableSolicitacaoCompraItem;
import br.com.bluesoft.desafio.ports.service.CotacaoService;
import br.com.bluesoft.desafio.ports.service.CotacaoServiceException;
import br.com.bluesoft.desafio.ports.service.CriadorPedidoException;
import br.com.bluesoft.desafio.ports.service.CriadorPedidoService;
import br.com.bluesoft.desafio.ports.service.ProdutoService;
import br.com.bluesoft.desafio.util.Maps;

@Service
public class CriadorPedidoServiceImpl implements CriadorPedidoService {

    private CotacaoService cotacaoService;
    private ProdutoService produtoService;

    public CriadorPedidoServiceImpl() {

    }

    @Autowired
    public CriadorPedidoServiceImpl(CotacaoService cotacaoService, ProdutoService produtoService) {
	this.cotacaoService = cotacaoService;
	this.produtoService = produtoService;
    }

    @Override
    public List<Pedido> criarNovosPedidos(List<SolicitacaoCompraItem> solicitacaoCompraItems)
	    throws CriadorPedidoException {
	Map<SolicitacaoCompraItem, List<Cotacao>> cotacoesDeProdutos = verificaSeAlgumFornecedorNaoAtendeQuantidadeMinima(
		cotacoesDeProdutos(solicitacaoCompraItems));

	Map<Fornecedor, List<PedidoItem>> pedidosPorFornecedores = Maps.newHashMap();
	for (Entry<SolicitacaoCompraItem, List<Cotacao>> cotacaoProduto : cotacoesDeProdutos.entrySet()) {
	    List<Cotacao> cotacoes = cotacaoProduto.getValue();
	    SolicitacaoCompraItem solicitacaoCompraItem = cotacaoProduto.getKey();
	    Integer quantidade = solicitacaoCompraItem.getQuantidade();
	    Produto produto = solicitacaoCompraItem.getProduto();
	    Cotacao cotacao = selecionarMelhorCotacao(solicitacaoCompraItem, cotacoes);
	    System.out.println("Melhor cotacao para o produto " + produto.getId() + " => " + cotacao.getFornecedor().getCnpj() + " = " + menorValor(cotacao, quantidade, produto));
	}

	throw new CriadorPedidoException("Não foi possivel criar, implementar ainda");
    }

    private Cotacao selecionarMelhorCotacao(SolicitacaoCompraItem solicitacaoCompraItem, List<Cotacao> cotacoes)
	    throws CriadorPedidoException {
	Integer quantidadeRequisitada = solicitacaoCompraItem.getQuantidade();
	Produto produto = solicitacaoCompraItem.getProduto();
	return cotacoes.stream()
		.sorted((c1, c2) -> Double.compare(menorValor(c1, quantidadeRequisitada, produto),
			menorValor(c2, quantidadeRequisitada, produto)))
		.findFirst().orElseThrow(() -> new CriadorPedidoException(
			"Não foi possível calcular a menor cotação para o produto " + produto.getId()));
    }

    private Double menorValor(Cotacao cotacao, Integer quantidadeRequisitada, Produto produto) throws RuntimeException {
	return cotacao.getPrecos().stream()
		.filter(cotacaoPreco -> quantidadeRequisitada >= cotacaoPreco.getQuantidadeMinima())
		.mapToDouble(cotacaoPreco -> cotacaoPreco.getPreco().getValor().doubleValue()).min()
		.orElseThrow(() -> new RuntimeException(
			"Não foi possível calcular o menor valor para o produto " + produto.getId()));
    }

    private Map<SolicitacaoCompraItem, List<Cotacao>> cotacoesDeProdutos(
	    List<SolicitacaoCompraItem> solicitacaoCompraItems) throws CriadorPedidoException {
	Map<SolicitacaoCompraItem, List<Cotacao>> cotacoesDeProdutos = solicitacaoCompraItems.parallelStream()
		.filter(solicitacaoCompraItem -> solicitacaoCompraItem.getQuantidade() > 0)
		.map(solicitacaoCompraItem -> Maps.entry(new HashableSolicitacaoCompraItem(solicitacaoCompraItem),
			realizaCotacoesParaProduto(solicitacaoCompraItem.getProduto())))
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	if (Maps.isEmpty(cotacoesDeProdutos)) {
	    throw new CriadorPedidoException(
		    "Ao menos um produto deve ser solicitado para a criação de um novo pedido");
	}
	return cotacoesDeProdutos;
    }

    private Map<SolicitacaoCompraItem, List<Cotacao>> verificaSeAlgumFornecedorNaoAtendeQuantidadeMinima(
	    Map<SolicitacaoCompraItem, List<Cotacao>> cotacoesDeProdutos) throws CriadorPedidoException {
	for (Entry<SolicitacaoCompraItem, List<Cotacao>> entry : cotacoesDeProdutos.entrySet()) {
	    SolicitacaoCompraItem solicitacaoCompraItem = entry.getKey();
	    Integer quantidadeRequisitada = solicitacaoCompraItem.getQuantidade();
	    Produto produto = solicitacaoCompraItem.getProduto();
	    List<Cotacao> cotacoes = entry.getValue();
	    if (!algumFornecedorAtendeQuantidadeMinima(quantidadeRequisitada, cotacoes)) {
		throw new CriadorPedidoException("Nenhum fornecedor encontrado para a quantidade solicitada do produto "
			+ produtoService.buscaPorGtin(produto).getNome());
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
