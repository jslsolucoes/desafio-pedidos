package br.com.bluesoft.desafio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.bluesoft.desafio.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

    @Query("select prod from Produto prod order by prod.nome")
    public List<Produto> buscarPorTodosOsProdutosDisponiveis();

    @Query("select prod from Produto prod where prod.gtin = :gtin")
    public Optional<Produto> buscarProdutoPorGtin(@Param("gtin") String gtin);

}
