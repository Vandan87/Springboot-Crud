package com.vandan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vandan.entity.Customer;

/**
 * This is the repository interface for managing customer data.
 * It extends JpaRepository to provide basic CRUD operations for the Customer entity.
 * 
 * @author VANDAN
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findById(Long id);
	
	void deleteById(Long id);
	
	boolean existsById(Long id);
	
	Customer findByMobile(Long mobile);
	
	Customer findByEmail(String email);

}