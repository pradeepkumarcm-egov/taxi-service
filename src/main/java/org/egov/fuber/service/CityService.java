package org.egov.fuber.service;

import org.egov.fuber.entity.City;
import org.egov.fuber.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Component("cityService")
@Transactional
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	public City getCity(String name, String country) {
		Assert.notNull(name, "Name must not be null");
		Assert.notNull(country, "Country must not be null");
		return this.cityRepository.findByNameAllIgnoringCase(name);
	}
}
