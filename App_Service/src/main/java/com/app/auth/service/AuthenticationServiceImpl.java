package com.app.auth.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.login.dao.LoginDAOInterFace;
import com.app.model.UserToken;
import com.app.model.Users;
import com.app.token.service.TokenService;
import com.app.token.service.UserTokenService;
import com.app.util.request.LoginReq;

/**
 * Authentication Service Authenticate Users And Validate Token
 *
 */
@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private TokenService tokenService;

	@Autowired
	private LoginDAOInterFace loginDAO;
	@Autowired
	private UserTokenService userTokenService;


	/**
	 * Authenticate Users By rhId and userMailid Generate token Store Token In
	 * DataBase
	 * 
	 * @param userMailid
	 * @param isInternal
	 * @param rhId
	 * @return UserToken
	 */
	@Override
	public String authenticateUser(LoginReq loginReq) {
		UserToken newToken;
		Users user;
		user = loginDAO.findUserByEmailPassword(loginReq.getUserEmail(), loginReq.getUserCredential());

		if (user == null) {
			// create custom exception and throw
			return "password missing";
		}

		UserToken userToken = userTokenService.findTokenByUser(user.getId());

		if (userToken == null) {
			userToken = tokenService.generateUserToken(user);
			userTokenService.storeTokenInDB(userToken);
		} else if (tokenService.isTokenExpired(userToken.getLastUsed())) {

			newToken = tokenService.generateUserToken(user);
			userToken.setLastUsed(newToken.getLastUsed());
			userToken.setToken(newToken.getToken());
			userToken.setSecretKey(newToken.getSecretKey());
			userTokenService.updateToken(userToken);
		}
		
		return userToken.getToken();
	}

}
