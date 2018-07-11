package com.app.token.service.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.app.auth.service.AuthenticationServiceImpl;
import com.app.login.dao.LoginDAOInterFace;
import com.app.model.UserToken;
import com.app.model.Users;
import com.app.token.service.TokenService;
import com.app.token.service.UserTokenService;
import com.app.util.request.LoginReq;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class AuthenticationServiceImplTest {
	
	@Tested
	private AuthenticationServiceImpl authenticationServiceImpl;
	
	@Injectable
	private TokenService tokenService;

	@Injectable
	private LoginDAOInterFace loginDAO;
	
	@Injectable
	private UserTokenService userTokenService;
	
	
	private LoginReq loginReq;
	private Users user;
	private UserToken newToken;
	private UserToken userToken;
	@Before
	public void temp() {
		System.out.println("asdasd");
		loginReq=new LoginReq();
		loginReq.setUserEmail("asd");
		loginReq.setUserCredential("asd");
		user = new Users();
		user.setId(1);
		newToken = new UserToken();
		newToken.setId(1);
		newToken.setToken("abc");
		newToken.setLastUsed(new Date());
		userToken = new UserToken();
		userToken.setId(1);
		userToken.setLastUsed(new Date());
		userToken.setSecretKey(new byte[20]);
		userToken.setToken("abc");
	}
	
	@Test
    public void passwordMissmatch() {
        new Expectations() {
            {
                loginDAO.findUserByEmailPassword(loginReq.getUserEmail(), loginReq.getUserCredential());
                result = null;
            }
        };
        assertEquals("password missing",authenticationServiceImpl.authenticateUser(loginReq));
    }
	
	@Test
    public void tokenNotCreated() {
        new Expectations() {
            {
                loginDAO.findUserByEmailPassword(loginReq.getUserEmail(), loginReq.getUserCredential());
                result = user;
                userTokenService.findTokenByUser(user.getId());
                result = null;
                tokenService.generateUserToken(user);
                result= newToken;
            }
        };
        assertEquals("abc",authenticationServiceImpl.authenticateUser(loginReq));
    }
	
	@Test
    public void tokenCreated() {
        new Expectations() {
            {
                loginDAO.findUserByEmailPassword(loginReq.getUserEmail(), loginReq.getUserCredential());
                result = user;
                userTokenService.findTokenByUser(user.getId());
                result = newToken;
                tokenService.isTokenExpired(newToken.getLastUsed());
                result= true;
                tokenService.generateUserToken(user);
                result = userToken;
            }
        };
        assertEquals("abc",authenticationServiceImpl.authenticateUser(loginReq));
    }
	
	

}

