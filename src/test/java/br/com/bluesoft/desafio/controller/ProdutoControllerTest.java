package br.com.bluesoft.desafio.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.api.RecursoNaoEncontradoException;
import br.com.bluesoft.desafio.api.controller.ProdutoController;
import br.com.bluesoft.desafio.api.dto.ProdutoDto;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.service.ProdutoService;
import br.com.bluesoft.desafio.test.AbstractTest;
import br.com.bluesoft.desafio.util.Lists;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProdutoControllerTest extends AbstractTest {

    @Autowired
    private ProdutoController produtoController;

    @MockBean
    private ProdutoService produtoService;

    @Test(expected = RecursoNaoEncontradoException.class)
    public void semProdutosDisponiveis() throws Exception {
	produtoController.listarTodosOsProdutosDisponiveis();
    }

    @Test
    public void buscarPorTodosOsProdutosDisponiveis() throws Exception {
	Produto produto = new Produto(1L, "1234", "produto1");
	when(produtoService.buscarPorTodosOsProdutosDisponiveis()).thenReturn(Lists.newArrayList(produto));
	ResponseEntity<List<ProdutoDto>> responseEntity = produtoController.listarTodosOsProdutosDisponiveis();
	assertEquals(200, responseEntity.getStatusCodeValue());
	assertTrue(Lists.orAnyMatch(responseEntity.getBody(),
		(produtoDto) -> produtoDto.getGtin().equals(produto.getGtin()),
		(produtoDto) -> produtoDto.getNome().equals(produto.getNome())));
    }

}
