package br.com.bluesoft.desafio.service.impl.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CotacaoPreco {

    private BigDecimal preco;

    @JsonProperty("quantidade_minima")
    private Integer quantidadeMinima;

    public BigDecimal getPreco() {
	return preco;
    }

    public void setPreco(BigDecimal preco) {
	this.preco = preco;
    }

    public Integer getQuantidadeMinima() {
	return quantidadeMinima;
    }

    public void setQuantidadeMinima(Integer quantidadeMinima) {
	this.quantidadeMinima = quantidadeMinima;
    }

}
