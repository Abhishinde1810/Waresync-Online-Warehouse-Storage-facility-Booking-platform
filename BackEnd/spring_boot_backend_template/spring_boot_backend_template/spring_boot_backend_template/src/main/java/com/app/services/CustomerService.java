package com.app.services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.CustomerDto;
import com.app.model.Customer;
import com.app.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Validator validator;

    @Override
    public String registerCustomer(CustomerDto customerDto) {
        // Validate the DTO before proceeding
        validateDto(customerDto);

        // Map DTO to Entity
        Customer customer = modelMapper.map(customerDto, Customer.class);

        // Save the entity
        customerRepository.save(customer);

        return "Customer registered successfully";
    }

    private void validateDto(CustomerDto customerDto) {
        // Validate the CustomerDto using the Validator
        var violations = validator.validate(customerDto);
        if (!violations.isEmpty()) {
            for (var violation : violations) {
                System.out.println("Validation error: " + violation.getPropertyPath() + " " + violation.getMessage());
            }
            throw new ConstraintViolationException("Validation failed for CustomerDto", violations);
        }
    }

    @Override
    public Customer authenticateCustomer(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password);
    }
}
