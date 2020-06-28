package br.com.bluesoft.desafio.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashMultiValueMap<K, V> implements MultiValueMap<K, V> {

    private Map<K, List<V>> map = Maps.newHashMap();

    @Override
    public List<V> put(K key, V value) {
	List<V> list = map.getOrDefault(key, Lists.newArrayList());
	list.add(value);
	return map.put(key, list);
    }

    @Override
    public List<V> get(K key) {
	return map.get(key);
    }

    @Override
    public Set<K> keySet() {
	return map.keySet();
    }

}
