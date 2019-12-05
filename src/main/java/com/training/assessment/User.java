package com.training.assessment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_tbl")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@NotNull
	private String password;
	
	@NotNull
	private boolean userAccess;
	public User() {
		
	}
	public User(int userId, String password, boolean userAccess) {
		super();
		this.userId = userId;
		this.password = password;
		this.userAccess = userAccess;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isUserAccess() {
		return userAccess;
	}
	public void setUserAccess(boolean userAccess) {
		this.userAccess = userAccess;
	}
	
}
