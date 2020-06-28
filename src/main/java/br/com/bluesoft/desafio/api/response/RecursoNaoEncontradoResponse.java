package br.com.bluesoft.desafio.api.response;

public class RecursoNaoEncontradoResponse {

    private final String mensagem;

    public RecursoNaoEncontradoResponse(final String mensagem) {
	this.mensagem = mensagem;
    }

    public String getMensagem() {
	return mensagem;
    }

}
