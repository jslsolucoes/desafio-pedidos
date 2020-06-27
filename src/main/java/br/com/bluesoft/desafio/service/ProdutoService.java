package br.com.bluesoft.desafio.service;

import java.util.List;

import br.com.bluesoft.desafio.model.Produto;

public interface ProdutoService {

    public List<Produto> buscarPorTodosOsProdutosDisponiveis();

    public Produto buscarProdutoPorGtin(Produto produto);

}
