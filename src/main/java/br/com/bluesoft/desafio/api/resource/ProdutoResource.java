package br.com.bluesoft.desafio.api.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesoft.desafio.api.response.dto.ProdutoDto;
import br.com.bluesoft.desafio.ports.domain.Produto;
import br.com.bluesoft.desafio.ports.service.ProdutoService;
import br.com.bluesoft.desafio.util.Lists;
import br.com.bluesoft.desafio.util.Responses;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/v1")
public class ProdutoResource {

    private ProdutoService produtoService;

    public ProdutoResource() {

    }

    @Autowired
    public ProdutoResource(ProdutoService produtoService) {
	this.produtoService = produtoService;
    }

    @GetMapping(value = "/produtos")
    public ResponseEntity<List<ProdutoDto>> listarTodosOsProdutosDisponiveis() throws NotFoundException {
	List<Produto> produtos = produtoService.listarTodosOsProdutosDisponiveis();
	if (Lists.isEmpty(produtos)) {
	    throw new NotFoundException("Não foi possível encontrar nenhum produto cadastrado");
	} else {
	    return Responses.ok(produtos.stream().map(ProdutoDto::converte).collect(Collectors.toList()));
	}
    }
}
