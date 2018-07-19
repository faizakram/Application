package com.app.token.service;

import java.util.Date;
import java.util.Map;

import com.app.model.UserToken;
import com.app.model.Users;

/**
 * Token service interface
 *
 * @author
 * Created: Dec 6, 2017
 */
public interface TokenService {

	/**
	 * generate new token for a user
	 * 
	 * @param user
	 * @param roles
	 * @return RhUserToken
	 */
    public UserToken generateUserToken(Users user);

    /**
     * parse a token string to check its authenticity and fetch data present store in token
     * 
     * @param token
     * @return Map<String, Object>
     */
    public Map<String, Object> parseUserToken(String token);

	public UserToken generateUserToken(UserToken userToken);


}
