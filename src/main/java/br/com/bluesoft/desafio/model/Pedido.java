package br.com.bluesoft.desafio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.PERSIST)
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


    public void setItens(Set<PedidoItem> itens) {
	this.itens = itens;
    }

    public static class Builder {

	private Fornecedor fornecedor;
	private Set<PedidoItem> itens = Sets.newHashSet();

	private Builder() {

	}

	public static Builder novoBuilder() {
	    return new Builder();
	}
	
	public Builder comItem(Produto produto,Integer quantidade,BigDecimal valor) {
	    return comItem(PedidoItem.Builder.novoBuilder().comProduto(produto).comQuantidade(quantidade)
		    	.comValor(valor).constroi());
	}
	
	public Builder comItem(PedidoItem pedidoItem) {
	    this.itens.add(pedidoItem);
	    return this;
	}

	public Builder comFornecedor(Fornecedor fornecedor) {
	    this.fornecedor = fornecedor;
	    return this;
	}

	public Pedido constroi() {
	    return new Pedido(null, fornecedor, itens);
	}

	public Builder comItens(Collection<PedidoItem> itens) {
	    this.itens = new HashSet<>(itens);
	    return this;
	}
    }

}
