package br.com.bluesoft.desafio.util;

import java.util.Set;

public interface MutableSet<T> extends Set<T> {

    public default MutableSet<T> addItem(T value) {
	add(value);
	return this;
    }

}
