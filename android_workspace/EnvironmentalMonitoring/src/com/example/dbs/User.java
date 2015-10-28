package com.example.dbs;

import java.io.Serializable;
import java.util.ArrayList;

public class User extends ArrayList<User> implements Serializable {
	private int id,class_id;
	private String name,phone,right,password,email;
	
	
	
	public User(int id, int class_id, String name, String phone, String right,
			String password,String email) {
		this.id = id;
		this.class_id = class_id;
		this.name = name;
		this.phone = phone;
		this.right = right;
		this.password = password;
		this.email=email;
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", class_id=" + class_id + ", name=" + name
				+ ", phone=" + phone + ", right=" + right + ", password="
				+ password + ", email=" + email + "]";
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRight() {
		return right;
	}
	public void setRight(String right) {
		this.right = right;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	
}