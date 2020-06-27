package br.com.bluesoft.desafio.api.domain;

import br.com.bluesoft.desafio.ports.domain.Gtin;
import br.com.bluesoft.desafio.ports.domain.Produto;

@SuppressWarnings("serial")
public class ApiProdutoImpl implements Produto {

    private final String gtin;
    private final String nome;

    public ApiProdutoImpl(final String gtin) {
	this(gtin, null);
    }

    public ApiProdutoImpl(final String gtin, final String nome) {
	this.gtin = gtin;
	this.nome = nome;
    }

    @Override
    public Gtin getId() {
	return new Gtin(gtin);
    }

    @Override
    public String getNome() {
	return nome;
    }

}
