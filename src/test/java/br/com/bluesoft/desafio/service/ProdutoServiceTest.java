package br.com.bluesoft.desafio.service;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.junit.AbstractTest;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.repository.ProdutoRepository;
import br.com.bluesoft.desafio.util.Lists;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProdutoServiceTest extends AbstractTest {

    @Autowired
    private ProdutoService produtoService;

    @MockBean
    private ProdutoRepository produtoRepository;

    @Test
    public void semProdutosCadastrados() {
	List<Produto> produtos = produtoService.buscarPorTodosOsProdutosDisponiveis();
	assertNotNull(produtos);
	assertTrue(produtos.isEmpty());
    }

    @Test
    public void buscarPorTodosOsProdutosDisponiveis() {
	Produto produto = new Produto(1L, "1234", "produto1");
	when(produtoRepository.buscarPorTodosOsProdutosDisponiveis()).thenReturn(Lists.newArrayList(produto));
	List<Produto> produtos = produtoService.buscarPorTodosOsProdutosDisponiveis();
	assertNotNull(produtos);
	assertFalse(produtos.isEmpty());
	assertEquals(1, produtos.size());
	assertEquals("1234", produtos.get(0).getGtin());
    }

    @Test(expected = ProdutoServiceException.class)
    public void buscarProdutoPorGtinInexistente() throws ProdutoServiceException {
	Produto produto = new Produto(1L, "1234", "produto1");
	when(produtoRepository.buscarProdutoPorGtin(anyString())).thenReturn(Optional.empty());
	produtoService.buscarProdutoPorGtin(produto);
    }

    @Test
    public void buscarProdutoPorGtinValido() throws ProdutoServiceException {
	Produto produto = new Produto(1L, "1234", "produto1");
	when(produtoRepository.buscarProdutoPorGtin(anyString())).thenReturn(Optional.of(produto));
	Produto produtoCarregado = produtoService.buscarProdutoPorGtin(produto);
	assertNotNull(produtoCarregado);
	assertEquals("1234", produtoCarregado.getGtin());
    }

}
