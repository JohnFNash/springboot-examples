package com.johnfnash.redis.domain;

import java.io.Serializable;

public class Info implements Serializable {

	private static final long serialVersionUID = -3935342547673411350L;

	private String phone;
	private String address;
	
	public Info(String phone, String address) {
		super();
		this.phone = phone;
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
