package br.com.bluesoft.desafio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.service.PedidoService;
import br.com.bluesoft.desafio.service.PedidoServiceException;
import br.com.bluesoft.desafio.service.impl.model.NovoPedido;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;
    private CriadorPedido criadorPedido;

    public PedidoServiceImpl() {

    }

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, CriadorPedido criadorPedido) {
	this.pedidoRepository = pedidoRepository;
	this.criadorPedido = criadorPedido;
    }

    @Override
    public List<Pedido> buscarPorTodosOsPedidosDisponiveis() {
	return pedidoRepository.buscarPorTodosOsPedidosDisponiveis();
    }

    @Override
    public List<Pedido> criarNovosPedidos(List<NovoPedido> novosPedidos) throws PedidoServiceException {
	return criadorPedido.criarNovosPedidos(novosPedidos);
    }

}
