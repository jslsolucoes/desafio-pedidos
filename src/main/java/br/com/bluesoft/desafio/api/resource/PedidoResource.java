package br.com.bluesoft.desafio.api.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesoft.desafio.api.BadRequestException;
import br.com.bluesoft.desafio.api.request.dto.SolicitaPedidoItemDto;
import br.com.bluesoft.desafio.api.response.dto.PedidoFornecedorDto;
import br.com.bluesoft.desafio.ports.domain.Pedido;
import br.com.bluesoft.desafio.ports.service.PedidoException;
import br.com.bluesoft.desafio.ports.service.PedidoService;
import br.com.bluesoft.desafio.util.Lists;
import br.com.bluesoft.desafio.util.Responses;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/v1")
public class PedidoResource {

    private PedidoService pedidoService;

    public PedidoResource() {

    }

    @Autowired
    public PedidoResource(PedidoService pedidoService) {
	this.pedidoService = pedidoService;
    }

    @GetMapping(value = "/pedidos")
    public ResponseEntity<List<PedidoFornecedorDto>> listarPedidos() throws NotFoundException {
	List<Pedido> pedidos = pedidoService.listarTodosOsPedidosDisponiveis();
	if (Lists.isEmpty(pedidos)) {
	    throw new NotFoundException("Não foi possível encontrar nenhum pedido cadastrado");
	} else {
	    return Responses.ok(pedidos.stream().map(PedidoFornecedorDto::converte).collect(Collectors.toList()));
	}
    }

    @PostMapping(value = "/pedidos")
    public ResponseEntity<List<PedidoFornecedorDto>> criarNovosPedidos(@RequestBody SolicitaPedidoItemDto[] itens)
	    throws BadRequestException {
	try {
	    return Responses.ok(pedidoService.criarNovosPedidos(SolicitaPedidoItemDto.converte(itens)).stream()
		    .map(PedidoFornecedorDto::converte).collect(Collectors.toList()));
	} catch (PedidoException pedidoException) {
	    throw new BadRequestException(pedidoException.getMessage());
	}
    }
}