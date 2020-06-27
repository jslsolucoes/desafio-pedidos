package br.com.bluesoft.desafio.ports.domain;

import java.util.List;

public interface Cotacao {

    public Produto getProduto();

    public Fornecedor getFornecedor();

    public List<CotacaoPreco> getPrecos();

}
