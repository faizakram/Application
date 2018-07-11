package com.app.login.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.model.Roles;
import com.app.model.UserProfile;
import com.app.model.Users;

@Repository
public class LoginDAOImplemaintation implements LoginDAOInterFace {
	
	private static Logger logger = Logger.getLogger(LoginDAOImplemaintation.class);

	@Autowired
	private SessionFactory sessionFactory;

	public UserProfile findUserProfile(String userEmail) {
		try {
			Criteria cr = sessionFactory.getCurrentSession().createCriteria(UserProfile.class);
			cr.add(Restrictions.eq("userEmailId", userEmail));
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			if (cr.list() == null || cr.list().isEmpty()) {
				return null;
			}
			logger.error(LoginDAOImplemaintation.class.getMethods());
			return (UserProfile) cr.list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(LoginDAOImplemaintation.class.getMethods());
			return null;
		}
	}
	
	public Roles findUserRole(String roleName) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Roles.class);
		cr.add(Restrictions.eq("role", roleName));
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if (cr.list() == null || cr.list().isEmpty()) {
			return null;
		}
		logger.info(LoginDAOImplemaintation.class.getMethods());
		return (Roles) cr.list().get(0);

	}

	public boolean registerNewAccount(Users user) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(Users.class.getName(), user);
			logger.error(LoginDAOImplemaintation.class.getMethods());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			logger.error(LoginDAOImplemaintation.class.getMethods());
			return false;

		}
	}

	public UserProfile findAccessKeyandEmailIdByAdmin(String accesskey, String userEmail) {

		Criteria cr = sessionFactory.getCurrentSession().createCriteria(UserProfile.class);
		Criterion rest = Restrictions.or(Restrictions.eq("userEmailId", userEmail));
		cr.createAlias("userVerfications", "verification"); 
		Criterion rest1 = Restrictions.or(Restrictions.eq("verification.adminEmailVerification", accesskey));
		cr.add(Restrictions.and(rest, rest1));
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if (cr.list() == null || cr.list().isEmpty()) {
			return null;
		}
		logger.info(LoginDAOImplemaintation.class.getMethods());
		return (UserProfile) cr.list().get(0);
	}

	public UserProfile findAccessKeyandEmailIdByUser(String accessKey, String emailId) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(UserProfile.class);
		Criterion rest = Restrictions.or(Restrictions.eq("userEmailId", emailId));
		cr.createAlias("userVerfications", "verification"); 
		Criterion rest1 = Restrictions.or(Restrictions.eq("verification.userEmailVerification", accessKey));
		cr.add(Restrictions.and(rest, rest1));
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if (cr.list() == null || cr.list().isEmpty()) {
			return null;
		}
		logger.info(LoginDAOImplemaintation.class.getMethods());
		return (UserProfile) cr.list().get(0);
	}

	

	public boolean updateUserProfile(UserProfile userProfile) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(UserProfile.class.getName(), userProfile);
			logger.error(LoginDAOImplemaintation.class.getMethods());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			logger.error(LoginDAOImplemaintation.class.getMethods());
			return false;
		}

	}

	public String findUserByEmailIdUserTable(String userEmail) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Users.class);
		cr.add(Restrictions.eq("username", userEmail));
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if (cr.list() == null || cr.list().isEmpty()) {
			return "Not Avail";
		}
		logger.info(LoginDAOImplemaintation.class.getMethods());
		return "Success";
	}

	public boolean rejectAccountByAdmin(String userEmail) {
		UserProfile userProfile = null;
		try {
			userProfile = (UserProfile) sessionFactory.getCurrentSession().load(UserProfile.class, userEmail);
			sessionFactory.getCurrentSession().delete(userProfile);
			logger.error(LoginDAOImplemaintation.class.getMethods());
			return true;
		} catch (HibernateException e) {
			logger.error(LoginDAOImplemaintation.class.getMethods());
			return false;
		}
	}

	public boolean findPasswordAccessKeyandEmailIdByUser(String accessKey, String emailId) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(UserProfile.class);
		Criterion rest = Restrictions.or(Restrictions.eq("userEmailId", emailId));
		Criterion rest1 = Restrictions.or(Restrictions.eq("userVerfications.forgotPassword", accessKey));
		cr.add(Restrictions.and(rest, rest1));
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if (cr.list() == null || cr.list().isEmpty()) {
			return false;
		}
		logger.info(LoginDAOImplemaintation.class.getMethods());
		return true;
	}

	public Users findUserByIdInUserTable(String userEmail) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Users.class);
		cr.add(Restrictions.eq("username", userEmail));
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if (cr.list() == null || cr.list().isEmpty()) {
			return null;
		}
		logger.info(LoginDAOImplemaintation.class.getMethods());
		return (Users) cr.list().get(0);
	}

	

	@Override
	public Users findUserByEmailPassword(String userEmail, String userCredentail) {
		Query query = sessionFactory.getCurrentSession().createQuery("select user from Users user where user.userProfile.userEmailId =:userEMail and user.password =:password");
		query.setString("userEMail", userEmail);
		query.setString("password", userCredentail);
		/*Criteria cr = sessionFactory.getCurrentSession().createCriteria(Users.class);
		cr.add(Restrictions.eq("userMailid", email));
		cr.add(Restrictions.eq("password", password));
		//cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
*/		logger.info(LoginDAOImplemaintation.class.getMethods());
		return (Users) query.uniqueResult();
	}

}
