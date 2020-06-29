package br.com.bluesoft.desafio.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.junit.AbstractTest;
import br.com.bluesoft.desafio.repository.PedidoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PedidoServiceTest extends AbstractTest {

    @Autowired
    private PedidoService pedidoService;

    @MockBean
    private PedidoRepository pedidoRepository;

    @MockBean
    private CriadorPedidoService criadorPedidoService;

    @Test
    public void buscarPorTodosOsPedidosDisponiveis() {
	pedidoService.buscarPorTodosOsPedidosDisponiveis();
	verify(pedidoRepository, times(1)).buscarPorTodosOsPedidosDisponiveis();
    }

    @Test
    public void criarNovosPedidos() throws PedidoServiceException {
	pedidoService.criarNovosPedidos(any());
	verify(criadorPedidoService, times(1)).criarNovosPedidos(any());
    }

}
