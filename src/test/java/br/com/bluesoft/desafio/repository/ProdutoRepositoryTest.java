package br.com.bluesoft.desafio.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.test.AbstractTest;
import br.com.bluesoft.desafio.util.Lists;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
public class ProdutoRepositoryTest extends AbstractTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    public void buscarPorTodosOsProdutosDisponiveis() {
	List<Produto> produtos = produtoRepository.buscarPorTodosOsProdutosDisponiveis();
	assertNotNull(produtos);
	assertEquals(7, produtos.size());
	assertTrue(Lists.orAnyMatch(produtos, (produto) -> "7894900011517".equals(produto.getGtin()),
		(produto) -> "7891000053508".equals(produto.getGtin())));
    }

    @Test
    public void buscarsProdutoExistentePorGtin() {
	assertTrue(produtoRepository.buscarProdutoPorGtin("7894900011517").isPresent());
    }
    
    @Test
    public void buscarsProdutoNaoExistentePorGtin() {
	assertFalse(produtoRepository.buscarProdutoPorGtin("323").isPresent());
    }

}
