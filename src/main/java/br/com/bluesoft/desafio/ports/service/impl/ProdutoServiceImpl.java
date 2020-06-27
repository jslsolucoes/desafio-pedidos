package br.com.bluesoft.desafio.ports.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.ports.domain.Produto;
import br.com.bluesoft.desafio.ports.repo.ProdutoRepository;
import br.com.bluesoft.desafio.ports.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoServiceImpl() {

    }

    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
	this.produtoRepository = produtoRepository;
    }

    @Override
    public List<Produto> listarTodosOsProdutosDisponiveis() {
	return produtoRepository.buscarPorTodosOsProdutos();
    }

    @Override
    public Produto buscaPorGtin(Produto produto) {
	return produtoRepository.buscaPorGtin(produto);
    }

}
