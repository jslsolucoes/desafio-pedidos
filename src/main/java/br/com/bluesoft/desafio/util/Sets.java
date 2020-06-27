package br.com.bluesoft.desafio.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class Sets {

    private Sets() {
	throw new IllegalStateException("You should not call this constructor");
    }

    @SafeVarargs
    public static <T> Set<T> newHashSet(T... elements) {
	if (elements == null) {
	    return newHashSet();
	}
	return new HashSet<>(Arrays.asList(elements));
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

    public static <T> Set<T> sort(Set<T> set, Comparator<T> comparator) {
	return set.stream().sorted(comparator).collect(Collectors.toSet());
    }

}
