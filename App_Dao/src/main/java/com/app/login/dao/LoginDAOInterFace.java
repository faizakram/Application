package com.app.login.dao;

import com.app.model.Roles;
import com.app.model.UserProfile;
import com.app.model.User_Test;
import com.app.model.Users;

public interface LoginDAOInterFace {

	public UserProfile findUserProfile(String userEmail);

	public boolean updateUserProfile(UserProfile userProfile);

	public Roles findUserRole(String userType);

	public boolean registerNewAccount(Users user);

	public UserProfile findAccessKeyandEmailIdByUser(String accessKey, String emailId);

	public UserProfile findAccessKeyandEmailIdByAdmin(String accesskey, String userEmail);

	public String findUserByEmailIdUserTable(String userEmail);

	public boolean rejectAccountByAdmin(String userEmail);

	public boolean findPasswordAccessKeyandEmailIdByUser(String accessKey, String emailId);

	public Users findUserByIdInUserTable(String userEmail);


	public Users findUserByEmailPassword(String userEmail, String userCredentail);

	public void addUser(User_Test userTest);

}
