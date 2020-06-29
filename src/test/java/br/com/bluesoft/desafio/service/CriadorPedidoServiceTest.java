package br.com.bluesoft.desafio.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.junit.AbstractTest;
import br.com.bluesoft.desafio.model.bo.NovoPedido;
import br.com.bluesoft.desafio.util.Lists;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CriadorPedidoServiceTest extends AbstractTest {

    @Autowired
    private CriadorPedidoService criadorPedidoService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void semProdutos() throws PedidoServiceException {
	expectedException.expectMessage("Ao menos um produto deve ser solicitado para a criação de um novo pedido");
	expectedException.expect(PedidoServiceException.class);
	criadorPedidoService.criarNovosPedidos(Lists.newArrayList());
    }

    @Test
    public void comTodosOsProdutosComQuantidadeZerada() throws PedidoServiceException {
	expectedException.expectMessage("Ao menos um produto deve ser solicitado para a criação de um novo pedido");
	expectedException.expect(PedidoServiceException.class);
	criadorPedidoService.criarNovosPedidos(
		Lists.newArrayList(new NovoPedido(null, 0), new NovoPedido(null, 0), new NovoPedido(null, 0)));
    }

}
