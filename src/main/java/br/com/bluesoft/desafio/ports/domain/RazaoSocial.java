package br.com.bluesoft.desafio.ports.domain;

public class RazaoSocial {

    private final String razaoSocial;

    public RazaoSocial(final String razaoSocial) {
	this.razaoSocial = razaoSocial;
    }

    public String getValue() {
	return razaoSocial;
    }
    
    @Override
    public String toString() {
	return razaoSocial;
    }
}
