package br.com.bluesoft.desafio.api;

@SuppressWarnings("serial")
public class RecursoNaoEncontradoException extends Exception {

    public RecursoNaoEncontradoException(String mensagem) {
	super(mensagem);
    }

}
