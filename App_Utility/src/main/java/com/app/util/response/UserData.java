package com.app.util.response;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UserData {
	
	private String name;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dob;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
