package br.com.bluesoft.desafio.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;

import br.com.bluesoft.desafio.util.Sets;

@SuppressWarnings("serial")
@Entity
@Table(name = "pedido")
@SequenceGenerator(name = "pedido_sq", initialValue = 1, allocationSize = 1, sequenceName = "pedido_sq")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(generator = "pedido_sq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @NotNull
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fornecedor")
    public Fornecedor fornecedor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
    @BatchSize(size = 50)
    private Set<PedidoItem> itens;

    public Pedido() {

    }

    public Pedido(Long id, Fornecedor fornecedor, Set<PedidoItem> itens) {
	this.id = id;
	this.fornecedor = fornecedor;
	this.itens = itens;
    }

    public Long getId() {
	return id;
    }

    public Fornecedor getFornecedor() {
	return fornecedor;
    }

    public Pedido setFornecedor(Fornecedor fornecedor) {
	this.fornecedor = fornecedor;
	return this;
    }

    public Set<PedidoItem> getItens() {
	return itens;
    }
    
    public Pedido addItem(PedidoItem pedidoItem) {
	itens = Sets.newHashMutableSet(itens).addItem(pedidoItem);
	return this;
    }

}
