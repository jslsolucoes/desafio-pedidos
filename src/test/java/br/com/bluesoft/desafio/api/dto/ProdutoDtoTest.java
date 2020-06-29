package br.com.bluesoft.desafio.api.dto;

import org.junit.Test;

import br.com.bluesoft.desafio.junit.AbstractTest;
import br.com.bluesoft.desafio.model.Produto;

public class ProdutoDtoTest extends AbstractTest {

    @Test
    public void verificaConversaoDeProduto() {
	Produto produto = new Produto(1L, "1234", "produto1");
	ProdutoDto produtoDto = ProdutoDto.converte(produto);
	assertEquals("1234", produtoDto.getGtin());
	assertEquals("produto1", produtoDto.getNome());
    }
}
