package br.com.bluesoft.desafio.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.model.bo.NovoPedido;
import br.com.bluesoft.desafio.service.PedidoServiceException;
import br.com.bluesoft.desafio.service.PedidoServiceValidator;
import br.com.bluesoft.desafio.util.Lists;

@Service
public class PedidoServiceValidatorImpl implements PedidoServiceValidator {

    @Override
    public List<NovoPedido> validar(List<NovoPedido> novosPedidos) throws PedidoServiceException {
	if (Lists.allMatch(novosPedidos, novoPedido -> novoPedido.getQuantidade() == 0)) {
	    throw new PedidoServiceException(
		    "Ao menos um produto deve ser solicitado para a criação de um novo pedido");
	}
	return Lists.filter(novosPedidos, novoPedido -> novoPedido.getQuantidade() > 0);
    }

}