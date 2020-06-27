package br.com.bluesoft.desafio.adapters.repo.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.bluesoft.desafio.ports.domain.Fornecedor;
import br.com.bluesoft.desafio.ports.domain.Id;
import br.com.bluesoft.desafio.ports.domain.Pedido;
import br.com.bluesoft.desafio.ports.domain.PedidoItem;
import br.com.bluesoft.desafio.util.Sets;

@SuppressWarnings("serial")
@Entity
@Table(name = "pedido")
@SequenceGenerator(name = "pedido_sq", initialValue = 1, allocationSize = 1, sequenceName = "pedido_sq")
public class PedidoImpl implements Pedido {

    @javax.persistence.Id
    @GeneratedValue(generator = "pedido_sq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fornecedor")
    public FornecedorImpl fornecedor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
    private Set<PedidoItemImpl> itens;

    public PedidoImpl() {

    }

    public PedidoImpl(Long id) {
	this(id, null, null);
    }

    public PedidoImpl(FornecedorImpl fornecedor) {
	this(null, fornecedor, null);
    }

    public PedidoImpl(Long id, FornecedorImpl fornecedor, Set<PedidoItemImpl> itens) {
	this.id = id;
	this.fornecedor = fornecedor;
	this.itens = itens;
    }

    @Override
    public Id getId() {
	return new Id(id);
    }

    @Override
    public Fornecedor getFornecedor() {
	return fornecedor;
    }

    public List<PedidoItem> getItens() {
	return Sets.newHashSet(itens).stream().map(pedidoItemImpl -> new PedidoItem(pedidoItemImpl.getValor(),
		pedidoItemImpl.getQuantidade(), pedidoItemImpl.getProduto())).collect(Collectors.toList());
    }
}
