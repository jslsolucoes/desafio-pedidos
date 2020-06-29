package br.com.bluesoft.desafio.it;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.junit.AbstractIntegrationTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PedidoControllerIT extends AbstractIntegrationTest {

    @Test
    public void criacaoDeNovoPedido() throws Exception {
	AbstractIntegrationTest abstractIntegrationTest = navigateTo("/pedidos/novo");

	assertEquals("Adicionar Pedido",
		abstractIntegrationTest.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")))
			.findElement(By.className("title")).getText());

	assertTrue(abstractIntegrationTest
		.until(ExpectedConditions.visibilityOfElementLocated(By.className("nome-produto")))
		.findElements(By.className("nome-produto")).stream()
		.anyMatch(webElement -> webElement.getText().contains("ACHOCOLATADO NESCAU 2.0")));
    }

    @Test
    public void listaPedidosDisponiveis() {
	assertTrue(navigateTo("/pedidos").and()
		.until(ExpectedConditions.visibilityOfElementLocated(By.className("nome-fornecedor")))
		.findElements(By.className("nome-fornecedor")).stream()
		.anyMatch(webElement -> webElement.getText().contains("Fornecedor 1")));
    }

    @Test
    public void tentarCriarPedidoComQuantidadesZeradas() throws Exception {
	AbstractIntegrationTest abstractIntegrationTest = navigateTo("/pedidos/novo");

	abstractIntegrationTest.until(ExpectedConditions.elementToBeClickable(By.className("submeter-formulario")))
		.findElement(By.className("submeter-formulario")).click();

	assertEquals("Ao menos um produto deve ser solicitado para a criação de um novo pedido",
		abstractIntegrationTest
			.until(ExpectedConditions.visibilityOfElementLocated(By.className("mensagem-erro")))
			.findElement(By.className("mensagem-erro")).getText());
    }
    
    @Test
    public void tentarCriarPedidoComQuantidadeSemFornecedorElegivel() throws Exception {
	AbstractIntegrationTest abstractIntegrationTest = navigateTo("/pedidos/novo");

	abstractIntegrationTest.until(ExpectedConditions.presenceOfElementLocated(By.id("7891000100103")))
		.findElement(By.id("7891000100103")).sendKeys("4");
	
	abstractIntegrationTest.until(ExpectedConditions.elementToBeClickable(By.className("submeter-formulario")))
		.findElement(By.className("submeter-formulario")).click();

	assertEquals("Nenhum fornecedor encontrado para a quantidade solicitada do produto LEITE CONDENSADO MOÇA",
		abstractIntegrationTest
			.until(ExpectedConditions.visibilityOfElementLocated(By.className("mensagem-erro")))
			.findElement(By.className("mensagem-erro")).getText());
    }

}