package com.vandan.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.vandan.entity.Customer;
import com.vandan.repository.CustomerRepository;
import com.vandan.service.CustomerService;

/**
 * This is the implementation of the CustomerService interface.
 * It provides method to perform various operation related to customer management.
 * 
 * @author VANDAN
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		if (customer.getId() != null && customerRepository.existsById(customer.getId())) {
            return customerRepository.save(customer);
        }
        return null;
	}

	@Override
	@Transactional
	public void deleteCustomerById(Long id) {
		customerRepository.deleteById(id);
	}
	
	@Override
    public boolean isMobileNumberUnique(Long id, Long mobile) {
        Customer customerWithSameMobile = customerRepository.findByMobile(mobile);
        if (customerWithSameMobile != null) {
            // Check if it's the same customer (for update operation)
            if (id != null && id.equals(customerWithSameMobile.getId())) {
                return false; // It's the same customer's mobile number
            }
            return true; // It's a different customer with the same mobile number
        }
        return false; // Mobile number is unique
    }
	
	@Override
	public boolean isEmailUnique(Long id, String email) {
	    Customer customerWithSameEmail = customerRepository.findByEmail(email);
	    if (customerWithSameEmail != null) {
	        // Check if it's the same customer (for update operation)
	        if (id != null && id.equals(customerWithSameEmail.getId())) {
	            return false; // It's the same customer's email
	        }
	        return true; // It's a different customer with the same email
	    }
	    return false; // Email is unique
	}

}