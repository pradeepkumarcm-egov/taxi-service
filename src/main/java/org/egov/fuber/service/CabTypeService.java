package org.egov.fuber.service;

import java.util.List;

import org.egov.fuber.entity.CabType;
import org.egov.fuber.repository.CabTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CabTypeService {
	@Autowired
	private CabTypeRepository cabTypeRepository;

	@Transactional
	public CabType createCabType(final CabType CabType) {
		return cabTypeRepository.save(CabType);
	}

	@Transactional
	public CabType updateCabType(final CabType CabType) {
		return cabTypeRepository.save(CabType);
	}

	public List<CabType> findAll() {
		return cabTypeRepository.findAll();
	}
}
