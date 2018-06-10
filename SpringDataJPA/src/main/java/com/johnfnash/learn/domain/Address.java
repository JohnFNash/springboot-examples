package com.johnfnash.learn.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	private String areaCode;
	private String country;
	private String province;
	private String city;
	private String area;
	private String detailAddress;
	
	public Address() {
		super();
	}
	
	public Address(String areaCode, String country, String province, String city, String area,
			String detailAddress) {
		super();
		this.areaCode = areaCode;
		this.country = country;
		this.province = province;
		this.city = city;
		this.area = area;
		this.detailAddress = detailAddress;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", areaCode=" + areaCode + ", country=" + country + ", province="
				+ province + ", city=" + city + ", area=" + area + ", detailAddress=" + detailAddress + "]";
	}

}
