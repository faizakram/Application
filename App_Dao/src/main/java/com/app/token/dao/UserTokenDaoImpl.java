package com.app.token.dao;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.app.abs.AbstractDao;
import com.app.model.UserToken;
import com.app.util.constant.CommonConstants;
import com.app.util.error.ErrorCodeHelper;

/**
 * User Token DAO ORM for UserToken Table
 */
@Repository
public class UserTokenDaoImpl extends AbstractDao<Integer, UserToken> implements UserTokenDao {

	@Autowired
	@Qualifier(CommonConstants.ERROR_CODE_HELPER)
	private ErrorCodeHelper errorCodeHelper;

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
		return getByKey(id);
		/*String hql = "select login from UserToken login where login.user.id = :id";//propertyReader.getProperty("");
		Query query = getSession().createQuery(hql);
		getSession().clear();
		query.setInteger("id", id);
		//query.setLong(CommonConstants.SET_INTEGER_ROLE_ID, userRoleId);
		//query.setInteger(CommonConstants.ISDELETED, CommonConstants.ZERO);
		return (UserToken) query.uniqueResult();*/
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
        Query query = getSession().createQuery("select userToken from UserToken userToken where userToken.token =:token");//propertyReader.getProperty(CommonConstants.FIND_KEY_BY_TOKEN));
        query.setString(CommonConstants.TOKEN, token);

		return (UserToken) query.uniqueResult();

	}

}
