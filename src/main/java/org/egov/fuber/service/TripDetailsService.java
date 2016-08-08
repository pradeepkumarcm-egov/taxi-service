package org.egov.fuber.service;

import java.util.List;

import org.egov.fuber.entity.TripDetails;
import org.egov.fuber.repository.TripDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TripDetailsService {
	@Autowired
	private TripDetailsRepository tripDetailsRepository;

	@Transactional
	public TripDetails createTripDetails(final TripDetails tripDetails) {
		return tripDetailsRepository.save(tripDetails);
	}

	@Transactional
	public TripDetails updateTripDetails(final TripDetails tripDetails) {
		return tripDetailsRepository.save(tripDetails);
	}

	public List<TripDetails> findAll() {
		return tripDetailsRepository.findAll();
	}

	public TripDetails checkTripAssignedToSelectedCab(String emailId) {
		return tripDetailsRepository.checkTripAssignedToSelectedCab(emailId);
	}
}
