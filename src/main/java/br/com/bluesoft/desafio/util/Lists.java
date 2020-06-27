package br.com.bluesoft.desafio.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Lists {

    private Lists() {
	throw new IllegalStateException("You should not call this constructor");
    }

    @SafeVarargs
    public static <T> List<T> newArrayList(T... elements) {
	if (elements == null)
	    newArrayList();
	return new ArrayList<>(Arrays.asList(elements));
    }

    public static <T> boolean isEmpty(List<T> list) {
	return list == null || list.isEmpty();
    }

    public static <T> List<T> newArrayList() {
	return new ArrayList<T>();
    }

    public static <T> List<T> newArrayList(Iterable<T> iterable) {
	if (iterable == null) {
	    return new ArrayList<T>();
	}
	return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    public static <T, R> List<R> transform(List<T> list, Function<T, R> map) {
	return list.stream().map(map).collect(Collectors.toList());
    }

    public static <T> T reduce(List<T> list, BinaryOperator<T> reduce) {
	return list.stream().reduce(reduce).get();
    }

    @SafeVarargs
    public static <T> boolean orAnyMatch(List<T> list, Predicate<T>... predicates) {
	return anyMatch(list, reduce(Lists.newArrayList(predicates), Predicate::or));
    }

    @SafeVarargs
    public static <T> boolean andAnyMatch(List<T> list, Predicate<T>... predicates) {
	return anyMatch(list, reduce(Lists.newArrayList(predicates), Predicate::and));
    }

    public static <T> boolean anyMatch(List<T> list, Predicate<T> predicate) {
	return list.stream().anyMatch(predicate);
    }

}
