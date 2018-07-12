package com.app.util.request;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
public class SearchDTO {

	@NotEmpty
    @Size(min=4, message="{NotEmpty.user.name}")
	private String name;
	@DateTimeFormat(pattern="dd/MM/yyyy")
    @NotNull
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dateOfJoin;
	@Size(min=6, max= 6, message = "{NotEmpty.user.phone}")
	@Pattern(regexp = "[0-9]+")
	private String pincode;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfJoin() {
		return dateOfJoin;
	}
	public void setDateOfJoin(Date dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "SearchDTO [name=" + name + ", dateOfJoin=" + dateOfJoin + ", pincode=" + pincode + "]";
	}
	
}
