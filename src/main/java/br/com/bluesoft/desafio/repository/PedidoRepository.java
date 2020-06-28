package br.com.bluesoft.desafio.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.util.Lists;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

    @Query("select ped from Pedido ped inner join fetch ped.fornecedor for order by ped.id desc")
    public List<Pedido> buscarPorTodosOsPedidosDisponiveis();

    public default List<Pedido> criarNovosPedidos(Collection<Pedido> pedidos) {
	return Lists.newArrayList(save(pedidos));
    }

}
