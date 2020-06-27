package br.com.bluesoft.desafio.ports.domain;

import java.math.BigDecimal;

public class PedidoItem {

    private final BigDecimal valor;
    private final Integer quantidade;
    private final Produto produto;

    public PedidoItem(final BigDecimal valor, final Integer quantidade, final Produto produto) {
	this.valor = valor;
	this.quantidade = quantidade;
	this.produto = produto;
    }

    public Dinheiro getValor() {
	return new Dinheiro(valor);
    }

    public Integer getQuantidade() {
	return quantidade;
    }

    public Produto getProduto() {
	return produto;
    }

}
