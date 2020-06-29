package br.com.bluesoft.desafio.model;

import org.junit.Test;

import br.com.bluesoft.desafio.junit.AbstractTest;

public class ProdutoTest extends AbstractTest {

    @Test
    public void construtor() {
	Produto produto = new Produto(1L, "1234", "produto1");
	assertEquals(1L, produto.getId());
	assertEquals("1234", produto.getGtin());
	assertEquals("produto1", produto.getNome());
    }
}
