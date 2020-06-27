package br.com.bluesoft.desafio.api.response;

public class ErroInternoServidorResponse {

    private final String mensagem;

    public ErroInternoServidorResponse(final String mensagem) {
	this.mensagem = mensagem;
    }

    public String getMensagem() {
	return mensagem;
    }
}
