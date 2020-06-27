package br.com.bluesoft.desafio.ports.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.ports.domain.Pedido;
import br.com.bluesoft.desafio.ports.domain.PedidoItem;
import br.com.bluesoft.desafio.ports.repo.PedidoItemRepo;
import br.com.bluesoft.desafio.ports.service.PedidoItemService;

@Service
public class PedidoItemServiceImpl implements PedidoItemService {

    private PedidoItemRepo pedidoItemRepo;

    public PedidoItemServiceImpl() {

    }

    @Autowired
    public PedidoItemServiceImpl(PedidoItemRepo pedidoItemRepo) {
	this.pedidoItemRepo = pedidoItemRepo;
    }

    @Override
    public List<PedidoItem> criarItemsPedido(List<PedidoItem> pedidoItems, Pedido pedido) {
	return pedidoItemRepo.criarItemsPedido(pedidoItems, pedido);
    }

}
