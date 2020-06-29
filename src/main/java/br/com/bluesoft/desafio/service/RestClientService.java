package br.com.bluesoft.desafio.service;

import java.util.Map;

public interface RestClientService {

    public <T> RestClientServiceResponseEntity<T> getForEntity(String url, Class<T> responseType,
	    Map<String, ?> uriVariables) throws RestClientServiceException;

}
