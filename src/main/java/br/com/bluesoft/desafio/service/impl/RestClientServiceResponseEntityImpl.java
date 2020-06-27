package br.com.bluesoft.desafio.service.impl;

import org.springframework.http.ResponseEntity;

import br.com.bluesoft.desafio.service.RestClientServiceResponseEntity;

public class RestClientServiceResponseEntityImpl<T> implements RestClientServiceResponseEntity<T> {

    private ResponseEntity<T> responseEntity;

    public RestClientServiceResponseEntityImpl(ResponseEntity<T> responseEntity) {
	this.responseEntity = responseEntity;
    }

    @Override
    public int getStatusCode() {
	return responseEntity.getStatusCode().value();
    }

    @Override
    public T getBody() {
	return responseEntity.getBody();
    }

}
