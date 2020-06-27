package br.com.bluesoft.desafio.ports.service;

import br.com.bluesoft.desafio.ports.domain.Fornecedor;

public interface FornecedorService {

    public Fornecedor criaUmNovoFornecedorSeNaoExistir(Fornecedor fornecedor);

}
