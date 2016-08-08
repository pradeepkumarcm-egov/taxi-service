package org.egov.fuber.repository;

import org.egov.fuber.entity.CabType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabTypeRepository extends JpaRepository<CabType, Long> {

}
