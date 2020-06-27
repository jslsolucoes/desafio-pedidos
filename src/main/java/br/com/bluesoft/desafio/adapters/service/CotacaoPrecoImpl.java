package br.com.bluesoft.desafio.adapters.service;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.bluesoft.desafio.ports.domain.CotacaoPreco;
import br.com.bluesoft.desafio.ports.domain.Dinheiro;

public class CotacaoPrecoImpl implements CotacaoPreco {

    private BigDecimal preco;

    @JsonProperty("quantidade_minima")
    private Integer quantidadeMinima;

    @Override
    public Dinheiro getPreco() {
	return new Dinheiro(preco);
    }

    @Override
    public Integer getQuantidadeMinima() {
	return quantidadeMinima;
    }

}
