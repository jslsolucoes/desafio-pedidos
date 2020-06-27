package br.com.bluesoft.desafio.api;

@SuppressWarnings("serial")
public class BadRequestException extends Exception {

    public BadRequestException(String message) {
	super(message);
    }
}
