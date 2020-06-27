package br.com.bluesoft.desafio.adapters.repo.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pedido_item")
@SequenceGenerator(name = "pedido_item_sq", initialValue = 1, allocationSize = 1, sequenceName = "pedido_item_sq")
public class PedidoItemImpl {

    @javax.persistence.Id
    @GeneratedValue(generator = "pedido_item_sq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @NotNull
    public Long id;

    @Column(name = "valor")
    @NotNull
    public BigDecimal valor;

    @Column(name = "quantidade")
    @NotNull
    public Integer quantidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_produto")
    public ProdutoImpl produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido")
    private PedidoImpl pedido;
    
    public PedidoItemImpl() {
	
    }
    
    public PedidoItemImpl(BigDecimal valor, Integer quantidade, ProdutoImpl produto, PedidoImpl pedido) {
	this(null, valor, quantidade, produto, pedido);
    }
    
    public PedidoItemImpl(Long id, BigDecimal valor, Integer quantidade, ProdutoImpl produto, PedidoImpl pedido) {
	this.id = id;
	this.valor = valor;
	this.quantidade = quantidade;
	this.produto = produto;
	this.pedido = pedido;
    }

    public Long getId() {
	return id;
    }

    public BigDecimal getValor() {
	return valor;
    }

    public void setValor(BigDecimal valor) {
	this.valor = valor;
    }

    public Integer getQuantidade() {
	return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
	this.quantidade = quantidade;
    }

    public ProdutoImpl getProduto() {
	return produto;
    }

    public void setProduto(ProdutoImpl produto) {
	this.produto = produto;
    }

    public PedidoImpl getPedido() {
	return pedido;
    }

    public void setPedido(PedidoImpl pedido) {
	this.pedido = pedido;
    }

}
