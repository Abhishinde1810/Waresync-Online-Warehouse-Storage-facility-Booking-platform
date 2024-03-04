package com.app.services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.OwnerDto;
import com.app.model.Owner;
import com.app.repository.OwnerRepository;

@Service
@Transactional
public class OwnerService implements IOwnerService{

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private OwnerRepository repo;
	 @Autowired
	    private Validator validator;

	@Override
	 public String addOwner(OwnerDto ownerDto) {
        // Validate the DTO before proceeding
        validateDto(ownerDto);

        // Map DTO to Entity
        Owner owner = modelMapper.map(ownerDto, Owner.class);

        // Save the entity
        repo.save(owner);

        return "Owner added successfully";
    }

	private void validateDto(OwnerDto ownerDto) {
	    // Validate the OwnerDto using the Validator
	    var violations = validator.validate(ownerDto);
	    if (!violations.isEmpty()) {
	        for (var violation : violations) {
	            System.out.println("Validation error: " + violation.getPropertyPath() + " " + violation.getMessage());
	        }
	        throw new ConstraintViolationException("Validation failed for OwnerDto", violations);
	    }
	}

	@Override
	public Owner authenticateUser(String email, String password) {
		
		return repo.findByEmailAndPassword(email,password);
	}

	
}
