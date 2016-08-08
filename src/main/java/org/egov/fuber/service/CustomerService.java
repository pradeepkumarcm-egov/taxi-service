package org.egov.fuber.service;

import java.util.List;

import org.egov.fuber.entity.Customer;
import org.egov.fuber.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Transactional
	public Customer createCustomer(final Customer customer) {
		return customerRepository.save(customer);
	}

	@Transactional
	public Customer updateCustomer(final Customer customer) {
		return customerRepository.save(customer);
	}

	@Transactional
	public Customer findByEmailIdAllIgnoringCase(final String emailId) {
		return customerRepository.findByEmailIdAllIgnoringCase(emailId);
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Customer save(Customer newCcustomer) {
		return customerRepository.save(newCcustomer);

	}
}
