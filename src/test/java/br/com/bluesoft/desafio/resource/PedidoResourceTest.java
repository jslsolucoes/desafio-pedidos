package br.com.bluesoft.desafio.resource;

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
public class PedidoResourceTest extends AbstractResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listarTodosOsPedidosDisponiveis() throws Exception {
	this.mockMvc.perform(get(path("/pedidos"))).andExpect(status().isOk())
		.andExpect(jsonPath("$").isArray())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
}
