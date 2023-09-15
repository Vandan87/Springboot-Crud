package com.vandan.service;

import java.util.List;
import com.vandan.entity.Customer;

/**
 * This is the service interface for managing customer-related operations.
 * It defines method for interacting with customer data, like retrieval, creation, updating, deleting 
 * of customers, as well as checking the uniqueness of mobile numbers and email.  
 * 
 * @author VANDAN
 */
public interface CustomerService {

	List<Customer> getAllCustomers();
	
	Customer getCustomerById(Long id);
	
	Customer saveCustomer(Customer customer);
	
	Customer updateCustomer(Customer customer);
	
	void deleteCustomerById(Long id);
	
	boolean isMobileNumberUnique(Long id, Long mobile);
	
	boolean isEmailUnique(Long id, String email);

}