package com.app.token.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.UserToken;
import com.app.token.dao.UserTokenDao;

/**
 * User Token Service Class
 */
@Service
@Transactional
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private UserTokenDao userTokenDao;

    /**
     * Store Token in DataBase
     * @param userToken
     * @return boolean
     */
    @Override
    public boolean storeTokenInDB(UserToken userToken) {
        return userTokenDao.storeTokenInDB(userToken);
    }

    /**
     * Find Token joined with User
     * 
     * @param userMailid
     * @return UserToken
     */
    @Override
    public UserToken findTokenByUser(Integer id) {
        return userTokenDao.findTokenByUser(id);
    }

    /**
     * Update Token Of User in UserToken
     * 
     * @param userToken
     * @return boolean
     */
    @Override
    public boolean updateToken(UserToken userToken) {

        return userTokenDao.updateToken(userToken);
    }

    /**
     * Get Key by Token
     * 
     * @param token
     * @return UserToken
     */
    @Override
    public UserToken getKeyByToken(String token) {

        return userTokenDao.getKeyByToken(token);
    }

}
