package com.training.assessment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_tbl")
public class User {
	@Id
	private int userId;
	
	@NotNull
	private String password;
	
	public enum userAccess{
			Admin,
			Employee
	};
	
	@NotNull
	private userAccess _userAccess;
	
	public User() {
		
	}
	public User(int userId, String password, userAccess uAc) {
		super();
		this.userId = userId;
		this.password = password;
		this._userAccess = uAc;
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
	public userAccess get_userAccess() {
		return _userAccess;
	}
	public void set_userAccess(userAccess _userAccess) {
		this._userAccess = _userAccess;
	}	
}
