package br.com.bluesoft.desafio.ports.service;

import java.util.List;

import br.com.bluesoft.desafio.ports.domain.Pedido;
import br.com.bluesoft.desafio.ports.domain.SolicitacaoCompraItem;

public interface PedidoService {

    public List<Pedido> listarTodosOsPedidosDisponiveis();

    public List<Pedido> criarNovosPedidos(List<SolicitacaoCompraItem> solicitacaoCompraItems) throws PedidoException;

}
