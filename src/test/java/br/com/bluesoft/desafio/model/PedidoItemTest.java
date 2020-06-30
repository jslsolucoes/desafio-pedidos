package br.com.bluesoft.desafio.model;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.bluesoft.desafio.junit.AbstractTest;

public class PedidoItemTest extends AbstractTest {

    @Test
    public void construtor() {
	PedidoItem pedidoItem = new PedidoItem(1L, BigDecimal.TEN, 1, null, null);
	assertEquals(1L, pedidoItem.getId());
	assertEquals(Integer.valueOf(1), pedidoItem.getQuantidade());
	assertEquals(BigDecimal.TEN, pedidoItem.getValor());
    }

    @Test
    public void builder() {
	PedidoItem pedidoItem = PedidoItem.Builder.novoBuilder().comQuantidade(1).comValor(BigDecimal.TEN).constroi();
	assertEquals(Integer.valueOf(1), pedidoItem.getQuantidade());
	assertEquals(BigDecimal.TEN, pedidoItem.getValor());
    }
}
