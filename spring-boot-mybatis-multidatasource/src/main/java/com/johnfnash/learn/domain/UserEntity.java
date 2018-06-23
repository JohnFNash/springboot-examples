package com.johnfnash.learn.domain;

import java.io.Serializable;

import com.johnfnash.learn.enums.UserSexEnum;

public class UserEntity implements Serializable {

	private static final long serialVersionUID = 4782121370999276153L;

	private String id;
	private String userName;
	private String passWord;
	private UserSexEnum userSex;
	private String nickName;
	
	public UserEntity() {
		super();
	}

	public UserEntity(String userName, String passWord, UserSexEnum userSex) {
		super();
		this.passWord = passWord;
		this.userName = userName;
		this.userSex = userSex;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String password) {
		this.passWord = password;
	}

	public UserSexEnum getUserSex() {
		return userSex;
	}

	public void setUserSex(UserSexEnum userSex) {
		this.userSex = userSex;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "userName " + this.userName + ", pasword " + this.passWord + ", sex " + userSex.name();
	}
	
}
