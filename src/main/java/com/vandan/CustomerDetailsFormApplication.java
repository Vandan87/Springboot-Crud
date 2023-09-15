package com.vandan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vandan.repository.CustomerRepository;

/**
 * This is the main class for the Customer Details Form Application.
 * 
 * @author VANDAN
 */
@SpringBootApplication
public class CustomerDetailsFormApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CustomerDetailsFormApplication.class, args);
	}

	@SuppressWarnings("unused")
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
	}

}