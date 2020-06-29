package br.com.bluesoft.desafio.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.api.RecursoNaoEncontradoException;
import br.com.bluesoft.desafio.api.controller.PedidoController;
import br.com.bluesoft.desafio.api.dto.PedidoDto;
import br.com.bluesoft.desafio.junit.AbstractTest;
import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoItem;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.service.PedidoService;
import br.com.bluesoft.desafio.util.Lists;
import br.com.bluesoft.desafio.util.Sets;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PedidoControllerTest extends AbstractTest {

    @Autowired
    private PedidoController pedidoController;

    @MockBean
    private PedidoService pedidoService;

    @Test(expected = RecursoNaoEncontradoException.class)
    public void semPedidosDisponiveis() throws Exception {
	pedidoController.buscarPorTodosOsPedidosDisponiveis();
    }

    @Test
    public void buscarPorTodosOsPedidosDisponiveis() throws Exception {
	Set<PedidoItem> itens = Sets
		.newHashSet(new PedidoItem(1L, BigDecimal.valueOf(12.5), 2, new Produto(1L, "1234", "produto1"), null));
	Pedido pedido = new Pedido(1L, new Fornecedor(1L, "1234", "fornecedor1"), itens);
	when(pedidoService.buscarPorTodosOsPedidosDisponiveis()).thenReturn(Lists.newArrayList(pedido));
	ResponseEntity<List<PedidoDto>> responseEntity = pedidoController.buscarPorTodosOsPedidosDisponiveis();
	PedidoDto pedidoDto = responseEntity.getBody().get(0);
	assertNotNull(responseEntity.getBody());
	assertEquals(200, responseEntity.getStatusCodeValue());
	assertEquals("fornecedor1", pedidoDto.getFornecedor().getNome());
	assertEquals(1L, pedidoDto.getId());
	assertEquals(1, pedidoDto.getItens().size());
	assertEquals(BigDecimal.valueOf(25.0), pedidoDto.getItens().get(0).getTotal());
    }

}
