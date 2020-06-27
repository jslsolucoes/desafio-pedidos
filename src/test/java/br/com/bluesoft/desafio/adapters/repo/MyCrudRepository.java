package br.com.bluesoft.desafio.adapters.repo;

import javax.persistence.EntityManager;

import br.com.bluesoft.desafio.adapters.repo.domain.FornecedorImpl;

public class MyCrudRepository extends CrudRepositoryImpl<FornecedorImpl, Long> {

    public MyCrudRepository(EntityManager entityManager) {
	super(entityManager);
    }

}