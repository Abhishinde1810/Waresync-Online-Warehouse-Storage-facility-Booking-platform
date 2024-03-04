package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Owner;
import com.app.model.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse,Integer>{

	List<Warehouse> findByOwner(Owner owner);

}
