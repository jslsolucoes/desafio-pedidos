package br.com.bluesoft.desafio.service.impl.model;

public class NovoPedido {

    private final String gtin;
    private final Integer quantidade;

    public NovoPedido(final String gtin, final Integer quantidade) {
	this.gtin = gtin;
	this.quantidade = quantidade;
    }

    public String getGtin() {
	return gtin;
    }

    public Integer getQuantidade() {
	return quantidade;
    }
}
