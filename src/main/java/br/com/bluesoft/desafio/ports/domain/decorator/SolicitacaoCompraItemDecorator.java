package br.com.bluesoft.desafio.ports.domain.decorator;

import br.com.bluesoft.desafio.ports.domain.Produto;
import br.com.bluesoft.desafio.ports.domain.SolicitacaoCompraItem;

public abstract class SolicitacaoCompraItemDecorator implements SolicitacaoCompraItem {

    protected SolicitacaoCompraItem solicitacaoCompraItem;

    public SolicitacaoCompraItemDecorator(SolicitacaoCompraItem solicitacaoCompraItem) {
	this.solicitacaoCompraItem = solicitacaoCompraItem;
    }

    @Override
    public Produto getProduto() {
	return solicitacaoCompraItem.getProduto();
    }

    @Override
    public Integer getQuantidade() {
	return solicitacaoCompraItem.getQuantidade();
    }

}
