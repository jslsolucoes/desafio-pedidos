package br.com.bluesoft.desafio.api.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FornecedorDto {

    private String nome;

    public FornecedorDto(String nome) {
	this.nome = nome;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public static class Builder {

	private String nome;

	private Builder() {

	}

	public static Builder newBuilder() {
	    return new Builder();
	}

	public Builder withNome(String nome) {
	    this.nome = nome;
	    return this;
	}

	public FornecedorDto build() {
	    return new FornecedorDto(nome);
	}
    }

}
