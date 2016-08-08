package org.egov.fuber.service;

import java.util.List;

import org.egov.fuber.entity.CabDetail;
import org.egov.fuber.repository.CabDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CabDetailService {
	@Autowired
	private CabDetailRepository cabDetailRepository;

	@Transactional
	public CabDetail createCabDetail(final CabDetail CabDetail) {
		return cabDetailRepository.save(CabDetail);
	}

	@Transactional
	public CabDetail updateCabDetail(final CabDetail CabDetail) {
		return cabDetailRepository.save(CabDetail);
	}

	public List<CabDetail> findAll() {
		return cabDetailRepository.findAll();
	}
}
