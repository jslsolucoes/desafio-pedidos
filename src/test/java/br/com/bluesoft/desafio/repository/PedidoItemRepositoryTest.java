package br.com.bluesoft.desafio.repository;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoItem;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.test.AbstractTest;
import br.com.bluesoft.desafio.util.Lists;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
public class PedidoItemRepositoryTest extends AbstractTest {

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Test
    public void criarNovoItemDePedido() {
	List<PedidoItem> pedidoItens = Lists.newArrayList(
		new PedidoItem(null, BigDecimal.TEN, 1, new Produto(1L, null, null), new Pedido(1L, null, null)));
	List<PedidoItem> itensDePedido = pedidoItemRepository.criarNovosItensDePedido(pedidoItens);
	assertNotNull(itensDePedido);
	assertEquals(1, itensDePedido.size());
	assertNotNull(itensDePedido.get(0).getId());
    }

}
