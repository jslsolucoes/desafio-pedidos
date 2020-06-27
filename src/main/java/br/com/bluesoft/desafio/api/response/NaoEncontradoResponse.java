package br.com.bluesoft.desafio.api.response;

public class NaoEncontradoResponse {

    private final String mensagem;

    public NaoEncontradoResponse(final String mensagem) {
	this.mensagem = mensagem;
    }

    public String getMensagem() {
	return mensagem;
    }

}
