package com.app.token.service.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.app.model.UserToken;
import com.app.token.dao.UserTokenDao;
import com.app.token.service.UserTokenServiceImpl;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class UserTokenServiceTest {

	@Tested
	UserTokenServiceImpl userTokenServiceImpl;

	@Injectable
	private UserTokenDao userTokenDao;

	private UserToken userToken;

	@Before
	public void preLoad() {
		userToken = new UserToken();
		userToken.setId(1);
		userToken.setLastUsed(new Date());
		userToken.setSecretKey(new byte[20]);
		userToken.setToken("abc");
	}

	@Test
	public void sotreTokenDBTest() {
		new Expectations() {
			{
				userTokenDao.storeTokenInDB(userToken);
				result = true;
			}
		};
		assertEquals(true, userTokenServiceImpl.storeTokenInDB(userToken));
	}

	@Test
	public void findTokenByUserTest() {
		new Expectations() {
			{
				userTokenDao.findTokenByUser(1);
				result = userToken;
			}
		};
		assertEquals(userToken, userTokenServiceImpl.findTokenByUser(1));
	}

	@Test
	public void updateTokenDBTest() {
		new Expectations() {
			{
				userTokenDao.updateToken(userToken);
				result = true;
			}
		};
		assertEquals(true, userTokenServiceImpl.updateToken(userToken));
	}

	@Test
	public void getKeyByTokenTest() {
		new Expectations() {
			{
				userTokenDao.getKeyByToken("abc");
				result = userToken;
			}
		};
		assertEquals(userToken, userTokenServiceImpl.getKeyByToken("abc"));
	}
}
