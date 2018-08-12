package com.app.auth.service;

import com.app.util.dto.LoginReq;

/**
 * Authentication Service Authenticate User And Validate Token
 *
 */
public interface AuthenticationService {

    /**
     * Authenticate User By rhId and userMailid Generate token Store Token In DataBase
     * @param password 
     * 
     * @param userMailid
     * @param isInternal
     * @param rhId
     * 
     * @return RhUserToken
     */
    public String authenticateUser(LoginReq loginReq);

    /**
     * Authenticate User By rhId and uuid Generate token Store Token In DataBase
     * @param ssoRequest
     * @return RhUserToken of user
     */
    //public PersistentLogin authenticateUser(SSORequest ssoRequest);


}
