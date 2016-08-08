package org.egov.fuber.repository;

import java.util.List;

import org.egov.fuber.entity.TripDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripDetailsRepository extends JpaRepository<TripDetails, Long> {
	List<TripDetails> findAllByCustomerEmailId(String emailId);
}
