package br.com.bluesoft.desafio.ports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.ports.domain.Fornecedor;
import br.com.bluesoft.desafio.ports.repo.FornecedorRepository;
import br.com.bluesoft.desafio.ports.service.FornecedorService;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    private FornecedorRepository fornecedorRepository;

    public FornecedorServiceImpl() {

    }

    @Autowired
    public FornecedorServiceImpl(FornecedorRepository fornecedorRepository) {
	this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    public Fornecedor criaUmNovoFornecedorSeNaoExistir(Fornecedor fornecedor) {
	return fornecedorRepository.verificaExistenciaDeUmFornecedorProcurandoPeloCnpj(fornecedor)
		.orElse(fornecedorRepository.criarUmNovoFornecedor(fornecedor));
    }
}
