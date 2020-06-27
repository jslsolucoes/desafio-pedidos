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
public class PedidoControllerMvcTest extends AbstractControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void buscarPorTodosOsPedidosDisponiveis() throws Exception {
	this.mockMvc.perform(get(path("/pedidos"))).andExpect(status().isOk())
		.andExpect(jsonPath("$").isArray())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[*].id", hasItems(1, 2)))
		.andExpect(jsonPath("$[*].fornecedor.nome", hasItems("Fornecedor 1")))
		.andExpect(jsonPath("$[*].itens[*].produto.nome", hasItems("ACHOCOLATADO NESCAU 2.0")))
		.andExpect(jsonPath("$[*].itens[*].quantidade", hasItems(3)))
		.andExpect(jsonPath("$[*].itens[*].total", hasItems(0.3)));
    }
}
