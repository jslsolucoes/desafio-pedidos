package br.com.bluesoft.desafio.adapters.repo;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.bluesoft.desafio.adapters.repo.domain.PedidoImpl;
import br.com.bluesoft.desafio.adapters.repo.domain.PedidoItemImpl;
import br.com.bluesoft.desafio.adapters.repo.domain.ProdutoImpl;
import br.com.bluesoft.desafio.ports.domain.Pedido;
import br.com.bluesoft.desafio.ports.domain.PedidoItem;
import br.com.bluesoft.desafio.ports.repo.PedidoItemRepo;
import br.com.bluesoft.desafio.util.Lists;

@Repository
public class PedidoItemRepoImpl extends CrudRepositoryImpl<PedidoItemImpl, Long> implements PedidoItemRepo {

    @Autowired
    public PedidoItemRepoImpl(EntityManager entityManager) {
	super(entityManager);
    }

    @Override
    public List<PedidoItem> criarItemsPedido(List<PedidoItem> pedidoItems, Pedido pedido) {
	return Lists.cast(Lists.newArrayList(save(pedidoItems.stream().map(pedidoItem -> converter(pedidoItem, pedido)).collect(Collectors.toList()))));
    }

    private PedidoItemImpl converter(PedidoItem pedidoItem, Pedido pedido) {
	return new PedidoItemImpl(pedidoItem.getValor().getValor(), pedidoItem.getQuantidade(),
		new ProdutoImpl(pedidoItem.getProduto().getId().getValue()), new PedidoImpl(pedido.getId().getValue()));
    }

}
