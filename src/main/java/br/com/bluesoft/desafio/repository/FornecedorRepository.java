package br.com.bluesoft.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.bluesoft.desafio.model.Fornecedor;

@Repository
public interface FornecedorRepository extends CrudRepository<Fornecedor, Long> {

    @Query("select forn from Fornecedor forn where forn.cnpj = :cnpj")
    public Optional<Fornecedor> buscarFornecedorPorCnpj(@Param("cnpj") String cnpj);

    public default Fornecedor criarUmNovoFornecedorSeNaoExistir(Fornecedor fornecedor) {
	return buscarFornecedorPorCnpj(fornecedor.getCnpj()).orElse(save(fornecedor));
    }

}
