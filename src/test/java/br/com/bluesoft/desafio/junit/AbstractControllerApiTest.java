package br.com.bluesoft.desafio.junit;

import org.hamcrest.Matcher;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractControllerApiTest extends AbstractTest {

    public String baseUri() {
	return "/api/v1";
    }

    public String path(String path) {
	return baseUri() + path;
    }
    
    public MockHttpServletRequestBuilder post(String urlTemplate, Object... uriVars) {
	return MockMvcRequestBuilders.post(urlTemplate, uriVars);
    }

    public MockHttpServletRequestBuilder get(String urlTemplate, Object... uriVars) {
	return MockMvcRequestBuilders.get(urlTemplate, uriVars);
    }

    public ContentResultMatchers content() {
	return MockMvcResultMatchers.content();
    }

    public StatusResultMatchers status() {
	return MockMvcResultMatchers.status();
    }

    public JsonPathResultMatchers jsonPath(String expression, Object... args) {
	return MockMvcResultMatchers.jsonPath(expression, args);
    }

    public <T> ResultMatcher jsonPath(String expression, Matcher<T> matcher) {
	return MockMvcResultMatchers.jsonPath(expression, matcher);
    }
    
    public String asJson(Object value) throws JsonProcessingException {
	ObjectMapper objectMapper = new ObjectMapper();
	return objectMapper.writeValueAsString(value);
    }
    
    public MockHttpServletRequestBuilder post(String path,Object value) throws JsonProcessingException {
	return post(path(path)).content(asJson(value)).contentType(MediaType.APPLICATION_JSON_UTF8);
    }
}
