package com.vandan.controller;

import java.sql.Date;
import java.text.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vandan.entity.Customer;
import com.vandan.service.CustomerService;

/**
 * This is the controller class for mapping customer-related operations.
 * It handles requests related to customers, such as listing, creating, updating and deleting customers.
 * 
 * @author VANDAN
 */
@Controller
public class CustomerController {

	private final CustomerService customerService;
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	// handler method to handle list customers and return mode and view
	@GetMapping("/customers")
	public String listCustomers(Model model) {
		model.addAttribute("customers", customerService.getAllCustomers());
		return "customers";
	}

	@GetMapping("/customers/new")
	public String createOrUpdateCustomerForm(@RequestParam(required = false) Long id, Model model) {
		Customer customer = new Customer();
		if (id != null) {
			// If id is provided, it's an update operation
			customer = customerService.getCustomerById(id);
		} 
		model.addAttribute("customer", customer);
		model.addAttribute("mobileNotUnique", false);
		return "create_customer";
	}

	@PostMapping("/customers")
	public String saveOrUpdateCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes, Model model) {
		boolean mobileNotUnique = customerService.isMobileNumberUnique(customer.getId(), customer.getMobile());
		boolean emailNotUnique = customerService.isEmailUnique(customer.getId(), customer.getEmail());
		boolean dateOfBirthValid = setDateOfBirth(customer, model);
		 
		 if (mobileNotUnique || emailNotUnique || !dateOfBirthValid) {
		        if (mobileNotUnique) {
		            model.addAttribute("mobileNotUnique", true);
		            model.addAttribute("resetMobileField", true);
		        }
		        if (emailNotUnique) {
		            model.addAttribute("emailNotUnique", true);
		            model.addAttribute("resetEmailField", true);
		        }
		        return "create_customer"; // Show the form again with the error message
		    }		 
		if (customer.getId() != null) {
			customerService.updateCustomer(customer);
			redirectAttributes.addFlashAttribute("message", "Customer updated successfully");
		} else {
			customerService.saveCustomer(customer);
			redirectAttributes.addFlashAttribute("message", "Customer saved successfully");			
		}	
		return "redirect:/customers";
	}
	
	private boolean setDateOfBirth(Customer customer, Model model) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date parsedDate = new Date(dateFormat.parse(customer.getFormattedDateOfBirth()).getTime());
            customer.setDateOfBirth(parsedDate);
            return true;
        } catch (ParseException e) {
            // Handle date parsing exception
            model.addAttribute("dateOfBirthError");
            return false;
        }
    }

	// Handler method to handle delete customer request
	@GetMapping("/customers/{id}")
	public String deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomerById(id);
		return "redirect:/customers";
	}

}