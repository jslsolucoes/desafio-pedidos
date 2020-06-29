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
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.model.bo.Cotacao;
import br.com.bluesoft.desafio.model.bo.CotacaoPreco;
import br.com.bluesoft.desafio.test.AbstractTest;
import br.com.bluesoft.desafio.util.Lists;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CotacaoServiceClassificadorTest extends AbstractTest {

    @Autowired
    private CotacaoServiceClassificador cotacaoServiceClassificador;

    @MockBean
    private CotacaoService cotacaoService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void semCotacoes() throws CotacaoServiceException {
	expectedException
		.expectMessage("Nenhum fornecedor encontrado para a quantidade solicitada do produto produto1");
	expectedException.expect(CotacaoServiceException.class);
	Produto produto = Produto.Builder.novoBuilder().comGtin("1234").comNome("produto1").constroi();
	Integer quantidade = 1;
	cotacaoServiceClassificador.melhorCotacao(produto, quantidade);
    }

    @Test
    public void possuiCotacoesPoremNaoAtingeQuantidadeMinima() throws CotacaoServiceException {
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
	when(cotacaoService.realizaCotacoesParaProduto(any())).thenReturn(Lists.newArrayList(cotacao));
	Produto produto = Produto.Builder.novoBuilder().comGtin("1234").comNome("produto1").constroi();
	Integer quantidade = 4;
	cotacaoServiceClassificador.melhorCotacao(produto, quantidade);
    }

    @Test
    public void verificaMelhorValorParaCotacoes() throws CotacaoServiceException {
	Cotacao cotacao1 = new Cotacao("00.00", "fornecedor1", Arrays.asList(new CotacaoPreco(BigDecimal.valueOf(12.5), 5),
		new CotacaoPreco(BigDecimal.valueOf(11.5), 6)));
	Cotacao cotacao2 = new Cotacao("11.11", "fornecedor2", Arrays.asList(new CotacaoPreco(BigDecimal.valueOf(10.5), 5),
		new CotacaoPreco(BigDecimal.valueOf(12.5), 6)));
	Cotacao cotacao3 = new Cotacao("22.22", "fornecedor3", Arrays.asList(new CotacaoPreco(BigDecimal.valueOf(8.5), 20),
		new CotacaoPreco(BigDecimal.valueOf(9.5), 10)));
	when(cotacaoService.realizaCotacoesParaProduto(any())).thenReturn(Lists.newArrayList(cotacao1,cotacao2,cotacao3));
	Produto produto = Produto.Builder.novoBuilder().comGtin("1234").comNome("produto1").constroi();
	
	Cotacao melhorPropostaParaCincoItens = cotacaoServiceClassificador.melhorCotacao(produto, 5);
	assertEquals("11.11", melhorPropostaParaCincoItens.getCnpj());
	assertEquals("fornecedor2", melhorPropostaParaCincoItens.getNome());
	assertTrue(melhorPropostaParaCincoItens.atendeQuantidadeMinimima(5));
	assertEquals(BigDecimal.valueOf(10.5),melhorPropostaParaCincoItens.getMelhorPreco(produto, 5));
	
	
	Cotacao melhorPropostaParaSeisItens = cotacaoServiceClassificador.melhorCotacao(produto, 6);
	assertEquals("11.11", melhorPropostaParaSeisItens.getCnpj());
	assertEquals("fornecedor2", melhorPropostaParaSeisItens.getNome());
	assertTrue(melhorPropostaParaSeisItens.atendeQuantidadeMinimima(6));
	assertEquals(BigDecimal.valueOf(10.5),melhorPropostaParaSeisItens.getMelhorPreco(produto, 6));
	
	Cotacao melhorPropostaParaDezItens = cotacaoServiceClassificador.melhorCotacao(produto, 10);
	assertEquals("22.22", melhorPropostaParaDezItens.getCnpj());
	assertEquals("fornecedor3", melhorPropostaParaDezItens.getNome());
	assertTrue(melhorPropostaParaDezItens.atendeQuantidadeMinimima(10));
	assertEquals(BigDecimal.valueOf(9.5),melhorPropostaParaDezItens.getMelhorPreco(produto, 10));
	
    }
    
    
}
