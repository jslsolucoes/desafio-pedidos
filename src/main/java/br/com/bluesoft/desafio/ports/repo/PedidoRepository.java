package br.com.bluesoft.desafio.ports.repo;

import java.util.List;

import br.com.bluesoft.desafio.ports.domain.Pedido;

public interface PedidoRepository {

    public List<Pedido> buscarPorTodosOsPedidos();

    public List<Pedido> salvarNovosPedidos(List<Pedido> pedidos);

}
