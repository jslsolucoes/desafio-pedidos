package br.com.bluesoft.desafio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.bo.NovoPedido;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.service.CriadorPedidoService;
import br.com.bluesoft.desafio.service.PedidoService;
import br.com.bluesoft.desafio.service.PedidoServiceException;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;
    private CriadorPedidoService criadorPedidoService;

    public PedidoServiceImpl() {

    }

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, CriadorPedidoService criadorPedidoService) {
	this.pedidoRepository = pedidoRepository;
	this.criadorPedidoService = criadorPedidoService;
    }

    @Override
    public List<Pedido> buscarPorTodosOsPedidosDisponiveis() {
	return pedidoRepository.buscarPorTodosOsPedidosDisponiveis();
    }

    @Override
    public List<Pedido> criarNovosPedidos(List<NovoPedido> novosPedidos) throws PedidoServiceException {
	return criadorPedidoService.criarNovosPedidos(novosPedidos);
    }

}
