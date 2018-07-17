package com.app.custom.data;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.util.response.UserData;
@Repository
public class CustomDAOImpl implements CustomDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserData> getUserData() {
		String queryString = "select user.name as name, user.dob as dob, user.userses.password as password from UserProfile user where user.userEmailId='faiz.krm@gmail.com'";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		query.setResultTransformer(Transformers.aliasToBean(UserData.class));
		return query.list();
	}

}
