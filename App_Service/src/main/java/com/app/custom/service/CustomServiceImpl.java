package com.app.custom.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom.data.CustomDAO;
import com.app.util.response.UserData;

@Service
@Transactional
public class CustomServiceImpl implements CustomService {

	@Autowired
	private CustomDAO customDao;
	
	@Override
	public List<UserData> getCustomUser() {
		// TODO Auto-generated method stub
		return customDao.getUserData();
	}

	
}
