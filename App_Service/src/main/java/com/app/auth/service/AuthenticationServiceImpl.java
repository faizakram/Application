package com.app.auth.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.login.dao.LoginDAOInterFace;
import com.app.model.UserToken;
import com.app.model.User_Test;
import com.app.model.User_Test_Child;
import com.app.model.Users;
import com.app.token.service.TokenService;
import com.app.token.service.UserTokenService;
import com.app.util.CommonConstants;
import com.app.util.CommonUtil;
import com.app.util.dto.LoginReq;
import com.app.util.error.ErrorCodeHelper;
import com.app.util.error.response.ErrorInfo;
import com.app.util.error.response.ServiceException;

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

	@Autowired
	private ErrorCodeHelper errorCodeHelper;

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
		addTest();
		Users user;
		user = loginDAO.findUserByEmailPassword(loginReq.getUserEmail(), loginReq.getUserCredential());

		if (user == null) {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstants.E1010_ERROR_CODE,
					CommonConstants.E1010_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.UNAUTHORIZED);
		}

		UserToken userToken = userTokenService.findTokenByUser(user.getId());

		if (userToken == null) {
			userToken = tokenService.generateUserToken(user);
			userTokenService.storeTokenInDB(userToken);

		} else if (CommonUtil.isTokenExpired(userToken.getLastUsed())) {

			newToken = tokenService.generateUserToken(userToken);
			userToken.setLastUsed(newToken.getLastUsed());
			userToken.setToken(newToken.getToken());
			userToken.setSecretKey(newToken.getSecretKey());
			userTokenService.updateToken(userToken);
		}

		return userToken.getToken();
	}

	private void addTest() {
		User_Test userTest = new User_Test();
		//userTest.setId(1);
		userTest.setName("Abc");
		User_Test_Child child = new User_Test_Child();
		//child.setId(2);
		child.setAge(20);
		child.setUserTest(userTest);
		userTest.setChild(child);
		loginDAO.addUser(userTest);
	}
}
