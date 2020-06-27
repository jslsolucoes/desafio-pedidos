package br.com.bluesoft.desafio.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class ResponsesTest {

    @Test
    public void okWithoutBody() {
	ResponseEntity<String> responseEntity = Responses.ok();
	assertEquals(200, responseEntity.getStatusCodeValue());
    }
    
    @Test
    public void okWithBody() {
	ResponseEntity<String> responseEntity = Responses.ok("bb");
	assertEquals(200, responseEntity.getStatusCodeValue());
	assertEquals("bb", responseEntity.getBody());
    }
    
    @Test
    public void notFound() {
	ResponseEntity<String> responseEntity = Responses.notFound();
	assertEquals(404, responseEntity.getStatusCodeValue());
    }
}
