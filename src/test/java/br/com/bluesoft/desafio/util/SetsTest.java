package br.com.bluesoft.desafio.util;

import org.junit.Test;

import br.com.bluesoft.desafio.junit.AbstractTest;

public class SetsTest extends AbstractTest {

    @Test
    public void hashableSet() {
	MutableSet<String> produtos = Sets.<String>newHashMutableSet().addItem("a").addItem("b");
	assertNotNull(produtos);
	assertFalse(produtos.isEmpty());
	assertEquals(2,produtos.size());
	assertTrue(produtos.contains("a"));
	assertNotNull(produtos.iterator());
	assertTrue(produtos.remove("b"));
	assertEquals(1,produtos.size());
	produtos.clear();
	assertTrue(produtos.isEmpty());
    }

}
