package br.com.bluesoft.desafio.ports.domain;

import java.math.BigDecimal;

public class Dinheiro {

    private final BigDecimal valor;

    public Dinheiro(final BigDecimal valor) {
	this.valor = valor;
    }

    public BigDecimal getValor() {
	return valor;
    }

    public Dinheiro multiplicaPor(Integer valor) {
	return new Dinheiro(this.valor.multiply(BigDecimal.valueOf(valor)));
    }
}
