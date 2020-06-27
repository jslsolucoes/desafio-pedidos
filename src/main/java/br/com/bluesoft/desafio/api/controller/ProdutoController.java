package br.com.bluesoft.desafio.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesoft.desafio.api.RecursoNaoEncontradoException;
import br.com.bluesoft.desafio.api.dto.ProdutoDto;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.service.ProdutoService;
import br.com.bluesoft.desafio.util.Lists;
import br.com.bluesoft.desafio.util.Responses;

@RestController
@RequestMapping("/api/v1")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController() {

    }

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
	this.produtoService = produtoService;
    }

    @GetMapping(value = "/produtos")
    public ResponseEntity<List<ProdutoDto>> listarTodosOsProdutosDisponiveis() throws RecursoNaoEncontradoException {
	List<Produto> produtos = produtoService.buscarPorTodosOsProdutosDisponiveis();
	if (Lists.isEmpty(produtos)) {
	    throw new RecursoNaoEncontradoException("Não foi possível encontrar nenhum produto cadastrado");
	} else {
	    return Responses.ok(Lists.transform(produtos, ProdutoDto::converte));
	}
    }
}
