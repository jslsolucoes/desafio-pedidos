package br.com.bluesoft.desafio.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.test.AbstractTest;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
public class FornecedorRepositoryTest extends AbstractTest {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Test
    public void buscarFornecedorExistente() {
	assertTrue(fornecedorRepository.buscarFornecedorPorCnpj("00.000.000/00000-00").isPresent());
    }
    
    @Test
    public void buscarFornecedorNaoExistente() {
	assertFalse(fornecedorRepository.buscarFornecedorPorCnpj("00.000.000/00000-20").isPresent());
    }
    
    @Test
    public void deveCriarFornecedorPoisEleNaoExiste() {
	Fornecedor fornecedor = fornecedorRepository.criarUmNovoFornecedorSeNaoExistir(new Fornecedor(null, "00.000.000/00000-20", "fornecedor3"));
	assertEquals(2L,fornecedorRepository.count());
	assertEquals("fornecedor3",fornecedor.getRazaoSocial());
    }
    
    @Test
    public void naoDeveCriarFornecedorPoisEleJaExiste() {
	Fornecedor fornecedor = fornecedorRepository.criarUmNovoFornecedorSeNaoExistir(new Fornecedor(null, "00.000.000/00000-00", "fornecedor3"));
	assertEquals(1L,fornecedorRepository.count());
	assertEquals("Fornecedor 1",fornecedor.getRazaoSocial());
    }

}
