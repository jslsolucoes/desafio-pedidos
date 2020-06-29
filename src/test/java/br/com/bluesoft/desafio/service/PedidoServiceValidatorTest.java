package br.com.bluesoft.desafio.service;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.model.bo.NovoPedido;
import br.com.bluesoft.desafio.test.AbstractTest;
import br.com.bluesoft.desafio.util.Lists;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PedidoServiceValidatorTest extends AbstractTest {

    @Autowired
    private PedidoServiceValidator pedidoServiceValidator;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void semProdutos() throws PedidoServiceException {
	expectedException.expectMessage("Ao menos um produto deve ser solicitado para a criação de um novo pedido");
	expectedException.expect(PedidoServiceException.class);
	pedidoServiceValidator.validar(Lists.newArrayList());
    }
    
    @Test
    public void comTodosOsProdutosComQuantidadeZerada() throws PedidoServiceException {
	expectedException.expectMessage("Ao menos um produto deve ser solicitado para a criação de um novo pedido");
	expectedException.expect(PedidoServiceException.class);
	pedidoServiceValidator.validar(Lists.newArrayList(new NovoPedido(null, 0),new NovoPedido(null, 0),new NovoPedido(null, 0)));
    }
    
    @Test
    public void validarComSucesso() throws PedidoServiceException {
	List<NovoPedido> novosPedidos = pedidoServiceValidator.validar(Lists.newArrayList(new NovoPedido(null, 0),new NovoPedido(null, 1)));
	assertNotNull(novosPedidos);
	assertEquals(1,novosPedidos.size());
    }

}
