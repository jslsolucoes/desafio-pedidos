package br.com.bluesoft.desafio.adapters.repo;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.bluesoft.desafio.adapters.repo.domain.FornecedorImpl;
import br.com.bluesoft.desafio.ports.domain.Fornecedor;
import br.com.bluesoft.desafio.ports.repo.FornecedorRepository;

@Repository
public class FornecedorRepositoryImpl extends CrudRepositoryImpl<FornecedorImpl, Long> implements FornecedorRepository {

    @Autowired
    public FornecedorRepositoryImpl(EntityManager entityManager) {
	super(entityManager);
    }

    @Override
    public Optional<Fornecedor> verificaExistenciaDeUmFornecedorProcurandoPeloCnpj(Fornecedor fornecedor) {
	try {
	    String sql = "select f from FornecedorImpl f where f.cnpj = :cnpj";
	    return Optional.of(entityManager.createQuery(sql, FornecedorImpl.class)
		    .setParameter("cnpj", fornecedor.getCnpj().getValue()).getSingleResult());
	} catch (NoResultException e) {
	    return Optional.empty();
	}
    }

    @Override
    public Fornecedor criarUmNovoFornecedor(Fornecedor fornecedor) {
	return save(new FornecedorImpl(fornecedor.getCnpj().getValue(), fornecedor.getRazaoSocial().getValue()));
    }

}
