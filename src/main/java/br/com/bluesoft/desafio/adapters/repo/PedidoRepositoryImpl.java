package br.com.bluesoft.desafio.adapters.repo;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.bluesoft.desafio.adapters.repo.domain.FornecedorImpl;
import br.com.bluesoft.desafio.adapters.repo.domain.PedidoImpl;
import br.com.bluesoft.desafio.ports.domain.Pedido;
import br.com.bluesoft.desafio.ports.repo.PedidoRepository;
import br.com.bluesoft.desafio.util.Lists;

@Repository
public class PedidoRepositoryImpl extends CrudRepositoryImpl<PedidoImpl, Long> implements PedidoRepository {

    @Autowired
    public PedidoRepositoryImpl(EntityManager entityManager) {
	super(entityManager);
    }

    @Override
    public List<Pedido> buscarPorTodosOsPedidos() {
	String query = "select ped from PedidoImpl ped inner join fetch ped.fornecedor for order by ped.id";
	return Lists.cast(entityManager.createQuery(query, PedidoImpl.class).getResultList());
    }

    @Override
    public List<Pedido> salvarNovosPedidos(List<Pedido> pedidos) {
	return Lists.cast(pedidos.stream().map(pedido -> save(converter(pedido))).collect(Collectors.toList()));
    }

    private PedidoImpl converter(Pedido pedido) {
	return new PedidoImpl(new FornecedorImpl(pedido.getFornecedor().getId().getValue()));
    }

}
