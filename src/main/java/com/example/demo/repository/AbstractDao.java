package com.example.demo.repository;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Properties;

/**
 * Base class for all other Daos to extend. Contains basic operations like save
 * and delete.
 *
 * @author Rohit
 */
public abstract class AbstractDao<T> implements Dao<T> {

	@Autowired
	private SessionFactoryProvider sessionFactoryProvider;


//    @Resource(name = "applicationProperties")
//    Properties applicationProperties;

	/**
	 * Saves a persistent object instance
	 *
	 * @param obj the object to be saved / created (must be persistent (i.e. in
	 *            current session).
	 */
	@Override
	public void saveOrUpdate(final T obj) {
		getCurrentSession().saveOrUpdate(obj);
	}

	/**
	 * Saves a persistent object instance
	 *
	 * @param obj the object to be saved / created (must be persistent (i.e. in
	 *            current session).
	 */
	@Override
	public Long save(final T obj) {
		return (Long)getCurrentSession().save(obj);
	}

	/**
	 * Saves a list of objects
	 */
	@Override
	public void saveAll(List<T> objects) {
		int i = 0;
		Session session = getCurrentSession();
		int batchSize = Integer.valueOf(100);
		for (T obj : objects) {
			session.save(obj);

			if (batchSize != 0 && (++i % batchSize == 0)) {
				// flush a batch of updates and release memory:
				session.flush();
				session.clear();
			}
		}
	}

	/**
	 * Merges and saves a persistent object instance.
	 *
	 * @param obj the object to be merged and persisted.
	 */
	@Override
	public T merge(final T obj) {
		return (T) getCurrentSession().merge(obj);
	}

	/**
	 * Deletes an object.
	 *
	 * @param obj the object to be deleted.
	 */
	@Override
	public void delete(final T obj) {
		getCurrentSession().delete(obj);
	}

	@Override
	public void deleteById(Serializable id) {
		T obj = getById(id);
		if (obj == null)
			return;
		delete(obj);
	}

	@Override
	public void deleteAllById(List<Long> ids) {
		ids.forEach(id -> deleteById(id));
	}

	@Override
	public void deleteAll() {
		List<T> allValues = loadAll();
		allValues.forEach(current -> getCurrentSession().delete(current));
	}

	@Override
	public void deleteAll(List<T> objects) {
		objects.forEach(obj -> getCurrentSession().delete(obj));
	}

	@Override
	public void update(T obj) {
		getCurrentSession().update(obj);
	}

	@Override
	public void updateAll(List<T> objects) {
		int i = 0;
		Session session = getCurrentSession();
		int batchSize = Integer.valueOf(100);
		for (T obj : objects) {
			session.update(obj);

			if (batchSize != 0 && (++i % batchSize == 0)) {
				// flush a batch of updates and release memory:
				session.flush();
				session.clear();
			}
		}
	}

	@Override
	public List<T> loadAll() {
		Session session = getCurrentSession();
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();

		CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(getClassType());
		Root<T> from = (Root<T>) criteriaQuery.from(getClassType());
		CriteriaQuery<T> selectQuery = criteriaQuery.select(from);

		TypedQuery<T> typedQuery = session.createQuery(selectQuery);
		return typedQuery.getResultList();
	}

	@Override
	public T getById(Serializable id) {
		return (T) getCurrentSession().get(getClassType(), id);
	}

	protected Session getCurrentSession() {
		return sessionFactoryProvider.getSessionFactory().getCurrentSession();
	}

	private Class<?> getClassType() {
		Class<?> genericClass = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return genericClass;
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return getCurrentSession().getCriteriaBuilder();
	}

	@Override
	public Query<T> createTypedCriteriaQuery(CriteriaQuery<T> typedCriteriaQuery) {
		return getCurrentSession().createQuery(typedCriteriaQuery);
	}

	@Override
	public void flush() {
		getCurrentSession().flush();
	}
}