package br.com.bluesoft.desafio.api;

@SuppressWarnings("serial")
public class NotFoundException extends Exception {

    public NotFoundException(String message) {
	super(message);
    }

}
