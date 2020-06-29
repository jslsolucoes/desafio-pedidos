package br.com.bluesoft.desafio.service;

import java.util.List;

import br.com.bluesoft.desafio.model.bo.NovoPedido;

public interface PedidoServiceValidator {

    public List<NovoPedido> validar(List<NovoPedido> novosPedidos) throws PedidoServiceException;

}
