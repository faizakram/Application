package com.app.util.dto;

import java.util.Date;

import javax.annotation.PreDestroy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.app.util.CommonConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
@Component
@Scope(value = CommonConstants.PROTO_TYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserData {
	
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	@Past
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = CommonConstants.MY_TIME_ZONE)
	@Temporal(TemporalType.DATE)
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

	@PreDestroy
	public void cleanUp() throws Exception {
	  System.out.println("Spring Container is destroy! Customer clean up");
	}
	
}
