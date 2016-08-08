package org.egov.fuber.repository;

import org.egov.fuber.entity.CabDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabDetailRepository extends JpaRepository<CabDetail, Long> {

	CabDetail findByEmailId(String emailId);

}
