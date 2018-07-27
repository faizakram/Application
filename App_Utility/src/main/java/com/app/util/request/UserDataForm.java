package com.app.util.request;

import org.springframework.web.multipart.MultipartFile;

public class UserDataForm {
	
	private String name;
	private MultipartFile file;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "UserDataForm [name=" + name + ", file=" + file + "]";
	}
	

}
