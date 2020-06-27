package br.com.bluesoft.desafio.util;

import java.util.HashSet;
import java.util.Set;

public final class Sets {

    private Sets() {
	throw new IllegalStateException("You should not call this constructor");
    }

    public static <T> Set<T> newHashSet() {
	return new HashSet<T>();
    }

    public static <T> Set<T> newHashSet(Set<T> set) {
	if (set == null) {
	    return newHashSet();
	}
	return set;
    }

}
