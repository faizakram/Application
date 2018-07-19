package com.app.token.service.test;

import static org.junit.Assert.assertNotNull;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Qualifier;

import com.app.model.Users;
import com.app.token.service.TokenServiceImpl;
import com.app.token.service.UserTokenService;
import com.app.util.constant.CommonConstants;
import com.app.util.error.ErrorCodeHelper;
import com.app.util.reader.PropertyReader;

import io.jsonwebtoken.impl.crypto.MacProvider;
import mockit.Injectable;
import mockit.Tested;

public class TokenServiceImplTest {

	@Tested
	private TokenServiceImpl tokenServiceImpl;

	@Injectable
	@Qualifier(CommonConstants.APPLICATION_PROPERTY_READER)
	private PropertyReader appPropertyReader;

	@Injectable
	@Qualifier(CommonConstants.ERROR_CODE_HELPER)
	private ErrorCodeHelper errorCodeHelper;

	@Injectable
	private UserTokenService userTokenService;

	private Users user;
	private static Key secret = MacProvider.generateKey();

	@Before
	public void preLoad() {
		user = new Users();
		user.setId(1);
		user.setPassword("123");

	}

	@Test
	public void generateUserTokenTest() {
		assertNotNull(tokenServiceImpl.generateUserToken(user));
	}


	

}
