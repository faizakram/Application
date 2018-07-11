package com.app.token.dao;

import com.app.model.UserToken;

/**
 * User Token DAO ORM for UserToken Table
 */
public interface UserTokenDao {

    /**
     * Store Token in DataBase
     * @param userToken
     * @return boolean
     */
    public boolean storeTokenInDB(UserToken userToken);

    /**
     * Find Token From UserToken join with User
     * @param id
     * @param userRoleId 
     * 
     * @return UserToken object
     */
    public UserToken findTokenByUser(Integer id);

    /**
     * Update Token of User in UserToken
     * @param userToken
     * @return boolean
     */
    public boolean updateToken(UserToken userToken);


    /**
     * Find Key By Token
     * 
     * @param token
     * @return RhUserToken
     */
    public UserToken getKeyByToken(String token);
}
