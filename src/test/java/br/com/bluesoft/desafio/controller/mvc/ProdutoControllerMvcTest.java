package br.com.bluesoft.desafio.controller.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerMvcTest extends AbstractControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void buscarPorTodosOsProdutosDisponiveis() throws Exception {
	this.mockMvc.perform(get(path("/produtos"))).andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$", hasSize(7)))
		.andExpect(jsonPath("$[*].gtin", hasItems("7894900011517", "7891000100103"))).andExpect(
			jsonPath("$[*].nome", hasItems("REFRIGERANTE COCA-COLA 2LT", "SALGADINHO FANDANGOS QUEIJO")));
    }
}
