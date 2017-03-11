package com.ww.entity;

import lombok.Data;

import java.util.Comparator;
@Data
public class User implements Comparator {

	private Integer userId;
	private String userName;
	private String password;

//	public Integer getUserId() {
//		return userId;
//	}
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}


	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}
}
