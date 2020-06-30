package br.com.bluesoft.desafio.service;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.junit.AbstractTest;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.model.bo.NovoPedido;
import br.com.bluesoft.desafio.repository.FornecedorRepository;
import br.com.bluesoft.desafio.repository.PedidoItemRepository;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.repository.ProdutoRepository;
import br.com.bluesoft.desafio.service.impl.CriadorPedidoServiceImpl;
import br.com.bluesoft.desafio.util.Lists;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CriadorPedidoServiceTest extends AbstractTest {

    @Autowired
    private CriadorPedidoService criadorPedidoService;
    
    @MockBean
    private PedidoRepository pedidoRepository;
    
    @MockBean
    private CotacaoService cotacaoService;
    
    @MockBean
    private FornecedorRepository fornecedorRepository;
    
    @MockBean
    private PedidoItemRepository pedidoItemRepository;
    
    @MockBean
    private ProdutoRepository produtoRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Test
    public void construtor() {
	assertNotNull(new CriadorPedidoServiceImpl());
    }

    @Test
    public void semProdutos() throws PedidoServiceException {
	expectedException.expectMessage("Ao menos um produto deve ser solicitado para a criação de um novo pedido");
	expectedException.expect(PedidoServiceException.class);
	criadorPedidoService.criarNovosPedidos(Lists.newArrayList());
    }

    @Test
    public void comTodosOsProdutosComQuantidadeZerada() throws PedidoServiceException {
	expectedException.expectMessage("Ao menos um produto deve ser solicitado para a criação de um novo pedido");
	expectedException.expect(PedidoServiceException.class);
	criadorPedidoService.criarNovosPedidos(
		Lists.newArrayList(new NovoPedido(null, 0), new NovoPedido(null, 0), new NovoPedido(null, 0)));
    }
    
    @Test
    public void naoEncontrarMelhorCotacaoParaAlgumProduto() throws CotacaoServiceException, PedidoServiceException {
	expectedException.expectMessage("Alguma mensagem");
	expectedException.expect(PedidoServiceException.class);
	Produto produto = Produto.Builder.novoBuilder().comGtin("1234").constroi();
	when(produtoRepository.buscarProdutoPorGtin(any())).thenReturn(Optional.of(produto));
	when(cotacaoService.melhorCotacao(any(), anyInt()))
		.thenThrow(new CotacaoServiceException("Alguma mensagem"));
	criadorPedidoService.criarNovosPedidos(
		Lists.newArrayList(new NovoPedido(produto, 1)));
    }

}
