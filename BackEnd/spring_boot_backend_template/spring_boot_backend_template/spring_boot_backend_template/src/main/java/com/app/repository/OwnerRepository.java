package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Integer> {

	Owner findByEmailAndPassword(String email, String password);
	
	Optional<Owner> findByEmail(String email);

	Owner findByOwnerName(String ownerName);
	
}
