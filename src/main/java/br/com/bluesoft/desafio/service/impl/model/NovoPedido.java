package br.com.bluesoft.desafio.service.impl.model;

import br.com.bluesoft.desafio.model.Produto;

public class NovoPedido {

    private final Produto produto;
    private final Integer quantidade;

    public NovoPedido(final Produto produto, final Integer quantidade) {
	this.produto = produto;
	this.quantidade = quantidade;
    }

    public Produto getProduto() {
	return produto;
    }

    public Integer getQuantidade() {
	return quantidade;
    }

    @Override
    public int hashCode() {
	return produto.getGtin().hashCode();
    }

}
