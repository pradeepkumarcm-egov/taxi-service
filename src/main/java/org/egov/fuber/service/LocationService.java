package org.egov.fuber.service;

import java.util.List;

import org.egov.fuber.entity.Location;
import org.egov.fuber.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LocationService {
	@Autowired
	private LocationRepository locationRepository;

	@Transactional
	public  Location createLocation(final  Location  location) {
		return  locationRepository.save( location);
	}

	@Transactional
	public  Location updateLocation(final  Location  location) {
		return  locationRepository.save( location);
	}

	public List< Location> findAll() {
		return  locationRepository.findAll();
	}

	public  Location findById(String  Location) {
		
		return  locationRepository.findOne(Long.valueOf( Location));
	}
}
