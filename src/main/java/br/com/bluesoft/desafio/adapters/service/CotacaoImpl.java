package br.com.bluesoft.desafio.adapters.service;

import java.util.List;

import br.com.bluesoft.desafio.adapters.repo.domain.FornecedorImpl;
import br.com.bluesoft.desafio.ports.domain.Cotacao;
import br.com.bluesoft.desafio.ports.domain.CotacaoPreco;
import br.com.bluesoft.desafio.ports.domain.Fornecedor;
import br.com.bluesoft.desafio.ports.domain.Produto;
import br.com.bluesoft.desafio.util.Lists;

public class CotacaoImpl implements Cotacao {

    private String cnpj;
    private String nome;
    private List<CotacaoPrecoImpl> precos;
    private Produto produto;

    public String getCnpj() {
	return cnpj;
    }

    public CotacaoImpl setProduto(Produto produto) {
	this.produto = produto;
	return this;
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

    public void setPrecos(List<CotacaoPrecoImpl> precos) {
	this.precos = precos;
    }

    @Override
    public Fornecedor getFornecedor() {
	return new FornecedorImpl(cnpj, nome);
    }

    @Override
    public List<CotacaoPreco> getPrecos() {
	return Lists.cast(precos);
    }

    @Override
    public Produto getProduto() {
	return produto;
    }

}
