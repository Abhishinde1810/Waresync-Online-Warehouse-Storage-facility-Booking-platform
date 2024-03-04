package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Owner;
import com.app.model.Warehouse;
import com.app.repository.OwnerRepository;
import com.app.services.WarehouseService;

import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST, RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT, RequestMethod.OPTIONS})
@RequestMapping("/warehouse")
//To control all warehouse related activities
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private OwnerRepository ownerRepository;

    @PostMapping("/adddata")
    public ResponseEntity<?> addWarehouse(@RequestBody Warehouse warehouseRequest) {
    	System.out.println(warehouseRequest.getOwner());
        System.out.println("Adding warehouse "+warehouseRequest);
        int id=warehouseRequest.getOwner().getOwnerId();
        System.out.println(id);
        try {
            // Check if the owner is null
            if (warehouseRequest.getOwner() == null) {
                return ResponseEntity.badRequest().body("Owner is required");
            }
            
            // Retrieve the owner by ownerId
            Optional<Owner> optionalOwner = ownerRepository.findById(id);
            if (!optionalOwner.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Owner not found with ID: " + warehouseRequest.getOwner().getOwnerId());
            }
            
            // Set the owner for the warehouse
            warehouseRequest.setOwner(optionalOwner.get());

            // Save the warehouse
            Warehouse savedWarehouse = warehouseService.addWarehouse(warehouseRequest);
            return ResponseEntity.ok(savedWarehouse);
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
            // Return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the warehouse");
        }
    }

    @GetMapping("/getwarehouse")
    public ResponseEntity<List<Warehouse>> getAllWareHouses(){
    	List<Warehouse> list=warehouseService.getWarehouse();
    	return ResponseEntity.ok(list);
    }
    
    @GetMapping("/ware/{id}")
    public ResponseEntity<Warehouse> getWarehouse(@PathVariable int id) {
    	System.out.println("for update getting data");
        Optional<Warehouse> warehouseOptional = warehouseService.getWarehouseById(id);
        if (warehouseOptional.isPresent()) {
            return ResponseEntity.ok(warehouseOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/delete/{wareId}")
    public ResponseEntity<String> deleteWarehouse(@PathVariable int wareId){
    	boolean check=warehouseService.deleteWare(wareId);
    	System.out.println(check);
		return ResponseEntity.ok("Deleted");
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateWarehouse(@RequestBody Warehouse warehouseRequest) {
        try {
            // Check if the warehouse with the given ID exists
            int id = warehouseRequest.getWareId(); // Assuming there's a getId() method in the Warehouse class to retrieve the ID
            Optional<Warehouse> optionalWarehouse = warehouseService.getWarehouseById(id);
            if (!optionalWarehouse.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warehouse not found with ID: " + id);
            }

            // Retrieve the existing warehouse
            Warehouse existingWarehouse = optionalWarehouse.get();

            // Update the warehouse details
            existingWarehouse.setWarehouseName(warehouseRequest.getWarehouseName());
            existingWarehouse.setPrice(warehouseRequest.getPrice());
            existingWarehouse.setCapacity(warehouseRequest.getCapacity());
            existingWarehouse.setAvailable(warehouseRequest.isAvailable());
            existingWarehouse.setAddressLine1(warehouseRequest.getAddressLine1());
            existingWarehouse.setAddressLine2(warehouseRequest.getAddressLine2());
            existingWarehouse.setCity(warehouseRequest.getCity());
            existingWarehouse.setPostalCode(warehouseRequest.getPostalCode());

            // Check if the owner is null
            if (warehouseRequest.getOwner() == null) {
                return ResponseEntity.badRequest().body("Owner is required");
            }

            // Retrieve the owner by ownerId
            Optional<Owner> optionalOwner = ownerRepository.findById(warehouseRequest.getOwner().getOwnerId());
            if (!optionalOwner.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Owner not found with ID: " + warehouseRequest.getOwner().getOwnerId());
            }

            // Set the owner for the warehouse
            existingWarehouse.setOwner(optionalOwner.get());

            // Save the updated warehouse
            Warehouse updatedWarehouse = warehouseService.updateWarehouse(existingWarehouse);

            return ResponseEntity.ok(updatedWarehouse);
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
            // Return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the warehouse");
        }
    }
}
