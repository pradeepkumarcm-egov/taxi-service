package org.egov.fuber.repository;

import java.util.List;

import org.egov.fuber.entity.TripDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TripDetailsRepository extends JpaRepository<TripDetails, Long> {
	List<TripDetails> findAllByCustomerEmailId(String emailId);
	
	@Query("select u from TripDetails u where u.cabDetail.emailId = :emailIds and u.tripStatus in ('TRIP_ALLOTED','TRIP_STARTED')")
	TripDetails checkTripAssignedToSelectedCab(@Param("emailIds") String emailIds);

	@Query("select u from TripDetails u where u.customer.emailId = :emailIds and u.tripStatus in ('TRIP_ALLOTED','TRIP_STARTED')")
	TripDetails checkTripAssignedToSelectedCustomer(@Param("emailIds") String emailId);
}
