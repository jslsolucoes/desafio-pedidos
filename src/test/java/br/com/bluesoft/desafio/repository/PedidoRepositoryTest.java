package br.com.bluesoft.desafio.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.test.AbstractTest;
import br.com.bluesoft.desafio.util.Lists;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
public class PedidoRepositoryTest extends AbstractTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    public void buscarPorTodosOsPedidosDisponiveis() {
	List<Pedido> pedidos = pedidoRepository.buscarPorTodosOsPedidosDisponiveis();
	assertNotNull(pedidos);
	assertEquals(2, pedidos.size());
	assertTrue(Lists.anyMatch(pedidos, (pedido) -> pedido.getId().equals(1L)
		&& "00.000.000/00000-00".equals(pedido.getFornecedor().getCnpj())));
    }
    
    

}