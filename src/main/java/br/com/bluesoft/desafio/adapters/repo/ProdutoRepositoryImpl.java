package br.com.bluesoft.desafio.adapters.repo;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.bluesoft.desafio.adapters.repo.domain.FornecedorImpl;
import br.com.bluesoft.desafio.adapters.repo.domain.ProdutoImpl;
import br.com.bluesoft.desafio.ports.domain.Produto;
import br.com.bluesoft.desafio.ports.repo.ProdutoRepository;
import br.com.bluesoft.desafio.util.Lists;

@Repository
public class ProdutoRepositoryImpl extends CrudRepositoryImpl<FornecedorImpl, Long> implements ProdutoRepository {

    @Autowired
    public ProdutoRepositoryImpl(EntityManager entityManager) {
	super(entityManager);
    }

    @Override
    public List<Produto> buscarPorTodosOsProdutos() {
	return Lists.cast(
		entityManager.createQuery("select prod from ProdutoImpl prod", ProdutoImpl.class).getResultList());
    }

    @Override
    public Produto buscaPorGtin(Produto produto) {
	return entityManager.createQuery("select prod from ProdutoImpl prod where prod.gtin = :gtin", ProdutoImpl.class)
		.setParameter("gtin", produto.getId().getValue()).getSingleResult();
    }

}
