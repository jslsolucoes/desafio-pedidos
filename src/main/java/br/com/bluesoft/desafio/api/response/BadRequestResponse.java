package br.com.bluesoft.desafio.api.response;

public class BadRequestResponse {

    private final String mensagem;

    public BadRequestResponse(final String mensagem) {
	this.mensagem = mensagem;
    }

    public String getMensagem() {
	return mensagem;
    }

}
