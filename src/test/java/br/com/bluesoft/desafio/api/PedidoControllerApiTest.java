package br.com.bluesoft.desafio.api;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.bluesoft.desafio.api.dto.NovoPedidoDto;
import br.com.bluesoft.desafio.junit.AbstractControllerApiTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PedidoControllerApiTest extends AbstractControllerApiTest {

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
    
    @Test
    public void criarNovoPedidoSemProdutos() throws Exception {
	this.mockMvc.perform(post(path("/pedidos"))).andExpect(status().isBadRequest())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.mensagem", is("Ao menos um produto deve ser solicitado para a criação de um novo pedido")));
    }
    
    @Test
    public void criarNovoPedidoComProdutosZerados() throws Exception {
	NovoPedidoDto novoPedidoDto = NovoPedidoDto.Builder.novoBuilder().comGtin("1234").comQuantidade(0).constroi();
	NovoPedidoDto novoPedidoDto2 = NovoPedidoDto.Builder.novoBuilder().comGtin("3456").comQuantidade(0).constroi();
	this.mockMvc.perform(post("/pedidos",Arrays.asList(novoPedidoDto,novoPedidoDto2)))
		.andExpect(status().isBadRequest())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.mensagem", is("Ao menos um produto deve ser solicitado para a criação de um novo pedido")));
    }
}
