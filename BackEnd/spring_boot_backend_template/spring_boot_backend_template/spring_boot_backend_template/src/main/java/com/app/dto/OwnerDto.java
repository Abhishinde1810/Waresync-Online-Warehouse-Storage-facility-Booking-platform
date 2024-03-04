package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class OwnerDto {

    private Integer ownerId;

    private String ownerName;

    @Min(value = 1000000000, message = "Mobile number must be at least 10 digits")
    @Max(value = 9999999999L, message = "Mobile number must be at most 10 digits")
    private Long mobileNo;

    @Email
    private String email;

    private String password;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private Integer postalCode;

    public OwnerDto(Integer ownerId, String ownerName, Long mobileNo, String email, String password,
                    String addressLine1, String addressLine2, String city, Integer postalCode) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.mobileNo = mobileNo;
        this.email = email;
        this.password = password;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
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

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "OwnerDto [ownerId=" + ownerId + ", ownerName=" + ownerName + ", mobileNo=" + mobileNo + ", email="
                + email + ", password=" + password + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
                + ", city=" + city + ", postalCode=" + postalCode + "]";
    }
}
