package org.egov.fuber.repository;

import org.egov.fuber.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	City findByName(String string);

	City findByNameAllIgnoringCase(String name);

}
