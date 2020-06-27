package br.com.bluesoft.desafio.api.response.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.bluesoft.desafio.ports.domain.Pedido;
import br.com.bluesoft.desafio.ports.domain.PedidoItem;
import br.com.bluesoft.desafio.util.Lists;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoFornecedorDto {

    private Long id;
    private FornecedorDto fornecedor;
    private final List<PedidoItemDto> itens;

    public PedidoFornecedorDto(Long id, List<PedidoItemDto> itens, FornecedorDto fornecedor) {
	this.id = id;
	this.itens = itens;
	this.fornecedor = fornecedor;
    }

    public List<PedidoItemDto> getItens() {
	return itens;
    }

    public FornecedorDto getFornecedor() {
	return fornecedor;
    }

    public void setFornecedor(FornecedorDto fornecedor) {
	this.fornecedor = fornecedor;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public static PedidoFornecedorDto converte(Pedido pedido) {
	PedidoFornecedorDto.Builder pedidoFornecedorDtoBuilder = PedidoFornecedorDto.Builder.newBuilder()
		.withId(pedido.getId().getValue()).withFornecedor(pedido.getFornecedor().getRazaoSocial().getValue());

	for (PedidoItem pedidoItem : pedido.getItens()) {
	    pedidoFornecedorDtoBuilder.withItem(pedidoItem.getProduto().getNome(), pedidoItem.getQuantidade(),
		    pedidoItem.getValor().getValor());
	}

	return pedidoFornecedorDtoBuilder.build();
    }

    public static class Builder {

	private Long id;
	private FornecedorDto fornecedor;
	private List<PedidoItemDto> itens = Lists.newArrayList();

	private Builder() {

	}

	public static Builder newBuilder() {
	    return new Builder();
	}

	public Builder withId(Long id) {
	    this.id = id;
	    return this;
	}

	public Builder withFornecedor(String nomeFornecedor) {
	    fornecedor = FornecedorDto.Builder.newBuilder().withNome(nomeFornecedor).build();
	    return this;
	}

	public Builder withItem(String nomeProduto, Integer quantidade, BigDecimal preco) {
	    itens.add(PedidoItemDto.Builder.newBuilder().withQuantidade(quantidade).withProduto(nomeProduto)
		    .withPreco(preco).build());
	    return this;
	}

	public PedidoFornecedorDto build() {
	    return new PedidoFornecedorDto(id, itens, fornecedor);
	}

    }
}
