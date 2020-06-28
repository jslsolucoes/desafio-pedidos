package br.com.bluesoft.desafio.service;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.model.bo.Cotacao;
import br.com.bluesoft.desafio.model.bo.CotacaoPreco;
import br.com.bluesoft.desafio.test.AbstractTest;
import br.com.bluesoft.desafio.util.Lists;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableRetry
public class CotacaoServiceTest extends AbstractTest {

    @Autowired
    private CotacaoService cotacaoService;

    @MockBean
    private RestClientService restClientService;

    @Test(expected = CotacaoServiceException.class)
    public void forcarRetryComProblemaDeComunicacao() throws CotacaoServiceException, RestClientServiceException {
	when(restClientService.getForEntity(anyString(), any(), anyMap()))
		.thenThrow(new RestClientServiceException(new Exception("some exception")));
	cotacaoService.realizaCotacoesParaProduto(new Produto(null, "1234", null));
    }

    @Test(expected = CotacaoServiceException.class)
    public void forcarRetryComProblemaDeStatusCode() throws CotacaoServiceException, RestClientServiceException {
	when(restClientService.getForEntity(anyString(), any(), anyMap()))
		.thenReturn(new BadRequestRestClientServiceResponseEntity());
	cotacaoService.realizaCotacoesParaProduto(new Produto(null, "1234", null));
    }

    @Test
    public void cotacaoDeveSerRealizadaNormalmente() throws CotacaoServiceException, RestClientServiceException {
	Cotacao cotacao = new Cotacao();
	cotacao.setCnpj("00.0000");
	cotacao.setNome("nome1");
	CotacaoPreco cotacaoPreco = new CotacaoPreco();
	cotacaoPreco.setPreco(BigDecimal.TEN);
	cotacaoPreco.setQuantidadeMinima(10);
	cotacao.setPrecos(Lists.newArrayList(cotacaoPreco));
	when(restClientService.getForEntity(anyString(), any(), anyMap()))
		.thenReturn(new CotacoesRestClientServiceResponseEntity(new Cotacao[] { cotacao }));
	List<Cotacao> cotacoes = cotacaoService.realizaCotacoesParaProduto(new Produto(null, "1234", null));
	assertNotNull(cotacoes);
	assertEquals(1, cotacoes.size());
	assertEquals("00.0000", cotacoes.get(0).getCnpj());
	assertEquals(BigDecimal.TEN, cotacoes.get(0).getPrecos().get(0).getPreco());

    }

    class CotacoesRestClientServiceResponseEntity implements RestClientServiceResponseEntity<Object> {

	private Object[] cotacoes;

	public CotacoesRestClientServiceResponseEntity(Object[] cotacoes) {
	    this.cotacoes = cotacoes;
	}

	@Override
	public int getStatusCode() {
	    return 200;
	}

	@Override
	public Object[] getBody() {
	    return cotacoes;
	}

    }

    class BadRequestRestClientServiceResponseEntity implements RestClientServiceResponseEntity<Object> {

	@Override
	public int getStatusCode() {
	    return 400;
	}

	@Override
	public Object getBody() {
	    return null;
	}
    }

}
