package com.app.services;

import com.app.dto.OwnerDto;
import com.app.model.Owner;

public interface IOwnerService {

	String addOwner(OwnerDto owner);

	Owner authenticateUser(String email, String password);

}
