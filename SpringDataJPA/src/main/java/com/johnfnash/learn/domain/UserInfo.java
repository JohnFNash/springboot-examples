package com.johnfnash.learn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_user")
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 8283950216116626180L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private int age;
    private String sex;
    private String email;
    
    private Long addressId;

	public UserInfo() {
		super();
	}
		
	public UserInfo(String name, int age, String sex, String email, Long addressId) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.email = email;
		this.addressId = addressId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	@Override
	public String toString() {
		return String.format("UserInfo [userId=%d, name=%s, age=%s, sex=%s, email=%s]", userId, name, age, sex, email);
	}
    
}
