package org.egov.fuber.web.controller;

import java.util.Scanner;

import org.egov.fuber.entity.CabDetail;
import org.egov.fuber.entity.CabType;
import org.egov.fuber.service.CabDetailService;
import org.egov.fuber.service.CabTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CabDetailsController {

	@Autowired
	private CabDetailService cabDetailService;
	@Autowired
	private	CabTypeService cabTypeService;
	@RequestMapping("/cab")
	public String cabDetails() {

		Scanner scanner = new Scanner(System.in);

		System.out.println(" Please select...");
		System.out.println("1. New CabDetail");
		System.out.println("2. Existing CabDetail");
		int CabDetailtype = scanner.nextInt();
		String emailId, mobilenumber, username, address;

		if (CabDetailtype == 1) {
			createNewCabDetail(scanner);

		} else if (CabDetailtype == 2)

		{
			System.out.println(" Please enter your email id ..");
			emailId = scanner.next();

			CabDetail CabDetail = cabDetailService
					.findByEmailIdAllIgnoringCase(emailId);
			System.out.println("Welcome Driver" + CabDetail.getDriverName());

		}

		return null;
	}

	private void createNewCabDetail(Scanner scanner) {
		String emailId;
		String mobilenumber;
		String username;
		String colour,cabNumber,cabtype;
		System.out.print(" Please enter email id : ");
		emailId = checkEmailAlreadyPresent(scanner);
		System.out.println(" Please enter cab number : ");
		cabNumber = scanner.next();
		System.out.println(" Please enter Mobile number : ");
		mobilenumber = scanner.next();
		System.out.println(" Please enter your Name : ");
		username = scanner.next();
		System.out.println(" Please enter cab colour : ");
		colour = scanner.next();
		
		System.out.println(" Select Cab Type : ");
		for (CabType cabtypes: cabTypeService.findAll())
		{
			System.out.println(""+cabtypes.getId()+" "+cabtypes.getType());
		}
		cabtype = scanner.next();
		
		CabType cabTypeSelected=cabTypeService.findById(cabtype);
		
		CabDetail newCCabDetail = new CabDetail();
		newCCabDetail.setMobileNumber(mobilenumber);
		newCCabDetail.setEmailId(emailId);
		newCCabDetail.setDriverName(username);
		newCCabDetail.setCabNumber(cabNumber);
		newCCabDetail.setCabType(cabTypeSelected); 
		newCCabDetail.setActive(true);
		newCCabDetail.setColour(colour);
		
		newCCabDetail = cabDetailService.save(newCCabDetail);

		System.out.println(" CabDetail data saved successfully. "
				+ newCCabDetail.getId());
	}

	private String checkEmailAlreadyPresent(Scanner scanner) {
		// TODO: Add loop to check whether email id already present ?
		String emailId;
		emailId = scanner.next();
		CabDetail cabDetail = cabDetailService
				.findByEmailIdAllIgnoringCase(emailId);

		if (cabDetail != null) {
			System.out
					.println(" This email address already present please reenter ");
			emailId = scanner.next();
		}

		return emailId;
	}
}
