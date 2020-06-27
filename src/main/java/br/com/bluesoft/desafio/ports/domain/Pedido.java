package br.com.bluesoft.desafio.ports.domain;

import java.util.List;

public interface Pedido extends Entity<Id> {

    public Fornecedor getFornecedor();

    public List<PedidoItem> getItens();

}
