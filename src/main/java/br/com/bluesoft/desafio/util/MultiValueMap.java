package br.com.bluesoft.desafio.util;

import java.util.List;

public interface MultiValueMap<K, V> {

    public List<V> put(K key, V value);

    public List<V> get(K key);
}
