package br.com.bluesoft.desafio.ports.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.desafio.ports.domain.Pedido;
import br.com.bluesoft.desafio.ports.domain.SolicitacaoCompraItem;
import br.com.bluesoft.desafio.ports.repo.PedidoRepository;
import br.com.bluesoft.desafio.ports.service.CriadorPedidoException;
import br.com.bluesoft.desafio.ports.service.CriadorPedidoService;
import br.com.bluesoft.desafio.ports.service.PedidoException;
import br.com.bluesoft.desafio.ports.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;
    private CriadorPedidoService criadorPedidoService;

    public PedidoServiceImpl() {

    }

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository,CriadorPedidoService criadorPedidoService) {
	this.pedidoRepository = pedidoRepository;
	this.criadorPedidoService = criadorPedidoService;
    }

    @Override
    public List<Pedido> listarTodosOsPedidosDisponiveis() {
	return pedidoRepository.buscarPorTodosOsPedidos();
    }

    @Override
    @Transactional
    public List<Pedido> criarNovosPedidos(List<SolicitacaoCompraItem> solicitacaoCompraItems) throws PedidoException {
	try {
	    return pedidoRepository.salvarNovosPedidos(criadorPedidoService.criarNovosPedidos(solicitacaoCompraItems));
	} catch (CriadorPedidoException criadorPedidoException) {
	    throw new PedidoException(criadorPedidoException.getMessage());
	}
    }

}
