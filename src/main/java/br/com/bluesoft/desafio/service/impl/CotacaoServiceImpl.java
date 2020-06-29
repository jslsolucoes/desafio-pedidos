package br.com.bluesoft.desafio.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.model.bo.Cotacao;
import br.com.bluesoft.desafio.service.CotacaoService;
import br.com.bluesoft.desafio.service.CotacaoServiceException;
import br.com.bluesoft.desafio.service.RestClientService;
import br.com.bluesoft.desafio.service.RestClientServiceException;
import br.com.bluesoft.desafio.service.RestClientServiceResponseEntity;
import br.com.bluesoft.desafio.util.Lists;
import br.com.bluesoft.desafio.util.Maps;

@Service
public class CotacaoServiceImpl implements CotacaoService {

    private RestClientService restClientService;

    public CotacaoServiceImpl() {

    }

    @Autowired
    public CotacaoServiceImpl(RestClientService restClientService) {
	this.restClientService = restClientService;
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 3000))
    private List<Cotacao> realizaCotacoesParaProduto(Produto produto) throws CotacaoServiceException {
	String errorTemplate = "Não foi possível verificar cotações para o produto gtin \"%s\" pois a integração de sistemas falhou. Você pode tentar novamente daqui alguns instantes. Caso o problema persista entre em contato com o setor técnico para averiguação . Detalhes do erro: %s";
	try {
	    Map<String, String> parameters = Maps.of("gtin", produto.getGtin());
	    RestClientServiceResponseEntity<Cotacao[]> responseEntity = restClientService.getForEntity(
		    "https://egf1amcv33.execute-api.us-east-1.amazonaws.com/dev/produto/{gtin}", Cotacao[].class,
		    parameters);
	    Integer statusCode = responseEntity.getStatusCode();
	    if (HttpStatus.OK == HttpStatus.valueOf(statusCode)) {
		return Lists.newArrayList(responseEntity.getBody());
	    }
	    throw new CotacaoServiceException(
		    String.format(errorTemplate, produto.getGtin(), "Status code não esperado: " + statusCode));
	} catch (RestClientServiceException exception) {
	    throw new CotacaoServiceException(
		    String.format(errorTemplate, produto.getGtin(), "Erro de comunicação: " + exception.getMessage()));
	}
    }

    @Override
    public Cotacao melhorCotacao(Produto produto, Integer quantidade) throws CotacaoServiceException {
	return realizaCotacoesParaProduto(produto).stream()
		.filter(cotacao -> cotacao.atendeQuantidadeMinimima(quantidade))
		.sorted((cotacao1, cotacao2) -> menorValor(produto, quantidade, cotacao1, cotacao2)).findFirst()
		.orElseThrow(() -> new CotacaoServiceException(
			"Nenhum fornecedor encontrado para a quantidade solicitada do produto " + produto.getNome()));
    }

    private Integer menorValor(Produto produto, Integer quantidade, Cotacao cotacao1, Cotacao cotacao2)
	    throws RuntimeException {
	try {
	    return cotacao1.getMelhorPreco(produto, quantidade).compareTo(cotacao2.getMelhorPreco(produto, quantidade));
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }
}
