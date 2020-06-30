package br.com.bluesoft.desafio.model;

import org.junit.Test;

import br.com.bluesoft.desafio.junit.AbstractTest;

public class FornecedorTest extends AbstractTest {

    @Test
    public void construtor() {
	Fornecedor fornecedor = new Fornecedor(1L, "1234", "for1");
	assertEquals(1L, fornecedor.getId());
	assertEquals("1234", fornecedor.getCnpj());
	assertEquals("for1", fornecedor.getRazaoSocial());
    }
    
    @Test
    public void modificadores() {
	Fornecedor fornecedor = new Fornecedor();
	fornecedor.setCnpj("1234");
	fornecedor.setRazaoSocial("for1");
	assertEquals("1234", fornecedor.getCnpj());
	assertEquals("for1", fornecedor.getRazaoSocial());
    }
    
    @Test
    public void builder() {
	Fornecedor fornecedor = Fornecedor.Builder.novoBuilder().comCnpj("1234").comRazaoSocial("produto1").constroi();
	assertEquals("1234", fornecedor.getCnpj());
	assertEquals("produto1", fornecedor.getRazaoSocial());
    }
    
    @Test
    public void verificarIgualidade() {
	Fornecedor fornecedor = Fornecedor.Builder.novoBuilder().comCnpj("1234").comRazaoSocial("produto1").constroi();
	Fornecedor fornecedor2 = Fornecedor.Builder.novoBuilder().comCnpj("1234").comRazaoSocial("produto2").constroi();
	Fornecedor fornecedor3 = Fornecedor.Builder.novoBuilder().constroi();
	assertFalse(fornecedor3.equals(fornecedor2));
	assertTrue(fornecedor.equals(fornecedor));
	assertTrue(fornecedor.equals(fornecedor2));
	assertFalse(fornecedor.equals(null));
	assertFalse(fornecedor.equals(new String("aee")));
    }
    
    @Test
    public void verificarHashing() {
	Fornecedor fornecedor = Fornecedor.Builder.novoBuilder().comCnpj("1234").comRazaoSocial("produto1").constroi();
	Fornecedor fornecedor2 = Fornecedor.Builder.novoBuilder().comCnpj("1234").comRazaoSocial("produto1").constroi();
	Fornecedor fornecedor3 = Fornecedor.Builder.novoBuilder().comCnpj("12345").comRazaoSocial("produto1").constroi();
	Fornecedor fornecedor4 = Fornecedor.Builder.novoBuilder().constroi();
	assertEquals(31,fornecedor4.hashCode());
	assertEquals(fornecedor.hashCode(), fornecedor2.hashCode());
	assertNotEquals(fornecedor2.hashCode(), fornecedor3.hashCode());
	
    }
}
