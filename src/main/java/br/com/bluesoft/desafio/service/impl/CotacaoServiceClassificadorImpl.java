package br.com.bluesoft.desafio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.model.bo.Cotacao;
import br.com.bluesoft.desafio.service.CotacaoService;
import br.com.bluesoft.desafio.service.CotacaoServiceClassificador;
import br.com.bluesoft.desafio.service.CotacaoServiceException;

@Service
public class CotacaoServiceClassificadorImpl implements CotacaoServiceClassificador {

    private CotacaoService cotacaoService;

    @Autowired
    public CotacaoServiceClassificadorImpl(CotacaoService cotacaoService) {
	this.cotacaoService = cotacaoService;
    }

    @Override
    public Cotacao melhorCotacao(Produto produto, Integer quantidade) throws CotacaoServiceException {
	return cotacaoService.realizaCotacoesParaProduto(produto).stream()
		.filter(cotacao -> cotacao.atendeQuantidadeMinimima(quantidade))
		.sorted((cotacao1, cotacao2) -> menorValor(produto, quantidade, cotacao1, cotacao2)).findFirst()
		.orElseThrow(() -> new CotacaoServiceException(
			"Nenhum fornecedor encontrado para a quantidade solicitada do produto " + produto.getNome()));
    }

    private Integer menorValor(Produto produto, Integer quantidade, Cotacao cotacao1, Cotacao cotacao2)
	    throws RuntimeException {
	try {
	    return cotacao1.getMelhorPreco(produto, quantidade).compareTo(cotacao2.getMelhorPreco(produto, quantidade));
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

}
