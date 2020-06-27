package br.com.bluesoft.desafio.api.dto;

import br.com.bluesoft.desafio.service.impl.model.NovoPedido;

public class NovoPedidoDto {

    private String gtin;
    private Integer quantidade;

    public String getGtin() {
	return gtin;
    }

    public void setGtin(String gtin) {
	this.gtin = gtin;
    }

    public Integer getQuantidade() {
	return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
	this.quantidade = quantidade;
    }

    public static NovoPedido converter(NovoPedidoDto novoPedidoDto) {
	return new NovoPedido(novoPedidoDto.getGtin(), novoPedidoDto.getQuantidade());
    }

}