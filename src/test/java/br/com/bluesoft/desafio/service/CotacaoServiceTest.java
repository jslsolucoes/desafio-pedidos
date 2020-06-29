package br.com.bluesoft.desafio.service;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.junit.AbstractTest;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.model.bo.Cotacao;
import br.com.bluesoft.desafio.model.bo.CotacaoPreco;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableRetry
public class CotacaoServiceTest extends AbstractTest {

    @Autowired
    private CotacaoService cotacaoService;

    @MockBean
    private RestClientService restClientService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void forcarRetryComProblemaDeComunicacao() throws CotacaoServiceException, RestClientServiceException {
	expectedException.expectMessage(
		"Não foi possível verificar cotações para o produto gtin \"1234\" pois a integração de sistemas falhou. Você pode tentar novamente daqui alguns instantes. Caso o problema persista entre em contato com o setor técnico para averiguação . Detalhes do erro: Erro de comunicação: java.lang.Exception: some exception");
	expectedException.expect(CotacaoServiceException.class);
	when(restClientService.getForEntity(anyString(), any(), anyMap()))
		.thenThrow(new RestClientServiceException(new Exception("some exception")));
	cotacaoService.melhorCotacao(new Produto(null, "1234", null), 1);
    }

    @Test
    public void forcarRetryComProblemaDeStatusCode() throws CotacaoServiceException, RestClientServiceException {
	expectedException.expectMessage(
		"Não foi possível verificar cotações para o produto gtin \"1234\" pois a integração de sistemas falhou. Você pode tentar novamente daqui alguns instantes. Caso o problema persista entre em contato com o setor técnico para averiguação . Detalhes do erro: Status code não esperado: 400");
	expectedException.expect(CotacaoServiceException.class);

	when(restClientService.getForEntity(anyString(), any(), anyMap()))
		.thenReturn(new BadRequestRestClientServiceResponseEntity());
	cotacaoService.melhorCotacao(new Produto(null, "1234", null), 1);
    }

    @Test
    public void cotacaoDeveSerRealizadaNormalmente() throws CotacaoServiceException, RestClientServiceException {
	when(restClientService.getForEntity(anyString(), any(), anyMap()))
		.thenReturn(new CotacoesRestClientServiceResponseEntity(new Cotacao[] {
			new Cotacao("00.00", "fornecedor1", Arrays.asList(new CotacaoPreco(BigDecimal.valueOf(12.5), 5),
				new CotacaoPreco(BigDecimal.valueOf(11.5), 6))) }));
	Cotacao cotacao = cotacaoService.melhorCotacao(new Produto(null, "1234", null), 6);
	assertEquals("00.00", cotacao.getCnpj());
	assertEquals("fornecedor1", cotacao.getNome());
    }

    @Test
    public void semCotacoes() throws CotacaoServiceException, RestClientServiceException {
	when(restClientService.getForEntity(anyString(), any(), anyMap()))
		.thenReturn(new CotacoesRestClientServiceResponseEntity(new Cotacao[] {}));
	expectedException
		.expectMessage("Nenhum fornecedor encontrado para a quantidade solicitada do produto produto1");
	expectedException.expect(CotacaoServiceException.class);
	Produto produto = Produto.Builder.novoBuilder().comGtin("1234").comNome("produto1").constroi();
	Integer quantidade = 1;
	cotacaoService.melhorCotacao(produto, quantidade);
    }

    @Test
    public void possuiCotacoesPoremNaoAtingeQuantidadeMinima()
	    throws CotacaoServiceException, RestClientServiceException {
	expectedException
		.expectMessage("Nenhum fornecedor encontrado para a quantidade solicitada do produto produto1");
	expectedException.expect(CotacaoServiceException.class);
	Cotacao cotacao = new Cotacao();
	cotacao.setCnpj("00.00");
	cotacao.setNome("fornecedor1");
	CotacaoPreco cotacaoPreco = new CotacaoPreco();
	cotacaoPreco.setPreco(BigDecimal.valueOf(12.5));
	cotacaoPreco.setQuantidadeMinima(5);

	CotacaoPreco cotacaoPreco2 = new CotacaoPreco();
	cotacaoPreco2.setPreco(BigDecimal.valueOf(11.5));
	cotacaoPreco2.setQuantidadeMinima(6);

	cotacao.setPrecos(Arrays.asList(cotacaoPreco, cotacaoPreco2));
	when(restClientService.getForEntity(anyString(), any(), anyMap()))
		.thenReturn(new CotacoesRestClientServiceResponseEntity(new Cotacao[] { cotacao }));
	Produto produto = Produto.Builder.novoBuilder().comGtin("1234").comNome("produto1").constroi();
	Integer quantidade = 4;
	cotacaoService.melhorCotacao(produto, quantidade);
    }

    @Test
    public void verificaMelhorValorParaCotacoes() throws CotacaoServiceException, RestClientServiceException {
	Cotacao cotacao1 = new Cotacao("00.00", "fornecedor1", Arrays
		.asList(new CotacaoPreco(BigDecimal.valueOf(12.5), 5), new CotacaoPreco(BigDecimal.valueOf(11.5), 6)));
	Cotacao cotacao2 = new Cotacao("11.11", "fornecedor2", Arrays
		.asList(new CotacaoPreco(BigDecimal.valueOf(10.5), 5), new CotacaoPreco(BigDecimal.valueOf(12.5), 6)));
	Cotacao cotacao3 = new Cotacao("22.22", "fornecedor3", Arrays
		.asList(new CotacaoPreco(BigDecimal.valueOf(8.5), 20), new CotacaoPreco(BigDecimal.valueOf(9.5), 10)));
	when(restClientService.getForEntity(anyString(), any(), anyMap())).thenReturn(
		new CotacoesRestClientServiceResponseEntity(new Cotacao[] { cotacao1, cotacao2, cotacao3 }));
	Produto produto = Produto.Builder.novoBuilder().comGtin("1234").comNome("produto1").constroi();

	Cotacao melhorPropostaParaCincoItens = cotacaoService.melhorCotacao(produto, 5);
	assertEquals("11.11", melhorPropostaParaCincoItens.getCnpj());
	assertEquals("fornecedor2", melhorPropostaParaCincoItens.getNome());
	assertTrue(melhorPropostaParaCincoItens.atendeQuantidadeMinimima(5));
	assertEquals(BigDecimal.valueOf(10.5), melhorPropostaParaCincoItens.getMelhorPreco(produto, 5));

	Cotacao melhorPropostaParaSeisItens = cotacaoService.melhorCotacao(produto, 6);
	assertEquals("11.11", melhorPropostaParaSeisItens.getCnpj());
	assertEquals("fornecedor2", melhorPropostaParaSeisItens.getNome());
	assertTrue(melhorPropostaParaSeisItens.atendeQuantidadeMinimima(6));
	assertEquals(BigDecimal.valueOf(10.5), melhorPropostaParaSeisItens.getMelhorPreco(produto, 6));

	Cotacao melhorPropostaParaDezItens = cotacaoService.melhorCotacao(produto, 10);
	assertEquals("22.22", melhorPropostaParaDezItens.getCnpj());
	assertEquals("fornecedor3", melhorPropostaParaDezItens.getNome());
	assertTrue(melhorPropostaParaDezItens.atendeQuantidadeMinimima(10));
	assertEquals(BigDecimal.valueOf(9.5), melhorPropostaParaDezItens.getMelhorPreco(produto, 10));

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
