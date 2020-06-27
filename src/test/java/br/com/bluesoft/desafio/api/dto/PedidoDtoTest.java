package br.com.bluesoft.desafio.api.dto;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.Test;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoItem;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.test.AbstractTest;
import br.com.bluesoft.desafio.util.Sets;

public class PedidoDtoTest extends AbstractTest {

    @Test
    public void verificaConversaoDePedido() {
	Set<PedidoItem> itens = Sets
		.newHashSet(new PedidoItem(1L, BigDecimal.valueOf(12.5), 2, new Produto(1L, "1234", "produto1"), null));
	Pedido pedido = new Pedido(1L, new Fornecedor(1L, "1234", "fornecedor1"), itens);
	PedidoDto pedidoDto = PedidoDto.converte(pedido);
	assertEquals("fornecedor1", pedidoDto.getFornecedor().getNome());
	assertEquals(1L, pedidoDto.getId());
	assertEquals(1, pedidoDto.getItens().size());
	assertEquals(BigDecimal.valueOf(25.0), pedidoDto.getItens().get(0).getTotal());
    }
}
