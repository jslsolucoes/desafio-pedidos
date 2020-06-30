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
	this.mockMvc.perform(get(path("/pedidos"))).andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[*].id", hasItems(1, 2 , 3)))
		.andExpect(jsonPath("$[*].fornecedor.nome", hasItems("Fornecedor 1")))
		.andExpect(jsonPath("$[*].itens[*].produto.nome", hasItems("ACHOCOLATADO NESCAU 2.0")))
		.andExpect(jsonPath("$[*].itens[*].quantidade", hasItems(3)))
		.andExpect(jsonPath("$[*].itens[*].total", hasItems(0.3)));
    }

    @Test
    public void criarNovoPedidoSemProdutos() throws Exception {
	this.mockMvc.perform(post(path("/pedidos"))).andExpect(status().isBadRequest())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(jsonPath(
			"$.mensagem", is("Ao menos um produto deve ser solicitado para a criação de um novo pedido")));
    }

    @Test
    public void criarNovoPedidoComProdutosZerados() throws Exception {
	NovoPedidoDto novoPedidoDto = NovoPedidoDto.Builder.novoBuilder().comGtin("1234").comQuantidade(0).constroi();
	NovoPedidoDto novoPedidoDto2 = NovoPedidoDto.Builder.novoBuilder().comGtin("3456").comQuantidade(0).constroi();
	this.mockMvc.perform(post("/pedidos", Arrays.asList(novoPedidoDto, novoPedidoDto2)))
		.andExpect(status().isBadRequest())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(jsonPath(
			"$.mensagem", is("Ao menos um produto deve ser solicitado para a criação de um novo pedido")));
    }

    @Test
    public void criarNovoPedidoSemFornecedorElegivel() throws Exception {
	NovoPedidoDto novoPedidoDto = NovoPedidoDto.Builder.novoBuilder().comGtin("7891000100103").comQuantidade(4)
		.constroi();
	this.mockMvc.perform(post("/pedidos", Arrays.asList(novoPedidoDto))).andExpect(status().isBadRequest())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.mensagem", startsWith(
			"Nenhum fornecedor encontrado para a quantidade solicitada do produto LEITE CONDENSADO")));
    }

    @Test
    public void criarNovoPedidoSemAgrupamentoDeFornecedorDescartandoZerados() throws Exception {
	NovoPedidoDto novoPedidoDto = NovoPedidoDto.Builder.novoBuilder().comGtin("7891000100103").comQuantidade(5)
		.constroi();
	NovoPedidoDto novoPedidoDto2 = NovoPedidoDto.Builder.novoBuilder().comGtin("7891000053508").comQuantidade(0)
		.constroi();
	this.mockMvc.perform(post("/pedidos", Arrays.asList(novoPedidoDto,novoPedidoDto2))).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].fornecedor.nome", is("Fornecedor 3")))
		.andExpect(jsonPath("$[0].itens").isArray()).andExpect(jsonPath("$[0].itens", hasSize(1)))
		.andExpect(jsonPath("$[0].itens[0].produto.nome", startsWith("LEITE CONDENSADO")))
		.andExpect(jsonPath("$[0].itens[0].quantidade", is(5)))
		.andExpect(jsonPath("$[0].itens[0].preco", is(5.0)))
		.andExpect(jsonPath("$[0].itens[0].total", is(25.0)));
    }

    @Test
    public void criarNovoPedidoComAgrupamentoDeFornecedor() throws Exception {
	NovoPedidoDto novoPedidoLeiteCondensado = NovoPedidoDto.Builder.novoBuilder().comGtin("7891000100103")
		.comQuantidade(6).constroi();
	NovoPedidoDto novoPedidoCerveja = NovoPedidoDto.Builder.novoBuilder().comGtin("7891991010856").comQuantidade(15)
		.constroi();
	NovoPedidoDto novoPedidoCocaCola = NovoPedidoDto.Builder.novoBuilder().comGtin("7894900011517").comQuantidade(10)
		.constroi();
	NovoPedidoDto novoPedidoFandangos = NovoPedidoDto.Builder.novoBuilder().comGtin("7892840222949").comQuantidade(40)
		.constroi();
	
	
	
	this.mockMvc.perform(post("/pedidos", Arrays.asList(novoPedidoLeiteCondensado, novoPedidoCerveja, novoPedidoCocaCola,novoPedidoFandangos)))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$", hasSize(3)))
		
		.andExpect(jsonPath("$[0].fornecedor.nome", is("Fornecedor 2")))
		.andExpect(jsonPath("$[0].itens").isArray())
		.andExpect(jsonPath("$[0].itens", hasSize(1)))
		.andExpect(jsonPath("$[0].itens[*].produto.nome", hasItems("SALGADINHO FANDANGOS QUEIJO")))
		.andExpect(jsonPath("$[0].itens[*].quantidade", hasItems(40)))
		.andExpect(jsonPath("$[0].itens[*].preco", hasItems(4.1)))
		.andExpect(jsonPath("$[0].itens[*].total", hasItems(164.0)))
		
		.andExpect(jsonPath("$[1].fornecedor.nome", is("Fornecedor 3")))
		.andExpect(jsonPath("$[1].itens").isArray())
		.andExpect(jsonPath("$[1].itens", hasSize(2)))
		.andExpect(jsonPath("$[1].itens[*].produto.nome", hasItems("CERVEJA BUDWEISER")))
		.andExpect(jsonPath("$[1].itens[*].quantidade", hasItems(15, 6)))
		.andExpect(jsonPath("$[1].itens[*].preco", hasItems(3.0, 5.0)))
		.andExpect(jsonPath("$[1].itens[*].total", hasItems(45.0, 30.0)))
		
		.andExpect(jsonPath("$[2].fornecedor.nome", is("Fornecedor 1")))
		.andExpect(jsonPath("$[2].itens").isArray())
		.andExpect(jsonPath("$[2].itens", hasSize(1)))
		.andExpect(jsonPath("$[2].itens[*].produto.nome", hasItems("REFRIGERANTE COCA-COLA 2LT")))
		.andExpect(jsonPath("$[2].itens[*].quantidade", hasItems(10)))
		.andExpect(jsonPath("$[2].itens[*].preco", hasItems(5.89)))
		.andExpect(jsonPath("$[2].itens[*].total", hasItems(58.90)));
    }
}
