package br.com.bluesoft.desafio.api;

@SuppressWarnings("serial")
public class RequisicaoInvalidaException extends Exception {

    public RequisicaoInvalidaException(String mensagem) {
	super(mensagem);
    }
}
