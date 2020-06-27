package br.com.bluesoft.desafio.service;

@SuppressWarnings("serial")
public class RestClientServiceException extends Exception {

    public RestClientServiceException(Throwable throwable) {
	super(throwable);
    }
}
