package br.com.bluesoft.desafio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bluesoft.desafio.model.PedidoItem;
import br.com.bluesoft.desafio.util.Lists;

@Repository
public interface PedidoItemRepository extends CrudRepository<PedidoItem, Long> {

    public default List<PedidoItem> criarNovosItensDePedido(List<PedidoItem> pedidoItens) {
	return Lists.newArrayList(save(pedidoItens));
    }

}
