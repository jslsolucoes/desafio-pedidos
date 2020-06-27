package br.com.bluesoft.desafio.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.bluesoft.desafio.model.Produto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoDto {

    private String gtin;
    private String nome;

    public ProdutoDto() {
	this(null, null);
    }

    public ProdutoDto(final String gtin, final String nome) {
	this.gtin = gtin;
	this.nome = nome;
    }

    public String getGtin() {
	return gtin;
    }

    public String getNome() {
	return nome;
    }

    public static ProdutoDto converte(Produto produto) {
	return ProdutoDto.Builder.novoBuilder().comGtin(produto.getNome()).comNome(produto.getNome()).build();
    }

    public static class Builder {

	private String gtin;
	private String nome;

	private Builder() {

	}

	public Builder comNome(String nome) {
	    this.nome = nome;
	    return this;
	}

	public Builder comGtin(String gtin) {
	    this.gtin = gtin;
	    return this;
	}

	public static Builder novoBuilder() {
	    return new Builder();
	}

	public ProdutoDto build() {
	    return new ProdutoDto(gtin, nome);
	}

    }

}
