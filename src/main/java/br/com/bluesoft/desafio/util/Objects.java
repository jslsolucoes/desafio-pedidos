package br.com.bluesoft.desafio.util;

@SuppressWarnings("unchecked")

public class Objects {

    private Objects() {
	throw new IllegalStateException("You should not call this constructor");
    }

    public static <C> C cast(Object object) {
	return (C) object;
    }

    public static <C> C cast(Object object, Class<C> clazz) {
	return (C) object;
    }
}
