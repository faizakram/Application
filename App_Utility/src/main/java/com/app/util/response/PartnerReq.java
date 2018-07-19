package com.app.util.response;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 
 * @author Faiz
 * while case of update pass id
 */
public class PartnerReq {
	
	private Integer id;
	@NotEmpty
    @Size(min=4, message="{NotEmpty.user.name}")
	private String name;
	@NotEmpty 
	@Email(message ="{NotEmpty.user.email}")
	private String email;
	@NotEmpty
	@Size(min=10, max= 10, message = "{NotEmpty.user.phone}")
	@Pattern(regexp = "[0-9]+")
	private String phone;
	@NotEmpty
	private String title;
	@NotEmpty
	private String instituteName;
	@NotEmpty
	private String description;
	@DateTimeFormat(pattern="dd/MM/yyyy")
    @NotNull
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date activeTo;
	@DateTimeFormat(pattern="dd/MM/yyyy")
    @NotNull
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date activeFrom;
	@NotNull
	private Boolean notify;
	@NotNull
	private Boolean loginStauts;
	private MultipartFile file;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getActiveTo() {
		return activeTo;
	}
	public void setActiveTo(Date activeTo) {
		this.activeTo = activeTo;
	}
	public Date getActiveFrom() {
		return activeFrom;
	}
	public void setActiveFrom(Date activeFrom) {
		this.activeFrom = activeFrom;
	}
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Boolean getNotify() {
		return notify;
	}
	public void setNotify(Boolean notify) {
		this.notify = notify;
	}
	public Boolean getLoginStauts() {
		return loginStauts;
	}
	public void setLoginStauts(Boolean loginStauts) {
		this.loginStauts = loginStauts;
	}
	
}
