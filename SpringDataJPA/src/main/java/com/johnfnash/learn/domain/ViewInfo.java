package com.johnfnash.learn.domain;

import java.io.Serializable;

public class ViewInfo implements Serializable {

	private static final long serialVersionUID = -6347911007178390219L;

	private UserInfo userInfo;
	private Address address;

	public ViewInfo() {

	}

	public ViewInfo(UserInfo userInfo) {
		Address address = new Address();
		this.userInfo = userInfo;
		this.address = address;
	}

	public ViewInfo(Address address) {
		UserInfo userInfo = new UserInfo();
		this.userInfo = userInfo;
		this.address = address;
	}

	public ViewInfo(UserInfo userInfo, Address address) {
		this.userInfo = userInfo;
		this.address = address;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
