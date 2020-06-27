package br.com.bluesoft.desafio.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "pedido_item")
@SequenceGenerator(name = "pedido_item_sq", initialValue = 1, allocationSize = 1, sequenceName = "pedido_item_sq")
public class PedidoItem implements Serializable {

    @Id
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
    public Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    public PedidoItem() {

    }

    public PedidoItem(Long id, BigDecimal valor, Integer quantidade, Produto produto, Pedido pedido) {
	this.id = id;
	this.valor = valor;
	this.quantidade = quantidade;
	this.produto = produto;
	this.pedido = pedido;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
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

    public Produto getProduto() {
	return produto;
    }

    public void setProduto(Produto produto) {
	this.produto = produto;
    }

    public Pedido getPedido() {
	return pedido;
    }

    public void setPedido(Pedido pedido) {
	this.pedido = pedido;
    }

}
