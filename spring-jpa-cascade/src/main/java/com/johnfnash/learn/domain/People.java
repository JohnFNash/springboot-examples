package com.johnfnash.learn.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class People implements Serializable {
	
	private static final long serialVersionUID = -4580187061659309868L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "name", nullable = true, length = 20)
	private String name;
	
	@Column(name = "sex", nullable = true, length = 1)
	private String sex;
	
	@Column(name = "birthday", nullable = true)
	private Timestamp birthday;
	
	// People 是关系的维护段，当删除 people时，会级联删除 address
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// people 中的 address_id 字段参考 address 表的id字段
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	public People() {
		super();
	}

	public People(String name, String sex, Timestamp birthday, Address address) {
		super();
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "People [id=" + id + ", name=" + name + ", sex=" + sex + ", birthday=" + birthday + ", address="
				+ address + "]";
	}
	
}
