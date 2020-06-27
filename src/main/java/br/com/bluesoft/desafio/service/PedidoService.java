package br.com.bluesoft.desafio.service;

import java.util.List;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.service.impl.model.NovoPedido;

public interface PedidoService {

    public List<Pedido> buscarPorTodosOsPedidosDisponiveis();

    public List<Pedido> criarNovosPedidos(List<NovoPedido> novosPedidos) throws PedidoException;

}
