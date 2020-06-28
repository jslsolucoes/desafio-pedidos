package br.com.bluesoft.desafio.util;

import java.util.List;
import java.util.Set;

public interface MultiValueMap<K, V> {

    public List<V> put(K key, V value);

    public List<V> get(K key);

    public Set<K> keySet();
}
