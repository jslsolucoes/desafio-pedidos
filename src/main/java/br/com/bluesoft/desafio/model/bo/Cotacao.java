package br.com.bluesoft.desafio.model.bo;

import java.math.BigDecimal;
import java.util.List;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.service.CotacaoServiceException;
import br.com.bluesoft.desafio.util.Lists;

public class Cotacao {

    private String cnpj;
    private String nome;
    private List<CotacaoPreco> precos;

    public Cotacao() {

    }

    public Cotacao(String cnpj, String nome, List<CotacaoPreco> precos) {
	this.cnpj = cnpj;
	this.nome = nome;
	this.precos = precos;
    }

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

    public BigDecimal getMelhorPreco(Produto produto, Integer quantidade) throws CotacaoServiceException {
	return new BigDecimal(precos.stream().filter(cotacaoPreco -> cotacaoPreco.atendeQuantidadeMinimima(quantidade))
		.mapToDouble(cotacaoPreco -> cotacaoPreco.getPreco().doubleValue()).min()
		.orElseThrow(() -> new CotacaoServiceException(
			"Não foi possível calcular o menor preço para o produto " + produto.getGtin())));
    }

    public Fornecedor getFornecedor() {
	return Fornecedor.Builder.novoBuilder().comCnpj(cnpj).comRazaoSocial(nome).constroi();
    }

    public Boolean atendeQuantidadeMinimima(Integer quantidade) {
	return Lists.anyMatch(precos, cotacaoPreco -> cotacaoPreco.atendeQuantidadeMinimima(quantidade));
    }
}
