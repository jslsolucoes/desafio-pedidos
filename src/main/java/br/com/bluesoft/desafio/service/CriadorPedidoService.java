package br.com.bluesoft.desafio.service;

import java.util.List;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.bo.NovoPedido;

public interface CriadorPedidoService {

    public List<Pedido> criarNovosPedidos(List<NovoPedido> novosPedidos) throws PedidoServiceException;

}
