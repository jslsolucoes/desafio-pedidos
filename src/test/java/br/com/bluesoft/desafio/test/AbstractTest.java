package br.com.bluesoft.desafio.test;

import java.util.Collection;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

@SuppressWarnings("unchecked")
public class AbstractTest {

    public <T> Matcher<java.lang.Iterable<T>> hasItems(T... items) {
	return Matchers.hasItems(items);
    }

    public Matcher<Collection<? extends Object>> hasSize(int size) {
	return Matchers.hasSize(size);
    }

    public <T> Matcher<T> is(T value) {
	return Matchers.is(value);
    }

    public <T> OngoingStubbing<T> when(T methodCall) {
	return Mockito.when(methodCall);
    }

    public void assertTrue(boolean condition) {
	Assert.assertTrue(condition);
    }

    public void assertFalse(boolean condition) {
	Assert.assertFalse(condition);
    }

    public void assertNotNull(Object object) {
	Assert.assertNotNull(object);
    }

    public void assertEquals(int expected, int actual) {
	Assert.assertEquals(expected, actual);
    }

    public void assertEquals(long expected, Object actual) {
	Assert.assertEquals(expected, actual);
    }

    public void assertEquals(long expected, long actual) {
	Assert.assertEquals(expected, actual);
    }

    public void assertEquals(Object expected, Object actual) {
	Assert.assertEquals(expected, actual);
    }

}
