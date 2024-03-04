package com.app.dto;



public  class WarehouseRequestDTO {
    private String warehouseName;
    private String price;
    private double capacity;
    private boolean isAvailable;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private int postalCode;
    private int owner_id;
    
	public WarehouseRequestDTO(String warehouseName, String price, double capacity, boolean isAvailable,
			String addressLine1, String addressLine2, String city, int postalCode, int owner_id) {
		super();
		this.warehouseName = warehouseName;
		this.price = price;
		this.capacity = capacity;
		this.isAvailable = isAvailable;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.postalCode = postalCode;
		this.owner_id = owner_id;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public double getCapacity() {
		return capacity;
	}
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
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
	public int getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}
	@Override
	public String toString() {
		return "WarehouseRequestDTO [warehouseName=" + warehouseName + ", price=" + price + ", capacity=" + capacity
				+ ", isAvailable=" + isAvailable + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", city=" + city + ", postalCode=" + postalCode + ", owner_id=" + owner_id + ", getWarehouseName()="
				+ getWarehouseName() + ", getPrice()=" + getPrice() + ", getCapacity()=" + getCapacity()
				+ ", isAvailable()=" + isAvailable() + ", getAddressLine1()=" + getAddressLine1()
				+ ", getAddressLine2()=" + getAddressLine2() + ", getCity()=" + getCity() + ", getPostalCode()="
				+ getPostalCode() + ", getOwner_id()=" + getOwner_id() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
   
	
   
}
