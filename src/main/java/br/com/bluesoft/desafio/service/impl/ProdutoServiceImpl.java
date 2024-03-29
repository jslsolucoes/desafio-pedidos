package br.com.bluesoft.desafio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.repository.ProdutoRepository;
import br.com.bluesoft.desafio.service.ProdutoService;
import br.com.bluesoft.desafio.service.ProdutoServiceException;

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
    public List<Produto> buscarPorTodosOsProdutosDisponiveis() {
	return produtoRepository.buscarPorTodosOsProdutosDisponiveis();
    }

    @Override
    public Produto buscarProdutoPorGtin(Produto produto) throws ProdutoServiceException {
	try {
	    return produtoRepository.buscarProdutoPorGtin(produto.getGtin()).orElseThrow(() -> new Exception(
		    String.format("Não foi possível encontrar um produto com o código %s", produto.getGtin())));
	} catch (Exception e) {
	    throw new ProdutoServiceException(e.getMessage());
	}
    }

}
