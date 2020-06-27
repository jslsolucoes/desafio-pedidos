package br.com.bluesoft.desafio.api.response;

public class NotFoundResponse {

    private final String mensagem;

    public NotFoundResponse(final String mensagem) {
	this.mensagem = mensagem;
    }

    public String getMensagem() {
	return mensagem;
    }

}
