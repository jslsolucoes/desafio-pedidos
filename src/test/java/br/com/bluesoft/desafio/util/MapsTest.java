package br.com.bluesoft.desafio.util;

import org.junit.Test;

public class MapsTest {

    
    @Test(expected = IllegalArgumentException.class)
    public void criaMapaInvalido() {
	Maps.of("1");
    }
    
    

}
