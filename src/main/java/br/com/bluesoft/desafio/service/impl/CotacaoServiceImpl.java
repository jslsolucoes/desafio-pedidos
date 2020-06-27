package br.com.bluesoft.desafio.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.service.CotacaoService;
import br.com.bluesoft.desafio.service.CotacaoServiceException;
import br.com.bluesoft.desafio.service.impl.model.Cotacao;
import br.com.bluesoft.desafio.util.Lists;
import br.com.bluesoft.desafio.util.Maps;

@Service
public class CotacaoServiceImpl implements CotacaoService {

    private RestTemplate restTemplate;

    public CotacaoServiceImpl() {

    }

    @Autowired
    public CotacaoServiceImpl(RestTemplate restTemplate) {
	this.restTemplate = restTemplate;
    }

    @Override
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 3000))
    public List<Cotacao> realizaCotacoesParaProduto(Produto produto) throws CotacaoServiceException {
	String errorMessageTemplate = "Não foi possível verificar cotações para o produto gtin \"%s\" pois a integração de sistemas falhou. Você pode tentar novamente daqui alguns instantes. Caso o problema persista entre em contato com o setor técnico para averiguação . Detalhes do erro: %s";
	try {
	    Map<String, String> parameters = Maps.of("gtin", produto.getGtin());
	    ResponseEntity<Cotacao[]> responseEntity = restTemplate.getForEntity(
		    "https://egf1amcv33.execute-api.us-east-1.amazonaws.com/dev/produto/{gtin}", Cotacao[].class,
		    parameters);
	    HttpStatus statusCode = responseEntity.getStatusCode();
	    if (statusCode.equals(HttpStatus.OK)) {
		return Lists.newArrayList(responseEntity.getBody());
	    }
	    throw new CotacaoServiceException(
		    String.format(errorMessageTemplate, produto.getGtin(), "Status code não esperado: " + statusCode));
	} catch (Exception exception) {
	    throw new CotacaoServiceException(String.format(errorMessageTemplate, produto.getGtin(),
		    "Erro de comunicação: " + exception.getMessage()));
	}
    }

}