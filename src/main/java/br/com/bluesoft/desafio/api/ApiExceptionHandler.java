package br.com.bluesoft.desafio.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesoft.desafio.api.response.RequisicaoInvalidaResponse;
import br.com.bluesoft.desafio.api.response.ErroInternoServidorResponse;
import br.com.bluesoft.desafio.api.response.NaoEncontradoResponse;

@RestController
@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErroInternoServidorResponse handleInternalServerError(Exception exception) {
	return new ErroInternoServidorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NaoEncontradoException.class)
    public NaoEncontradoResponse handleNotFound(NaoEncontradoException naoEncontradoException) {
	return new NaoEncontradoResponse(naoEncontradoException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RequisicaoInvalidaException.class)
    public RequisicaoInvalidaResponse handleBadRequest(RequisicaoInvalidaException requisicaoInvalidaException) {
	return new RequisicaoInvalidaResponse(requisicaoInvalidaException.getMessage());
    }

}
