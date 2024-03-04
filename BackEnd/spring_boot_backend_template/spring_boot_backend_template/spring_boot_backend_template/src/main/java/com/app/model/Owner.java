package com.app.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
public class Owner implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ownerId;
	@Column(length = 20)
	private String ownerName;
	
	private long mobileNo;
	@Email
	private String email;
	
	private String password;
		
	@Column(length = 20)
	private String addressLine1;
	@Column(length = 20)
	private String addressLine2;
	@Column(length = 10)
	private String city;
	@Column(length = 6)
	private int postalCode;
	@JsonIgnore
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true  )
    private List<Warehouse> warehouses = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="user_role",joinColumns = @JoinColumn(name="owner",referencedColumnName = "ownerId"),inverseJoinColumns = @JoinColumn(name="id"))
    private Set<Role> roles = new HashSet<>();

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }
    public Owner(int ownerId) {
        this.ownerId = ownerId;
    }
   //  Constructor with ownerId parameter
//    @JsonCreator
//    public Owner(@JsonProperty("ownerId") int ownerId) {
//        this.ownerId = ownerId;
//    }
	public Owner(int ownerId, String ownerName, @Length(max = 10, min = 10) long mobileNo, @Email String email,
			 String password,
			String addressLine1, String addressLine2, String city, int postalCode) {
		System.out.println("Owner Coonstructor called");
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
	public void addWarehouse1(Warehouse warehouse) {
        warehouses.add(warehouse);
        warehouse.setOwner(this);
    }

    public void removeWarehouse(Warehouse warehouse) {
        warehouses.remove(warehouse);
        warehouse.setOwner(null);
    }
	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	@Override
    public String toString() {
        return "Owner{" +
                "ownerId=" + ownerId +
                ", ownerName='" + ownerName + '\'' +
                ", mobileNo=" + mobileNo +
                ", email='" + email + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> rol=roles.stream().map((roles)->new SimpleGrantedAuthority(roles.getName())).collect(Collectors.toList());
		return rol;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
	
	
	
	
}
