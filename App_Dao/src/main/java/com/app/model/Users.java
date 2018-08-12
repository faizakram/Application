package com.app.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "User_Email_Id"))
public class Users implements java.io.Serializable {

 
	private static final long serialVersionUID = 1L;
	private Integer id;
	private UserProfile userProfile;
	private String password;
	private boolean enable;
	private UserToken userToken;
	private Set<Roles> roleses = new HashSet<Roles>(0);
	
	public Users() {
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "User_Email_Id", unique = true, nullable = false)
	public UserProfile getUserProfile() {
		return this.userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Column(name = "password", nullable = false, length = 60)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "enable", nullable = false)
	public boolean getEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", uniqueConstraints = { @UniqueConstraint(columnNames = "user_id"),
	@UniqueConstraint(columnNames = "role_id") }, joinColumns = {
    @JoinColumn(name = "User_Id", unique = true, nullable = false, updatable = false) }, inverseJoinColumns = {
	@JoinColumn(name = "Role_Id", unique = true, nullable = false, updatable = false) })

	public Set<Roles> getRoleses() {
		return this.roleses;
	}

	public void setRoleses(Set<Roles> roleses) {
		this.roleses = roleses;
	}
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	public UserToken getUserToken() {
		return userToken;
	}
	public void setUserToken(UserToken userToken) {
		this.userToken = userToken;
	}
	
}