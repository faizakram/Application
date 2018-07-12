package com.app.token.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.app.custom.data.CustomDAO;
import com.app.custom.service.CustomServiceImpl;
import com.app.util.response.UserData;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class CustomServiceImplTest {

	@Tested
	private CustomServiceImpl customServiceImpl;
	
	@Injectable
	private CustomDAO customDao;
	List<UserData> userInfo;
	@Before
	public void preLoad()
	{
		userInfo = new ArrayList<UserData>();
	}
	
	@Test
    public void getUsers() {
        new Expectations() {
            {
            	customDao.getUserData();
                result = userInfo;
            }
        };
        assertNotNull(customServiceImpl.getCustomUser());
    }
}
