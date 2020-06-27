package br.com.bluesoft.desafio.api.request.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bluesoft.desafio.api.domain.ApiSolicitacaoCompraItemImpl;
import br.com.bluesoft.desafio.ports.domain.SolicitacaoCompraItem;
import br.com.bluesoft.desafio.util.Lists;

public class SolicitaPedidoItemDto {

    private String gtin;
    private Integer quantidade;

    public String getGtin() {
	return gtin;
    }

    public void setGtin(String gtin) {
	this.gtin = gtin;
    }

    public Integer getQuantidade() {
	return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
	this.quantidade = quantidade;
    }

    @Override
    public String toString() {
	return "SolicitaPedidoItemDto [gtin=" + gtin + ", quantidade=" + quantidade + "]";
    }

    public static List<SolicitacaoCompraItem> converte(SolicitaPedidoItemDto[] solicitaPedidoItemDtos) {
	return Lists.newArrayList(solicitaPedidoItemDtos).stream().map(SolicitaPedidoItemDto::converte)
		.collect(Collectors.toList());
    }

    public static SolicitacaoCompraItem converte(SolicitaPedidoItemDto solicitaPedidoItemDto) {
	return new ApiSolicitacaoCompraItemImpl(solicitaPedidoItemDto.getGtin(), solicitaPedidoItemDto.getQuantidade());
    }

}
