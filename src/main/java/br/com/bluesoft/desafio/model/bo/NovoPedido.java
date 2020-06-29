package br.com.bluesoft.desafio.model.bo;

import br.com.bluesoft.desafio.model.Produto;

public class NovoPedido {

    private Produto produto;
    private final Integer quantidade;

    public NovoPedido(final Produto produto, final Integer quantidade) {
	this.produto = produto;
	this.quantidade = quantidade;
    }

    public Produto getProduto() {
	return produto;
    }

    public NovoPedido setProduto(Produto produto) {
	this.produto = produto;
	return this;
    }

    public Integer getQuantidade() {
	return quantidade;
    }

}
