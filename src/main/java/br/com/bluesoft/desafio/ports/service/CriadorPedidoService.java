package br.com.bluesoft.desafio.ports.service;

import java.util.List;

import br.com.bluesoft.desafio.ports.domain.Pedido;
import br.com.bluesoft.desafio.ports.domain.SolicitacaoCompraItem;

public interface CriadorPedidoService {

    public List<Pedido> criarNovosPedidos(List<SolicitacaoCompraItem> solicitacaoCompraItems)
	    throws CriadorPedidoException;

}
