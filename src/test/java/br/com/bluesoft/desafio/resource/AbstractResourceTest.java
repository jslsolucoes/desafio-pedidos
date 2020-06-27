package br.com.bluesoft.desafio.resource;

import org.hamcrest.Matcher;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import br.com.bluesoft.desafio.junit.AbstractBaseTest;

public abstract class AbstractResourceTest extends AbstractBaseTest {

    public String baseUri() {
	return "/api/v1";
    }

    public String path(String path) {
	return baseUri() + path;
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
}
