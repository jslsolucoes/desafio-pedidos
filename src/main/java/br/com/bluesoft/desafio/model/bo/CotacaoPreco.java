package br.com.bluesoft.desafio.model.bo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CotacaoPreco {

    private BigDecimal preco;

    @JsonProperty("quantidade_minima")
    private Integer quantidadeMinima;

    public CotacaoPreco() {

    }

    public CotacaoPreco(BigDecimal preco, Integer quantidadeMinima) {
	this.preco = preco;
	this.quantidadeMinima = quantidadeMinima;
    }

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

    public Boolean atendeQuantidadeMinimima(Integer quantidade) {
	return quantidadeMinima <= quantidade;
    }

}
