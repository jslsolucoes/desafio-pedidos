package br.com.bluesoft.desafio.repository;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.desafio.junit.AbstractTest;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoItem;
import br.com.bluesoft.desafio.model.Produto;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
public class PedidoItemRepositoryTest extends AbstractTest {

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Test
    public void criarNovoItemDePedido() {
	PedidoItem pedidoItem = pedidoItemRepository.criarNovo(
		new PedidoItem(null, BigDecimal.TEN, 1, new Produto(1L, null, null), new Pedido(1L, null, null)));
	assertNotNull(pedidoItem);
	assertNotNull(pedidoItem.getId());
    }

}
