package br.com.bluesoft.desafio.adapters.repo;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.desafio.adapters.repo.domain.FornecedorImpl;
import br.com.bluesoft.desafio.junit.AbstractBaseTest;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
public class CrudRepositoryImplTest extends AbstractBaseTest {

    @Autowired
    private MyCrudRepository myCrudRepository;

    @Test
    public void salvarUnicaEntidade() {
	FornecedorImpl fornecedorImpl = myCrudRepository.save(new FornecedorImpl("1212", "2121"));
	assertNotNull(fornecedorImpl.getId().getValue());
    }

    @Test
    public void salvarVariasEntidades() {
	List<FornecedorImpl> fornecedoresImpl = Lists.newArrayList(myCrudRepository
		.save(Arrays.asList(new FornecedorImpl("1212", "2121"), new FornecedorImpl("1212", "2121"))));
	assertNotNull(fornecedoresImpl);
	assertEquals(2, fornecedoresImpl.size());
    }

    @Test
    public void encontraUmaEntidade() {
	FornecedorImpl fornecedorImpl = myCrudRepository.findOne(1L);
	assertNotNull(fornecedorImpl);
	assertEquals(1L, fornecedorImpl.getId().getValue());
    }

    @Test
    public void verificaSeUmaEntidadeExiste() {
	assertTrue(myCrudRepository.exists(1L));
    }

    @Test
    public void encontrarTodas() {
	Lists.newArrayList(myCrudRepository
		.save(Arrays.asList(new FornecedorImpl("1212", "f1"), new FornecedorImpl("1212", "f2"))));
	List<FornecedorImpl> fornecedoresImpl = Lists.newArrayList(myCrudRepository.findAll());
	assertNotNull(fornecedoresImpl);
	assertEquals(3, fornecedoresImpl.size());
    }

    @Test
    public void encontrarTodosPorId() {
	List<FornecedorImpl> findeds = Lists.newArrayList(myCrudRepository.findAll(Lists.newArrayList(1L)));
	assertNotNull(findeds);
	assertEquals(1, findeds.size());
    }

    @Test
    public void contar() {
	assertEquals(1, myCrudRepository.count());
    }

    @Test
    public void deletarPorId() {
	FornecedorImpl fornecedorImpl = myCrudRepository.save(new FornecedorImpl("1212", "2121"));
	myCrudRepository.delete(fornecedorImpl.getId().getValue());
	assertFalse(myCrudRepository.exists(fornecedorImpl.getId().getValue()));
    }

    @Test
    public void deletarPorEntidade() {
	FornecedorImpl fornecedorImpl = myCrudRepository.save(new FornecedorImpl("1212", "2121"));
	myCrudRepository.delete(fornecedorImpl);
	assertFalse(myCrudRepository.exists(fornecedorImpl.getId().getValue()));
    }

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

	@Bean
	@Autowired
	public MyCrudRepository employeeService(EntityManager entityManager) {
	    return new MyCrudRepository(entityManager);
	}
    }

}
