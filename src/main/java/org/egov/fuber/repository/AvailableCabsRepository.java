package org.egov.fuber.repository;

import org.egov.fuber.entity.AvailableCabs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableCabsRepository extends
		JpaRepository<AvailableCabs, Long> {

}
