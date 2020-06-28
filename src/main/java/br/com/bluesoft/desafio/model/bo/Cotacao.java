package br.com.bluesoft.desafio.model.bo;

import java.math.BigDecimal;
import java.util.List;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.service.CotacaoServiceException;

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

    public BigDecimal getMelhorPreco(Produto produto,Integer quantidade) throws CotacaoServiceException {
	return new BigDecimal(precos.stream().filter(cotacaoPreco -> quantidade >= cotacaoPreco.getQuantidadeMinima())
		.mapToDouble(cotacaoPreco -> cotacaoPreco.getPreco().doubleValue())
		.min().orElseThrow(() -> new CotacaoServiceException("Não foi possível calcular o menor para o produto " + produto.getGtin())));
    }
    
    public Fornecedor getFornecedor() {
	return Fornecedor.Builder.novoBuilder().comCnpj(cnpj).comRazaoSocial(nome).constroi();
    }

}
