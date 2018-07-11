package com.app.token.service;

import com.app.model.UserToken;

/**
 * User Token Service
 */
public interface UserTokenService {

    /**
     *  Store Token in DataBase
     * @param userToken
     * @return boolean
     */
    public boolean storeTokenInDB(UserToken userToken);

    /**
     * Find Token joined with User
     * 
     * @param integer
     * @param userRoleId 
     * 
     * @return UserToken
     */
    public UserToken findTokenByUser(Integer integer);

    /**
     * Update Token Of User in UserToken
     * @param userToken
     * @return boolean
     */
    public boolean updateToken(UserToken userToken);

    /**
     * Find key by Token
     * 
     * @param token
     * @return UserToken
     */
    public UserToken getKeyByToken(String token);
}
