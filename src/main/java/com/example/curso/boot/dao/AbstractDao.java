package com.example.curso.boot.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class AbstractDao<T, PK extends Serializable> {

	@SuppressWarnings("unchecked")
	private final Class<T> clazz = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	@PersistenceContext
	private EntityManager em;
	
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public void save(T entity) {
		em.persist(entity);
	}
	
	public void update(T entity) {
		em.merge(entity);
	}
	
	public void delete(PK id) {
		em.remove(em.getReference(clazz, id));
	}
	
	public T findById(PK id) {
		return em.find(clazz, id);
	}
	
	public List<T> findAll(){
		return em.createQuery("from " + clazz.getSimpleName(), clazz).getResultList();
	}
	
	protected List<T> createQuery(String jpql, Object... params){
		TypedQuery<T> query = em.createQuery(jpql, clazz);
		for(int i=0; i<params.length; i++) {
			query.setParameter(i+1, params[i]);
		}
		return query.getResultList();
	}
}
