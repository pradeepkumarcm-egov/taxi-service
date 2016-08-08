package org.egov.fuber.web.controller;

import java.util.Scanner;

import org.egov.fuber.eGovProperties;
import org.egov.fuber.entity.Customer;
import org.egov.fuber.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	@Autowired
	eGovProperties props;
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/customer")
	public String customer() {

		Scanner scanner = new Scanner(System.in);

		System.out.println(" Please select...");
		System.out.println("1. New Customer");
		System.out.println("2. Existing Customer");
		int customertype = scanner.nextInt();
		String emailId, mobilenumber, username, address;

		if (customertype == 1) {
			createNewCustomer(scanner);

		} else if (customertype == 2)

		{
			System.out.println(" Please enter your email id ..");
			emailId = scanner.next();

			Customer customer = customerService
					.findByEmailIdAllIgnoringCase(emailId);
			System.out.println("Welcome " + customer.getName());

		}

		return null;
	}

	private void createNewCustomer(Scanner scanner) {
		String emailId;
		String mobilenumber;
		String username;
		String address;
		System.out.print(" Please enter email id : ");
		emailId = checkEmailAlreadyPresent(scanner);
		System.out.println(" Please enter mobile number : ");
		mobilenumber = scanner.next();
		System.out.println(" Please enter your Name : ");
		username = scanner.next();
		System.out.println(" Please enter your address : ");
		address = scanner.next();
		Customer newCcustomer = new Customer();
		newCcustomer.setMobileNumber(mobilenumber);
		newCcustomer.setEmailId(emailId);
		newCcustomer.setName(username);
		newCcustomer.setAddress(address);
		newCcustomer = customerService.save(newCcustomer);

		System.out.println(" Customer data saved successfully. "
				+ newCcustomer.getId());
	}

	private String checkEmailAlreadyPresent(Scanner scanner) {
		// TODO: Add loop to check whether email id already present ?
		String emailId;
		emailId = scanner.next();
		Customer customer = customerService
				.findByEmailIdAllIgnoringCase(emailId);

		if (customer != null) {
			System.out
					.println(" This email address already present please reenter ");
			emailId = scanner.next();
		}

		return emailId;
	}
}
