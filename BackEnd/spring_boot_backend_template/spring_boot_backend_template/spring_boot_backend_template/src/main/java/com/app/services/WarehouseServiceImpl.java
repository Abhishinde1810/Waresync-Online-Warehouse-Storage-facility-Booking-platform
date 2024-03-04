package com.app.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Owner;
import com.app.model.Warehouse;
import com.app.repository.WarehouseRepository;
@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService{
	
	@Autowired
	private WarehouseRepository repo;
	@Override
	public Warehouse addWarehouse(Warehouse ware) {
		if(ware!=null) {
			
			return repo.save(ware);
		}else {
			return null;
		}
	}
	@Override
	public List<Warehouse> getWarehouse() {
		return repo.findAll();
	}
	@Override
	public Optional<Warehouse> getWarehouseById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
	@Override
	public boolean deleteWare(int id) {
	    try {
	        // Check if the warehouse exists before attempting to delete
	        Optional<Warehouse> optionalWarehouse = repo.findById(id);
	        
	        if (optionalWarehouse.isPresent()) {
	            repo.deleteById(id);
	            return true; // Deletion was successful
	        } else {
	            // Warehouse with the given ID does not exist
	            System.out.println("Warehouse with ID " + id + " does not exist.");
	            return false;
	        }
	    } catch (Exception e) {
	        // Exception occurred during deletion
	        System.err.println("Error deleting warehouse with ID " + id + ": " + e.getMessage());
	        return false;
	    }
	}
	public Warehouse updateWarehouse(Warehouse warehouse) {
        // Here you can perform validation, business logic, etc. before updating the warehouse
        // For simplicity, we directly call the save method to update the warehouse
        return repo.save(warehouse);
    }
	@Override
	public List<Warehouse> getWarehouseListByOwner(Owner owner) {
		
		return repo.findByOwner(owner);
	}

}
