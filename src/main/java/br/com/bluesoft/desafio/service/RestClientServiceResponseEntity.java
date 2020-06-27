package br.com.bluesoft.desafio.service;

public interface RestClientServiceResponseEntity<T> {

    public int getStatusCode();

    public T getBody();

}
