package br.com.bluesoft.desafio.ports.repo;

import java.util.List;

import br.com.bluesoft.desafio.ports.domain.Produto;

public interface ProdutoRepository {

    public List<Produto> buscarPorTodosOsProdutos();

    public Produto buscaPorGtin(Produto produto);

}
