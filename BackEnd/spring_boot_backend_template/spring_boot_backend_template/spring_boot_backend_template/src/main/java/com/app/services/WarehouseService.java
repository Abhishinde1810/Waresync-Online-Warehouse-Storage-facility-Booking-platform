package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.app.model.Owner;
import com.app.model.Warehouse;

public interface WarehouseService {

	public Warehouse addWarehouse(Warehouse ware);

	public List<Warehouse> getWarehouse();

	public Optional<Warehouse> getWarehouseById(int id);

	public boolean deleteWare(int id);
	
	public Warehouse updateWarehouse(Warehouse warehouse) ;
	
	public List<Warehouse> getWarehouseListByOwner(Owner owner);
}
