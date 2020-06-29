package br.com.bluesoft.desafio.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.junit.AbstractTest;
import br.com.bluesoft.desafio.util.Maps;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestClientServiceTest extends AbstractTest {

    @Autowired
    private RestClientService restClientService;

    @Test
    public void testeSimplesDeGet() throws RestClientServiceException {
	RestClientServiceResponseEntity<String> responseRestClientEntity = restClientService
		.getForEntity("https://httpbin.org/get", String.class, Maps.newHashMap());
	assertEquals(200, responseRestClientEntity.getStatusCode());
	assertTrue(responseRestClientEntity.getBody().contains("\"url\": \"https://httpbin.org/get\""));
    }

}
