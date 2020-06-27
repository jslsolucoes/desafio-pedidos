package br.com.bluesoft.desafio.util;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public final class Maps {

    private Maps() {
	throw new IllegalStateException("You should not call this constructor");
    }

    public static <K, V> Map<K, V> newHashMap() {
	return new HashMap<>();
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) {
	return map == null || map.isEmpty();
    }

    public static <K, V> Map<K, V> newHashMap(Map<K, V> map) {
	if (map == null) {
	    return newHashMap();
	}
	return map;
    }

    public static <K, V> Map.Entry<K, V> entry(K key, V value) {
	return new AbstractMap.SimpleEntry<>(key, value);
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> of(Object... objects) {
	if (objects.length % 2 != 0)
	    throw new IllegalArgumentException("Objects must be odd values");
	Map<K, V> map = Maps.newHashMap();
	for (int i = 0; i < objects.length; i = i + 2) {
	    map.put((K) objects[i], (V) objects[i + 1]);
	}
	return map;
    }

}
