package com.app.services;

import com.app.dto.CustomerDto;
import com.app.dto.OwnerDto;
import com.app.model.Customer;
import com.app.model.Owner;

public interface ICustomerService {

	String registerCustomer(CustomerDto customerDto);
	
	Customer authenticateCustomer(String email, String password);

}
