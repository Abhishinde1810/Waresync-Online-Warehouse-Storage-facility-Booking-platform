package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Warehouse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wareId;
	@NotNull
	private String warehouseName;
	
	@NotNull
	private String price;
	
	@NotNull
	private double capacity;
	
	private boolean isAvailable;
	@Column(length = 20)
	private String addressLine1;
	@Column(length = 20)
	private String addressLine2;
	@Column(length = 10)
	private String city;
	@Column(length = 6)
	private int postalCode;
	
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private Owner owner;
//	
//	

	public void setOwner(Owner owner) {
		this.owner = owner;
		System.out.println(owner);
	}


	public Warehouse() {
		super();
	}
//	@ManyToOne
//    @JoinColumn(name = "owner_id", nullable = false)
//    @JsonProperty("owner") // Specify the JSON property name for the Owner object
//    private Owner owner;
//
//    // Constructors, getters, and setters...
//
//    @JsonCreator // Tell Jackson to use this constructor for deserialization
//    public Warehouse(@JsonProperty("wareId") int wareId,
//                     @JsonProperty("warehouseName") @NotNull String warehouseName,
//                     @JsonProperty("price") @NotNull String price,
//                     @JsonProperty("capacity") @NotNull double capacity,
//                     @JsonProperty("isAvailable") boolean isAvailable,
//                     @JsonProperty("addressLine1") String addressLine1,
//                     @JsonProperty("addressLine2") String addressLine2,
//                     @JsonProperty("city") String city,
//                     @JsonProperty("postalCode") int postalCode,
//                     @JsonProperty("owner") Owner owner) {
//        this.wareId = wareId;
//        this.warehouseName = warehouseName;
//        this.price = price;
//        this.capacity = capacity;
//        this.isAvailable = isAvailable;
//        this.addressLine1 = addressLine1;
//        this.addressLine2 = addressLine2;
//        this.city = city;
//        this.postalCode = postalCode;
//        this.owner = owner;
//    }


	public Warehouse(int wareId, @NotNull String warehouseName, @NotNull String price, @NotNull double capacity,
			boolean isAvailable, String addressLine1, String addressLine2, String city, int postalCode,@JsonProperty("owner") Owner owner) {
		
		this.wareId = wareId;
		this.warehouseName = warehouseName;
		this.price = price;
		this.capacity = capacity;
		this.isAvailable = isAvailable;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.postalCode = postalCode;
		this.owner=owner;
	}

	public int getWareId() {
		return wareId;
	}

	public void setWareId(int wareId) {
		this.wareId = wareId;
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

	public Owner getOwner() {
		return owner;
	}


	@Override
	public String toString() {
		return "Warehouse [wareId=" + wareId + ", warehouseName=" + warehouseName + ", price=" + price + ", capacity="
				+ capacity + ", isAvailable=" + isAvailable + ", addressLine1=" + addressLine1 + ", addressLine2="
				+ addressLine2 + ", city=" + city + ", postalCode=" + postalCode + "]";
	}
	
	
	
}