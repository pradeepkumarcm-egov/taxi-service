package org.egov.fuber.service;

import java.util.List;

import org.egov.fuber.entity.AvailableCabs;
import org.egov.fuber.repository.AvailableCabsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AvailableCabsService {
	@Autowired
	private AvailableCabsRepository availableCabsRepository;

	@Transactional
	public AvailableCabs createAvailableCabs(final AvailableCabs availableCabs) {
		return availableCabsRepository.save(availableCabs);
	}

	@Transactional
	public AvailableCabs updateAvailableCabs(final AvailableCabs availableCabs) {
		return availableCabsRepository.save(availableCabs);
	}

	public List<AvailableCabs> findAll() {
		return availableCabsRepository.findAll();
	}
}
