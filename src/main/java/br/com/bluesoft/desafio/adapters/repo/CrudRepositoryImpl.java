package br.com.bluesoft.desafio.adapters.repo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Id;

import org.springframework.core.ResolvableType;
import org.springframework.data.repository.CrudRepository;

import br.com.bluesoft.desafio.util.Lists;

public abstract class CrudRepositoryImpl<T, K extends Serializable> implements CrudRepository<T, K> {

    protected EntityManager entityManager;
    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public CrudRepositoryImpl(EntityManager entityManager) {
	this.entityManager = entityManager;
	this.entityClass = (Class<T>) ResolvableType.forClass(CrudRepositoryImpl.class, getClass()).getGeneric(0)
		.resolve();
    }

    @Override
    public <S extends T> S save(S entity) {
	entityManager.persist(entity);
	return entity;
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
	return Lists.newArrayList(entities).stream().map(entity -> save(entity)).collect(Collectors.toList());
    }

    @Override
    public T findOne(K id) {
	return entityManager.find(entityClass, id);
    }

    @Override
    public boolean exists(K id) {
	return findOne(id) != null;
    }

    @Override
    public Iterable<T> findAll() {
	return entityManager.createQuery("select t from " + entityClass.getSimpleName() + " t ", entityClass)
		.getResultList();
    }

    @Override
    public Iterable<T> findAll(Iterable<K> ids) {
	return entityManager
		.createQuery("select t from " + entityClass.getSimpleName() + " t where " + id().getName() + " in :ids",
			entityClass)
		.setParameter("ids", ids).getResultList();
    }

    @Override
    public long count() {
	return entityManager.createQuery("select count(t) from " + entityClass.getSimpleName() + " t ", Long.class)
		.getSingleResult();
    }

    private Field id() {
	return Arrays.asList(entityClass.getDeclaredFields()).stream()
		.filter(field -> field.isAnnotationPresent(Id.class)).findFirst().orElseThrow(
			() -> new IllegalArgumentException("Class" + entityClass + " doesn't have @Id annotation"));
    }

    @Override
    public void delete(K id) {
	delete(findOne(id));
    }

    @Override
    public void delete(T entity) {
	entityManager.remove(entity);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
	entities.forEach(entity -> delete(entity));
    }

    @Override
    public void deleteAll() {
	delete(findAll());
    }
}
