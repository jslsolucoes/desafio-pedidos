package br.com.bluesoft.desafio.junit;

import java.io.IOException;
import java.util.List;

import org.eclipse.jetty.util.resource.Resource;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.context.embedded.LocalServerPort;

public abstract class AbstractIntegrationTest extends AbstractTest {

    @LocalServerPort
    private int port;

    protected WebDriver webDriver;
    protected String urlBase;

    @Before
    public void before() throws IOException {
	this.webDriver = create();
	this.urlBase = urlBase();
    }

    @After
    public void after() {
	this.webDriver.close();
    }

    private String urlBase() throws IOException {
	return "http://localhost:" + port + "/";
    }

    public WebDriver webDriver() {
	return this.webDriver;
    }

    public AbstractIntegrationTest navigateTo(String url) {
	webDriver.navigate().to(url);
	return this;
    }

    public AbstractIntegrationTest and() {
	return this;
    }

    public <T> AbstractIntegrationTest until(ExpectedCondition<T> expectedCondition) {
	new WebDriverWait(webDriver, 3).until(expectedCondition);
	return this;
    }
    
    public WebElement findElement(By by) {
	return webDriver.findElement(by);
    }
    
    public List<WebElement> findElements(By by) {
	return webDriver.findElements(by);
    }

    private WebDriver create() {
	System.setProperty("webdriver.chrome.driver", driverLocation());
	ChromeOptions chromeOptions = new ChromeOptions();
	chromeOptions.addArguments("--headless");
	return new ChromeDriver(chromeOptions);
    }

    private String driverLocation() {
	try {
	    return Resource.newClassPathResource("/chromedriver.exe").getFile().getAbsolutePath();
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    
}