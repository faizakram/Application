package com.app.abs;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;

import com.app.util.CommonConstants;

@PropertySource(CommonConstants.QUERY_PROPERTIES)
public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}
	public void delete(T entity) {
		getSession().delete(entity);
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}
	
	public Long getCount()
	{
		Criteria criteriaCount = getSession().createCriteria(persistentClass);
		criteriaCount.setProjection(Projections.rowCount());
		return (Long)criteriaCount.uniqueResult();
	}
	/*for Example */
	@SuppressWarnings("unchecked")
	public List<Object> findAllActiveBlog(Integer pageIndex, Integer pageSize, String name) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("From Blog where name =:name order by blogDateTime desc");
			query.setString("name", name);
			query.setFirstResult((pageIndex - 1) * pageSize);
			query.setMaxResults(pageSize);
			if (query.list() == null || query.list().isEmpty()) {
				return null;
			}
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
