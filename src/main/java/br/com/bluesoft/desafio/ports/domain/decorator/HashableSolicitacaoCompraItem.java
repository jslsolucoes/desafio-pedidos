package br.com.bluesoft.desafio.ports.domain.decorator;

import br.com.bluesoft.desafio.ports.domain.Gtin;
import br.com.bluesoft.desafio.ports.domain.SolicitacaoCompraItem;

public class HashableSolicitacaoCompraItem extends SolicitacaoCompraItemDecorator {

    public HashableSolicitacaoCompraItem(SolicitacaoCompraItem solicitacaoCompraItem) {
	super(solicitacaoCompraItem);
    }

    @Override
    public int hashCode() {
	Gtin gtin = getProduto().getId();
	final int prime = 31;
	int result = 1;
	result = prime * result + ((gtin == null) ? 0 : gtin.hashCode());
	return result;
    }

}
