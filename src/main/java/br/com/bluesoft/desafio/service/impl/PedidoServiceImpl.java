package br.com.bluesoft.desafio.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.service.PedidoException;
import br.com.bluesoft.desafio.service.PedidoService;
import br.com.bluesoft.desafio.service.impl.model.NovoPedido;
import br.com.bluesoft.desafio.util.Sets;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoServiceImpl() {

    }

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
	this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> buscarPorTodosOsPedidosDisponiveis() {
	return pedidoRepository.buscarPorTodosOsPedidosDisponiveis();
    }

    @Override
    public List<Pedido> criarNovosPedidos(List<NovoPedido> novosPedidos) throws PedidoException {
	return Arrays.asList(new Pedido(1L, new Fornecedor(1L, "1221", "teste"), Sets.newHashSet()),
		new Pedido(2L, new Fornecedor(1L, "1221ds", "teste"), Sets.newHashSet()));
    }

}
