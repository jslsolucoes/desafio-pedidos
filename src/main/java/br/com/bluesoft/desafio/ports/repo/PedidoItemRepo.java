package br.com.bluesoft.desafio.ports.repo;

import java.util.List;

import br.com.bluesoft.desafio.ports.domain.Pedido;
import br.com.bluesoft.desafio.ports.domain.PedidoItem;

public interface PedidoItemRepo {

    public List<PedidoItem> criarItemsPedido(List<PedidoItem> pedidoItems,Pedido pedido);
    
}
