package br.com.bluesoft.desafio.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesoft.desafio.api.RecursoNaoEncontradoException;
import br.com.bluesoft.desafio.api.RequisicaoInvalidaException;
import br.com.bluesoft.desafio.api.response.ErroInternoServidorResponse;
import br.com.bluesoft.desafio.api.response.RecursoNaoEncontradoResponse;
import br.com.bluesoft.desafio.api.response.RequisicaoInvalidaResponse;

@RestController
@ControllerAdvice
public class TratatamentoErroController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErroInternoServidorResponse trataErroInterno(Exception exception) {
	return new ErroInternoServidorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public RecursoNaoEncontradoResponse trataRecursoNaoEncontrado(
	    RecursoNaoEncontradoException naoEncontradoException) {
	return new RecursoNaoEncontradoResponse(naoEncontradoException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RequisicaoInvalidaException.class)
    public RequisicaoInvalidaResponse trataRequisicaoInvalida(RequisicaoInvalidaException requisicaoInvalidaException) {
	return new RequisicaoInvalidaResponse(requisicaoInvalidaException.getMessage());
    }

}
