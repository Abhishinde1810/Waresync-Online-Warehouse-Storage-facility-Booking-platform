package com.app.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	
	@ManyToOne
	@JoinColumn(name = "name")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "wareId")
	private Warehouse warehouse;
	
	@FutureOrPresent
	private LocalDate startDate;
	
	@Future
	private LocalDate endDate;

	public Booking(int bookId, Customer customer, Warehouse warehouse, @FutureOrPresent LocalDate startDate,
			@Future LocalDate endDate) {
		super();
		this.bookId = bookId;
		this.customer = customer;
		this.warehouse = warehouse;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	// Constructors, getters, and setters
	
	
}
