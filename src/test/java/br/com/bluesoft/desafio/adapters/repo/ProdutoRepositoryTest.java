package br.com.bluesoft.desafio.adapters.repo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.desafio.junit.AbstractBaseTest;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.repository.ProdutoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
public class ProdutoRepositoryTest extends AbstractBaseTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    public void buscarPorTodosOsProdutosDisponiveis() {
	List<Produto> produtos = produtoRepository.buscarPorTodosOsProdutosDisponiveis();
	assertNotNull(produtos);
	assertEquals(7, produtos.size());
    }

}
