package bionic.project.abo.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bionic.project.abo.entity.Flight;

/**
 * Implementation of the generic Data Access Service All CRUD (create, read,
 * update, delete) basic data access operations for any persistent object are
 * performed in this class.
 * 
 * @author Vasyl Horokhovskyi
 */

public abstract class GenericDAO<T> {
	@PersistenceContext
	protected EntityManager em;
	private Class<T> type;

	public GenericDAO() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}
		public T create(final T t) {
			em.persist(t);
			return t;
		}

		public void delete(final Object id) {
			em.remove(em.getReference(type, id));
		} 
		public T read(final Object id) {
			return (T) em.find(type, id);
		} 

		public T update(final T t) {
			return this.em.merge(t);
		}
		
//		public List<T> showAll() {
//			TypedQuery<T> query = em.createQuery("SELECT t FROM :T", type);
//			query.setParameter("T", type.getName());
//			 List<T> list = query.getResultList();
//			 return list; 
//		}
	}

