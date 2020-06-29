package br.com.bluesoft.desafio.junit;

import java.util.Collection;
import java.util.Map;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.mockito.verification.VerificationMode;

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
    
    
    public <T> T verify(T mock,VerificationMode verificationMode) {
	return Mockito.verify(mock,verificationMode);
    }
    
    public VerificationMode times(int wantedNumberOfInvocations) {
	return Mockito.times(wantedNumberOfInvocations);
    }
    
    public String eq(String value) {
	return Mockito.eq(value);
    }
    
    public <T> T any(Class<T> clazz) {
   	return Mockito.any(clazz);
    }
    
    public Integer anyInt() {
   	return Mockito.anyInt();
    }
    
    public String anyString() {
   	return Mockito.anyString();
    }
    
    public <K,V> Map<K,V> anyMap() {
   	return Mockito.anyMap();
    }

    public <T> T any() {
	return Mockito.any();
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
