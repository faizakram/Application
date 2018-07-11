package com.app.util.request;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginReq {
	@NotEmpty 
	@Email(message ="{NotEmpty.user.email}")
	private String userEmail;
	@NotEmpty
    @Size(min=8, message="{NotEmpty.user.password}")
	private String userCredential;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserCredential() {
		return userCredential;
	}
	public void setUserCredential(String userCredential) {
		this.userCredential = userCredential;
	}
	
	

}
