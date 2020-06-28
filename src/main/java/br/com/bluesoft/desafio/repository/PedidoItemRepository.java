package br.com.bluesoft.desafio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bluesoft.desafio.model.PedidoItem;

@Repository
public interface PedidoItemRepository extends CrudRepository<PedidoItem, Long> {

    public default PedidoItem criarNovo(PedidoItem pedidoItem) {
	return save(pedidoItem);
    }

}
