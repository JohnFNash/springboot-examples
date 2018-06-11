package com.johnfnash.learn.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class Address implements Serializable {
	
	private static final long serialVersionUID = -4960690362029661737L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "phone", nullable = false, length = 11)
	private String phone;
	
	@Column(name = "zipcode", nullable = true, length = 6)
    private String zipcode;
	
    @Column(name = "address", nullable = true, length = 100)
    private String address;

    //如果不需要根据Address级联查询People，可以注释掉
//  @OneToOne(mappedBy = "address", cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
//  private People people;
    
    public Address() {
		super();
	}
    
	public Address(String phone, String zipcode, String address) {
		super();
		this.phone = phone;
		this.zipcode = zipcode;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", phone=" + phone + ", zipcode=" + zipcode + ", address=" + address + "]";
	}
	
}
