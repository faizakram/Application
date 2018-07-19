package com.app.token.dao;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.app.abs.AbstractDao;
import com.app.model.UserToken;
import com.app.util.constant.CommonConstants;

/**
 * User Token DAO ORM for UserToken Table
 */
@Repository
public class UserTokenDaoImpl extends AbstractDao<Integer, UserToken> implements UserTokenDao {
	
	@Autowired
	private Environment queryPropertyRead;
	/**
	 * Store token in db
	 * 
	 * @param userToken
	 * @return boolean
	 */
	@Override
	public boolean storeTokenInDB(UserToken userToken) {
		persist(userToken);
		return true;

	}

	/**
	 * Find Token From UserToken join with User
	 * 
	 * @param userMailid
	 * @return UserToken
	 */
	@Override
	public UserToken findTokenByUser(Integer id) {
		Query query = getSession().createQuery(queryPropertyRead.getProperty("GET_TOKEN_BY_USER_ID"));
		query.setInteger(CommonConstants.ID, id);
		return (UserToken) query.uniqueResult();
	}

	/**
	 * Update Token of User in UserToken
	 * 
	 * @param userToken
	 * @return boolean
	 */

	@Override
	public boolean updateToken(UserToken userToken) {
		update(userToken);
		return true;

	}

	/**
	 * Find Key By Token
	 * 
	 * @param token
	 * @return UserToken
	 */
	@Override
	public UserToken getKeyByToken(String token) {
        Query query = getSession().createQuery(queryPropertyRead.getProperty("GET_TOKEN_OBJECT_BY_TOKEN"));
        query.setString(CommonConstants.TOKEN, token);
		return (UserToken) query.uniqueResult();

	}

}
