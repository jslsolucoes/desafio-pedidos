package br.com.bluesoft.desafio.api.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoItemDto {

    private ProdutoDto produto;
    private Integer quantidade;
    private BigDecimal total;
    private BigDecimal preco;

    public PedidoItemDto(ProdutoDto produto, Integer quantidade, BigDecimal preco) {
	this.produto = produto;
	this.quantidade = quantidade;
	this.total = total(quantidade, preco);
	this.preco = preco;
    }

    private BigDecimal total(Integer quantidade, BigDecimal preco) {
	return preco.multiply(BigDecimal.valueOf(quantidade));
    }

    public ProdutoDto getProduto() {
	return produto;
    }

    public void setProduto(ProdutoDto produto) {
	this.produto = produto;
    }

    public Integer getQuantidade() {
	return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
	this.quantidade = quantidade;
    }

    public BigDecimal getTotal() {
	return total;
    }

    public BigDecimal getPreco() {
	return preco;
    }

    public void setPreco(BigDecimal preco) {
	this.preco = preco;
    }

    public static class Builder {

	private Integer quantidade;
	private BigDecimal preco;
	private ProdutoDto produto;

	private Builder() {

	}

	public static Builder novoBuilder() {
	    return new Builder();
	}

	public Builder comQuantidade(Integer quantidade) {
	    this.quantidade = quantidade;
	    return this;
	}

	public Builder comPreco(BigDecimal preco) {
	    this.preco = preco;
	    return this;
	}

	public Builder comProduto(String nomeProduto) {
	    produto = ProdutoDto.Builder.novoBuilder().comNome(nomeProduto).build();
	    return this;
	}

	public PedidoItemDto build() {
	    return new PedidoItemDto(produto, quantidade, preco);
	}
    }

}
