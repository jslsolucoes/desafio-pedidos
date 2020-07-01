package br.com.bluesoft.desafio.repository;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.desafio.junit.AbstractTest;
import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.service.ProdutoServiceException;
import br.com.bluesoft.desafio.util.Lists;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
public class PedidoRepositoryTest extends AbstractTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Test
    public void buscarPorTodosOsPedidosDisponiveis() {
	List<Pedido> pedidos = pedidoRepository.buscarPorTodosOsPedidosDisponiveis();
	assertNotNull(pedidos);
	assertEquals(2, pedidos.size());
	assertTrue(Lists.anyMatch(pedidos, (pedido) -> pedido.getId().equals(1L)
		&& "00.000.000/00000-00".equals(pedido.getFornecedor().getCnpj())));
    }

    @Test
    public void criarNovoPedido() throws ProdutoServiceException {
	Pedido pedido = pedidoRepository.criarNovo(Pedido.Builder.novoBuilder()
		.comFornecedor(fornecedorRepository.criarUmNovoFornecedorSeNaoExistir(
			Fornecedor.Builder.novoBuilder().comCnpj("12.112").comRazaoSocial("for2").constroi()))
		.comItem(produtoRepository.buscarProdutoPorGtin("7894900011517")
			.orElseThrow(() -> new ProdutoServiceException("Não achei produto")), 1, BigDecimal.TEN)
		.constroi());
	assertNotNull(pedido);
	assertNotNull(pedido.getId());
	assertNotNull(pedido.getItens());
	assertEquals(1, pedido.getItens().size());
    }

}
