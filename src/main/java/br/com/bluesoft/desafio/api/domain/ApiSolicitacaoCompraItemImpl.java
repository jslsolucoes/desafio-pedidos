package br.com.bluesoft.desafio.api.domain;

import br.com.bluesoft.desafio.ports.domain.Produto;
import br.com.bluesoft.desafio.ports.domain.SolicitacaoCompraItem;

public class ApiSolicitacaoCompraItemImpl implements SolicitacaoCompraItem {

    private final Integer quantidade;
    private final String gtin;

    public ApiSolicitacaoCompraItemImpl(String gtin, Integer quantidade) {
	this.gtin = gtin;
	this.quantidade = quantidade;
    }

    @Override
    public Produto getProduto() {
	return new ApiProdutoImpl(gtin);
    }

    @Override
    public Integer getQuantidade() {
	return quantidade;
    }

}
