package com.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Entity
@Table(name = "user_profile")
public class UserProfile implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private String userEmailId;
	private String name;
	private Date dob;
	private Date doj;
	private String mobileNo;
	private String skype;
	private String linkedIn;
	private String facebook;
	private String twitter;
	private String googlePlus;
	private String website;
	private String address;
	private String profilePic;

	private Users userses;
	


	@Id
	@Column(name = "User_Email_Id", unique = true, nullable = false, length = 60)
	public String getUserEmailId() {
		return this.userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	@Column(name = "Name", nullable = false, length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DOB", length = 0)
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DOJ", nullable = false, length = 0)
	public Date getDoj() {
		return this.doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	@Column(name = "MobileNo", length = 10)
	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(name = "Skype", length = 65535)
	public String getSkype() {
		return this.skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	@Column(name = "LinkedIn", length = 65535)
	public String getLinkedIn() {
		return this.linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	@Column(name = "Facebook", length = 65535)
	public String getFacebook() {
		return this.facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	@Column(name = "Twitter", length = 65535)
	public String getTwitter() {
		return this.twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	@Column(name = "GooglePlus", length = 65535)
	public String getGooglePlus() {
		return this.googlePlus;
	}

	public void setGooglePlus(String googlePlus) {
		this.googlePlus = googlePlus;
	}

	@Column(name = "Website", length = 65535)
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "Address", length = 65535)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ProfilePic", length = 65535)
	public String getProfilePic() {
		return this.profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userProfile")
	public Users getUserses() {
		return this.userses;
	}

	public void setUserses(Users userses) {
		this.userses = userses;
	}
	
	

	
	
}
