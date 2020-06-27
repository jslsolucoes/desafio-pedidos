package br.com.bluesoft.desafio.ports.domain;

public class Cnpj {

    private final String cnpj;

    public Cnpj(final String cnpj) {
	this.cnpj = cnpj;
    }

    public String getValue() {
	return cnpj;
    }

    @Override
    public String toString() {
	return cnpj;
    }

}
