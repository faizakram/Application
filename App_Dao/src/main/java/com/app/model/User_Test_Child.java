package com.app.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name ="User_Test_Child")
@Entity
public class User_Test_Child {
	private Integer id;
	private Integer age;
	
	private User_Test userTest;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@OneToOne
	@JoinColumn(name = "test_id")
	public User_Test getUserTest() {
		return userTest;
	}

	public void setUserTest(User_Test userTest) {
		this.userTest = userTest;
	}


	
}
