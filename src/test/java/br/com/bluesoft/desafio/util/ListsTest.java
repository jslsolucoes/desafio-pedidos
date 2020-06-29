package br.com.bluesoft.desafio.util;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class ListsTest {

    
    @Test
    public void criaListaVaziaComArrayNulo() {
	Object[] arrayOfObjects = null;
	List<Object> objects = Lists.newArrayList(arrayOfObjects);
	assertNotNull(objects);
	assertTrue(objects.isEmpty());
    }
    
    @Test
    public void criaListaVazia() {
	List<Object> objects = Lists.newArrayList();
	assertNotNull(objects);
	assertTrue(objects.isEmpty());
    }

    @Test
    public void criaListaVaziaComIterableNull() {
	Iterable<Object> iterable = null;
	List<Object> objects = Lists.newArrayList(iterable);
	assertNotNull(objects);
	assertTrue(objects.isEmpty());
    }

    @Test
    public void createListaVaziaComIterableValido() {
	Iterable<String> iterable = Lists.newArrayList("a", "b");
	List<String> objects = Lists.newArrayList(iterable);
	assertNotNull(objects);
	assertFalse(objects.isEmpty());
	assertThat(objects.size(),is(2));
	assertThat(objects, hasItems("a", "b"));
    }
    
    @Test
    public void verificaListaVazia() {
	assertTrue(Lists.isEmpty(null));
	assertTrue(Lists.isEmpty(Lists.newArrayList()));
	assertFalse(Lists.isEmpty(Lists.newArrayList("a","b")));
    }

}
