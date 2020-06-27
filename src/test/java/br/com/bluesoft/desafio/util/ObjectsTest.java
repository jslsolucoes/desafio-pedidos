package br.com.bluesoft.desafio.util;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ObjectsTest {

    @Test(expected = ClassCastException.class)
    public void explicitIllegalCast() {
	Long value = Objects.cast(1, Long.class);
	assertThat(value, is(1L));
    }

    @Test
    public void valorNullComCastExplicito() {
	Long value = Objects.cast(null, Long.class);
	assertThat(value, is(nullValue()));
    }

    @Test
    public void valorValidoComCastExplicito() {
	MyInterface myInterface = Objects.cast(new MyInterfaceImpl(), MyInterface.class);
	assertThat(myInterface, not(nullValue()));
    }
    
    
    @Test(expected = ClassCastException.class)
    public void verificaCastIlegalImplicito() {
	Long value = Objects.cast(1);
	assertThat(value, is(1L));
    }

    @Test
    public void valorNullComCastImplicito() {
	Long value = Objects.cast(null);
	assertThat(value, is(nullValue()));
    }
    
    @Test
    public void valorValidoComCastImplicito() {
	MyInterface myInterface = Objects.cast(new MyInterfaceImpl());
	assertThat(myInterface, not(nullValue()));
    }

    interface MyInterface {

    }

    class MyInterfaceImpl implements MyInterface {

    }

}
