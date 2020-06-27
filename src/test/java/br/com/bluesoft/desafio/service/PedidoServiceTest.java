package br.com.bluesoft.desafio.service;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoItem;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.test.AbstractTest;
import br.com.bluesoft.desafio.util.Lists;
import br.com.bluesoft.desafio.util.Sets;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PedidoServiceTest extends AbstractTest {

    @Autowired
    private PedidoService pedidoService;

    @MockBean
    private PedidoRepository pedidoRepository;

    @Test
    public void semPedidosCadastrados() {
	List<Pedido> pedidos = pedidoService.buscarPorTodosOsPedidosDisponiveis();
	assertNotNull(pedidos);
	assertTrue(pedidos.isEmpty());
    }

    @Test
    public void buscarPorTodosOsPedidosDisponiveis() {
	PedidoItem pedidoItem = new PedidoItem(1L, BigDecimal.TEN, 1, new Produto(1L, "1234", "produto1"), null);
	Pedido pedido = new Pedido(1L, new Fornecedor(1L, "00.000", "fornecedor1"), Sets.newHashSet(pedidoItem));
	when(pedidoRepository.buscarPorTodosOsPedidosDisponiveis()).thenReturn(Lists.newArrayList(pedido));
	List<Pedido> pedidos = pedidoService.buscarPorTodosOsPedidosDisponiveis();
	assertNotNull(pedidos);
	assertFalse(pedidos.isEmpty());
	assertEquals(1, pedidos.size());
	assertEquals(1L, pedidos.get(0).getId());
	assertEquals("00.000", pedidos.get(0).getFornecedor().getCnpj());
	assertEquals("1234", pedidos.get(0).getItens().iterator().next().getProduto().getGtin());
    }

}
