package br.com.bluesoft.desafio.service;

@SuppressWarnings("serial")
public class ServiceRuntimeException extends RuntimeException {

    public ServiceRuntimeException(String mensagem) {
	super(mensagem);
    }
}
