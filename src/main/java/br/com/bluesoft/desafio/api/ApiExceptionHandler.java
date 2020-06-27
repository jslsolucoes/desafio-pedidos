package br.com.bluesoft.desafio.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesoft.desafio.api.response.BadRequestResponse;
import br.com.bluesoft.desafio.api.response.InternalServerErrorResponse;
import br.com.bluesoft.desafio.api.response.NotFoundResponse;

@RestController
@ControllerAdvice
public class ApiExceptionHandler {

    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public InternalServerErrorResponse handleInternalServerError(Exception exception) {
	return new InternalServerErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public NotFoundResponse handleNotFound(NotFoundException notFoundException) {
	return new NotFoundResponse(notFoundException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public BadRequestResponse handleBadRequest(BadRequestException badRequestException) {
	return new BadRequestResponse(badRequestException.getMessage());
    }

}
