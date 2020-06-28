package br.com.bluesoft.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bluesoft.desafio.model.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

    @Query("select ped from Pedido ped inner join fetch ped.fornecedor for order by ped.id desc")
    public List<Pedido> buscarPorTodosOsPedidosDisponiveis();

    public default Pedido criarNovo(Pedido pedido) {
	return save(pedido);
    }

}
