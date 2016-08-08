package org.egov.fuber.web.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

import org.egov.fuber.entity.AvailableCabs;
import org.egov.fuber.entity.CabDetail;
import org.egov.fuber.entity.CabType;
import org.egov.fuber.entity.Location;
import org.egov.fuber.entity.TripDetails;
import org.egov.fuber.entity.TripStatus;
import org.egov.fuber.service.AvailableCabsService;
import org.egov.fuber.service.CabDetailService;
import org.egov.fuber.service.CabTypeService;
import org.egov.fuber.service.LocationService;
import org.egov.fuber.service.TripDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CabDetailsController {

	@Autowired
	private CabDetailService cabDetailService;

	@Autowired
	private AvailableCabsService availableCabsService;
	@Autowired
	private CabTypeService cabTypeService;
	@Autowired
	private TripDetailsService tripDetailsService;
	@Autowired
	private LocationService locationService;

	@RequestMapping("/cab")
	public String cabDetails() {

		Scanner scanner = new Scanner(System.in);

		System.out.println(" Please select...");
		System.out.println("1. New CabDetail");
		System.out.println("2. Existing CabDetail");
		int CabDetailtype = scanner.nextInt();
		String emailId, mobilenumber, username, address, waitOrExit, location;

		if (CabDetailtype == 1) {
			createNewCabDetail(scanner);

		} else if (CabDetailtype == 2)

		{
			System.out.println(" Please enter your email id ..");
			emailId = scanner.next();

			CabDetail cabDetail = cabDetailService
					.findByEmailIdAllIgnoringCase(emailId);

			if (cabDetail == null) {
				System.out.println("Invalid email Id.. Try again..");
			} else {
				System.out
						.println("Welcome Driver" + cabDetail.getDriverName());

				AvailableCabs cabPresentInList = availableCabsService
						.checkCabAlreadyInQueue(emailId);

				TripDetails tripDtl = tripDetailsService
						.checkTripAssignedToSelectedCab(emailId);

				// Mean cab already in available cab list.
				if (cabPresentInList != null) {
					System.out
							.println(" You are in queue. No cabs assigned yet. You want to go offline ?");
					System.out.println("Yes/No");
					waitOrExit = scanner.next();

					if (waitOrExit.equalsIgnoreCase("YES")) {
						System.out.println("Removing from list --------");

						// Remove from waiting list.
						availableCabsService
								.removeCabFromAvailableList(cabPresentInList);

						System.out.println("You are Offline Now. Thank You");
					} else {
						System.out
								.println("You are online. Please wait for next trip.");
					}
				} else if (tripDtl != null) { 
					if (tripDtl.getTripStatus() != null
							&& tripDtl.getTripStatus().equals(
									TripStatus.TRIP_ALLOTED)) {
						System.out.println(" Cab assigned. Trip number "
								+ tripDtl.getTripNumber());
						System.out.println(" You want to cancel trip ? Yes/No");

						waitOrExit = scanner.next();
						if (waitOrExit.equalsIgnoreCase("YES")) {
							System.out
									.println("Cancelling your trip. Please wait......");

							// Remove from waiting list.
							tripDtl.setTripStatus(TripStatus.DRIVERCANCELLED);
							tripDetailsService.updateTripDetails(tripDtl);

							// Add current cab into Available list.
							markCabAvailableOnCustomerCancellation(tripDtl,
									false);

							System.out
									.println("Your Last trip cancelled. Thank You");
						} else {
							tripDtl.setTripStatus(TripStatus.TRIP_STARTED);
							tripDtl.setTripStartTime(new Date());
							tripDetailsService.updateTripDetails(tripDtl);
							System.out.println("Trip Started...");
						}
					} else if (tripDtl.getTripStatus() != null
							&& tripDtl.getTripStatus().equals(
									TripStatus.TRIP_STARTED)) {

						System.out.println("Do you want to end trip ? Yes/No");
						waitOrExit = scanner.next();

						if (waitOrExit.equalsIgnoreCase("YES")) {
							System.out
									.println("Ending your trip. Please wait......");
							tripDtl.setTripEndTime(new Date());
							tripDtl.setTripStatus(TripStatus.TRIP_ENDED);
							tripDtl.setAmount(BigDecimal.TEN);// TODO we need to
																// calculate
																// later.
							tripDetailsService.updateTripDetails(tripDtl);
							markCabAvailableOnCustomerCancellation(tripDtl,
									true);
						}
						// TODO: GIVE OPTION TO SELECT OPTION TO START OR STOP
						// TRIP.
					}
				}else {
						System.out.println("Please select your location");
						for (Location locations : locationService.findAll()) {
							System.out.println("" + locations.getId() + " "
									+ locations.getName());
						}
						location = scanner.next(); // select location and go
													// online.

						Location locationObject = locationService
								.findById(location);
						// Go Online
						AvailableCabs addNewCabToAvailableList = new AvailableCabs();
						addNewCabToAvailableList.setCabDetail(cabDetail);
						addNewCabToAvailableList.setLocation(locationObject);
						availableCabsService
								.createAvailableCabs(addNewCabToAvailableList);
						System.out.println("You are online. Please wait ....");

					}
				}
			
		}
		return null;
	}

	private void markCabAvailableOnCustomerCancellation(TripDetails tripDtl,
			boolean useEndtripLocation) {
		AvailableCabs addCurrentCabAsAvailable = new AvailableCabs();
		addCurrentCabAsAvailable.setCabDetail(tripDtl.getCabDetail());
		if (useEndtripLocation)
			addCurrentCabAsAvailable.setLocation(tripDtl.getStartingPoint());
		else
			addCurrentCabAsAvailable.setLocation(tripDtl.getEndingPoint());
		availableCabsService.createAvailableCabs(addCurrentCabAsAvailable);
	}

	private void createNewCabDetail(Scanner scanner) {
		String emailId;
		String mobilenumber;
		String username;
		String colour, cabNumber, cabtype;
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
		for (CabType cabtypes : cabTypeService.findAll()) {
			System.out
					.println("" + cabtypes.getId() + " " + cabtypes.getType());
		}
		cabtype = scanner.next();

		CabType cabTypeSelected = cabTypeService.findById(cabtype);

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
