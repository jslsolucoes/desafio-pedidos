package br.com.bluesoft.desafio.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class HashMutableSet<T> implements MutableSet<T> {

    private Set<T> set = Sets.newHashSet();;

    public HashMutableSet() {

    }

    public HashMutableSet(Set<T> set) {
	this.set.addAll(set);
    }

    @Override
    public int size() {
	return set.size();
    }

    @Override
    public boolean isEmpty() {
	return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
	return set.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
	return set.iterator();
    }

    @Override
    public Object[] toArray() {
	return set.toArray();
    }

    @SuppressWarnings("hiding")
    @Override
    public <T> T[] toArray(T[] a) {
	return set.toArray(a);
    }

    @Override
    public boolean add(T e) {
	return set.add(e);
    }

    @Override
    public boolean remove(Object o) {
	return set.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
	return set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
	return set.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
	return set.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
	return set.removeAll(c);
    }

    @Override
    public void clear() {
	set.clear();
    }

}
