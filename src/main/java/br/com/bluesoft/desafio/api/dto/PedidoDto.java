package br.com.bluesoft.desafio.api.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoItem;
import br.com.bluesoft.desafio.util.Lists;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoDto {

    private Long id;
    private FornecedorDto fornecedor;
    private final List<PedidoItemDto> itens;

    public PedidoDto(Long id, List<PedidoItemDto> itens, FornecedorDto fornecedor) {
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

    public static PedidoDto converte(Pedido pedido) {
	PedidoDto.Builder pedidoFornecedorDtoBuilder = PedidoDto.Builder.novoBuilder().comId(pedido.getId())
		.comFornecedor(pedido.getFornecedor().getRazaoSocial());
	for (PedidoItem pedidoItem : pedido.getItens()) {
	    pedidoFornecedorDtoBuilder.comItem(pedidoItem.getProduto().getNome(), pedidoItem.getQuantidade(),
		    pedidoItem.getValor());
	}
	return pedidoFornecedorDtoBuilder.constroi();
    }

    public static class Builder {

	private Long id;
	private FornecedorDto fornecedor;
	private List<PedidoItemDto> itens = Lists.newArrayList();

	private Builder() {

	}

	public static Builder novoBuilder() {
	    return new Builder();
	}

	public Builder comId(Long id) {
	    this.id = id;
	    return this;
	}

	public Builder comFornecedor(String nomeFornecedor) {
	    fornecedor = FornecedorDto.Builder.novoBuilder().comNome(nomeFornecedor).constroi();
	    return this;
	}

	public Builder comItem(String nomeProduto, Integer quantidade, BigDecimal preco) {
	    itens.add(PedidoItemDto.Builder.novoBuilder().comQuantidade(quantidade).comProduto(nomeProduto)
		    .comPreco(preco).build());
	    return this;
	}

	public PedidoDto constroi() {
	    return new PedidoDto(id, itens, fornecedor);
	}

    }
}
