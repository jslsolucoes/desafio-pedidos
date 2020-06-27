package br.com.bluesoft.desafio.api;

@SuppressWarnings("serial")
public class NaoEncontradoException extends Exception {

    public NaoEncontradoException(String mensagem) {
	super(mensagem);
    }

}
