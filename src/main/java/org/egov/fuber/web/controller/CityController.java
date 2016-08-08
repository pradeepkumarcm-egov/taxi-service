package org.egov.fuber.web.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.egov.fuber.eGovProperties;
import org.egov.fuber.entity.City;
import org.egov.fuber.service.CabTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

	@Autowired
	eGovProperties props;
	@Autowired
	private CabTypeService cabTypeService;
	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping("/hello")
	public String hello(@RequestParam String name) {

		City city = entityManager.find(City.class, 3l);
		return props.getGreeting() + name + " to " + city.getName();

	}
}
