package br.com.bluesoft.desafio.api.response;

public class RequisicaoInvalidaResponse {

    private final String mensagem;

    public RequisicaoInvalidaResponse(final String mensagem) {
	this.mensagem = mensagem;
    }

    public String getMensagem() {
	return mensagem;
    }

}
