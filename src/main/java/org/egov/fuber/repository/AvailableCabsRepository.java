package org.egov.fuber.repository;

import java.util.List;

import org.egov.fuber.entity.AvailableCabs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableCabsRepository extends
		JpaRepository<AvailableCabs, Long> {

	AvailableCabs findByCabDetailEmailId(String emailId);

	@Query("select u from AvailableCabs u where u.location.latitude = :latitudes")
	List<AvailableCabs> findCabNearByLocation(@Param("latitudes") Long latitudes );

}
