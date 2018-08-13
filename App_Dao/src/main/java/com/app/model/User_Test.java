package com.app.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.engine.internal.Cascade;

@Table(name ="User_Test")
@Entity
public class User_Test {
	private Integer id;
	private String name;
	private User_Test_Child child;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = IDENTITY)
	public Integer getId() {
		return this.id;
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
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userTest", cascade = CascadeType.ALL)
	public User_Test_Child getChild() {
		return child;
	}

	public void setChild(User_Test_Child child) {
		this.child = child;
	}
	
}
