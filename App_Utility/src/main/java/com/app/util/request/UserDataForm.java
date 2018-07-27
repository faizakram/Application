package com.app.util.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UserDataForm {
	
	private String name;
	private List<MultipartFile> file;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MultipartFile> getFile() {
		return file;
	}
	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "UserDataForm [name=" + name + ", file=" + file + "]";
	}
	

}
