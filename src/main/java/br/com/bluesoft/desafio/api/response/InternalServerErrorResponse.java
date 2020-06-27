package br.com.bluesoft.desafio.api.response;

public class InternalServerErrorResponse {

    private final String error;

    public InternalServerErrorResponse(final String error) {
	this.error = error;
    }

    public String getMessage() {
	return error;
    }
}
