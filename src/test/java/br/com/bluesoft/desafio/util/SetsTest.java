package br.com.bluesoft.desafio.util;

import java.util.Set;

import org.junit.Test;

import br.com.bluesoft.desafio.junit.AbstractTest;

public class SetsTest extends AbstractTest {

    
    @Test
    public void setVazios() {
	Set<String> set = null;
	assertNotNull(Sets.newHashSet(set));
    }
    
    @Test
    public void arraysVazios() {
	String[] elements = null;
	assertNotNull(Sets.newHashSet(elements));
    }


}
