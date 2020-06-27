package br.com.bluesoft.desafio.ports.service;

import java.util.List;

import br.com.bluesoft.desafio.ports.domain.Produto;

public interface ProdutoService {

    public List<Produto> listarTodosOsProdutosDisponiveis();

    public Produto buscaPorGtin(Produto produto);

}
