package org.egov.fuber.repository;

import org.egov.fuber.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByName(String string);

	Customer findByNameAllIgnoringCase(String name);

	Customer findByEmailIdAllIgnoringCase(String emailId);

}
