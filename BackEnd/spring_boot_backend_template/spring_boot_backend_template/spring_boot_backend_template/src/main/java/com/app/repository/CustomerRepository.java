package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Customer;
import com.app.model.Owner;

public interface CustomerRepository extends  JpaRepository<Customer, Integer>{
      Customer findByEmailAndPassword(String email, String password);
	
	Optional<Customer> findByEmail(String email);
	
}
