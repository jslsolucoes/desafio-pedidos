package br.com.bluesoft.desafio.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.bluesoft.desafio.service.RestClientServiceResponseEntity;
import br.com.bluesoft.desafio.service.RestClientService;
import br.com.bluesoft.desafio.service.RestClientServiceException;

@Service
public class RestClientServiceImpl implements RestClientService {

    private RestTemplate restTemplate;

    public RestClientServiceImpl() {

    }

    @Autowired
    public RestClientServiceImpl(RestTemplate restTemplate) {
	this.restTemplate = restTemplate;
    }

    @Override
    public <T> RestClientServiceResponseEntity<T> getForEntity(String url, Class<T> responseType,
	    Map<String, ?> uriVariables) throws RestClientServiceException {
	try {
	    return new RestClientServiceResponseEntityImpl<T>(
		    restTemplate.getForEntity(url, responseType, uriVariables));
	} catch (RestClientException e) {
	    throw new RestClientServiceException(e);
	}
    }

}
