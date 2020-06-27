package br.com.bluesoft.desafio.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesoft.desafio.api.RecursoNaoEncontradoException;
import br.com.bluesoft.desafio.api.RequisicaoInvalidaException;
import br.com.bluesoft.desafio.api.dto.NovoPedidoDto;
import br.com.bluesoft.desafio.api.dto.PedidoDto;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.service.PedidoException;
import br.com.bluesoft.desafio.service.PedidoService;
import br.com.bluesoft.desafio.service.impl.model.NovoPedido;
import br.com.bluesoft.desafio.util.Lists;
import br.com.bluesoft.desafio.util.Responses;

@RestController
@RequestMapping("/api/v1")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController() {

    }

    @Autowired
    public PedidoController(PedidoService pedidoService) {
	this.pedidoService = pedidoService;
    }

    @GetMapping(value = "/pedidos")
    public ResponseEntity<List<PedidoDto>> buscarPorTodosOsPedidosDisponiveis() throws RecursoNaoEncontradoException {
	List<Pedido> pedidos = pedidoService.buscarPorTodosOsPedidosDisponiveis();
	if (Lists.isEmpty(pedidos)) {
	    throw new RecursoNaoEncontradoException("Não foi possível encontrar nenhum pedido cadastrado");
	} else {
	    return Responses.ok(Lists.transform(pedidos, PedidoDto::converte));
	}
    }

    @PostMapping(value = "/pedidos")
    public ResponseEntity<List<PedidoDto>> criarNovosPedidos(@RequestBody NovoPedidoDto[] novosPedidosDto)
	    throws RequisicaoInvalidaException {
	try {
	    List<NovoPedido> novosPedidos = Lists.transform(Lists.newArrayList(novosPedidosDto),
		    NovoPedidoDto::converter);
	    return Responses.ok(Lists.transform(pedidoService.criarNovosPedidos(novosPedidos), PedidoDto::converte));
	} catch (PedidoException pedidoException) {
	    throw new RequisicaoInvalidaException(pedidoException.getMessage());
	}

    }

}