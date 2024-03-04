package com.app.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class CustomerDto {
	private int custId;
	private String name;
	@Min(value = 1000000000, message = "Mobile number must be at least 10 digits")
    @Max(value = 9999999999L, message = "Mobile number must be at most 10 digits")
	private Long mobileNo;
	
	private String email;
	
	private String password;
		
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String city;
	
	private int postalCode;
	
	
	public CustomerDto(int custId, String name,
			long mobileNo,
			String email, String password, String addressLine1, String addressLine2, String city, int postalCode) {
		super();
		this.custId = custId;
		this.name = name;
		this.mobileNo = mobileNo;
		this.email = email;
		this.password = password;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.postalCode = postalCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	@Override 
	public String toString() {
		return "CustomerDto [custId=" + custId + ", name=" + name + ", mobileNo=" + mobileNo + ", email=" + email
				+ ", password=" + password + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", city=" + city + ", postalCode=" + postalCode + "]";
	}
	
	
	
}
