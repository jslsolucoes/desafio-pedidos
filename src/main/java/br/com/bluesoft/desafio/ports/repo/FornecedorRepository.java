package br.com.bluesoft.desafio.ports.repo;

import java.util.Optional;

import br.com.bluesoft.desafio.ports.domain.Fornecedor;

public interface FornecedorRepository {

    public Optional<Fornecedor> verificaExistenciaDeUmFornecedorProcurandoPeloCnpj(Fornecedor fornecedor);

    public Fornecedor criarUmNovoFornecedor(Fornecedor fornecedor);

}
