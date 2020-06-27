package br.com.bluesoft.desafio.service.impl.model;

import java.util.List;

public class Cotacao {

    private String cnpj;
    private String nome;
    private List<CotacaoPreco> precos;

    public String getCnpj() {
	return cnpj;
    }

    public void setCnpj(String cnpj) {
	this.cnpj = cnpj;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public List<CotacaoPreco> getPrecos() {
	return precos;
    }

    public void setPrecos(List<CotacaoPreco> precos) {
	this.precos = precos;
    }

}
