package org.egov.fuber.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.egov.fuber.eGovProperties;
import org.egov.fuber.entity.AvailableCabs;
import org.egov.fuber.entity.Customer;
import org.egov.fuber.entity.Location;
import org.egov.fuber.entity.TripDetails;
import org.egov.fuber.entity.TripStatus;
import org.egov.fuber.service.AvailableCabsService;
import org.egov.fuber.service.CustomerService;
import org.egov.fuber.service.LocationService;
import org.egov.fuber.service.TripDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	@Autowired
	eGovProperties props;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private TripDetailsService tripDetailsService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private AvailableCabsService availableCabsService;
	
	@RequestMapping("/customer")
	public String customer() {

		Scanner scanner = new Scanner(System.in);

		System.out.println(" Please select...");
		System.out.println("1. New Customer");
		System.out.println("2. Existing Customer");
		int customertype = scanner.nextInt();
		String emailId, mobilenumber, username, address,waitOrExit,startlocation,endlocation;

		if (customertype == 1) {
			createNewCustomer(scanner);

		} else if (customertype == 2)

		{
			System.out.println(" Please enter your email id ..");
			emailId = scanner.next();

			Customer customer = customerService
					.findByEmailIdAllIgnoringCase(emailId);
			if (customer == null)
				System.out.println("Invalid input. Try again.");
			else
			{
				System.out.println("Welcome " + customer.getName());
				//check any trip already booked ?
				TripDetails tripDtl= tripDetailsService.checkTripAssignedToSelectedCustomer(emailId);
				
				if (tripDtl!=null)
				{
					if(tripDtl.getTripStatus()!=null && tripDtl.getTripStatus().equals(TripStatus.TRIP_ALLOTED))
					{
						System.out.println(" Cab assigned on your last request." + tripDtl.getTripNumber());
						System.out.println(" You want to cancel trip ? Yes/No" );
					
						waitOrExit = scanner.next();
						if (waitOrExit.equalsIgnoreCase("YES")) {
							System.out
							.println("Cancelling your trip. Please wait......");
							
							// Remove from waiting list.
							tripDtl.setTripStatus(TripStatus.CUSTOMERCANCELLED);
							tripDetailsService.updateTripDetails(tripDtl);
							
							//Add current cab into Available list.
								markCabAvailableOnCustomerCancellation(tripDtl);
							
							System.out
							.println("Your Last trip cancelled. Thank You");
						} else {
							System.out
									.println("Happy Journey...");
						}
					}else if(tripDtl.getTripStatus()!=null && tripDtl.getTripStatus().equals(TripStatus.TRIP_STARTED))
					{
						System.out
						.println("Trip Started.Happy Journey...");
					}
				}else
				{
					System.out
					.println("Please select starting point ");
					
					for (Location locations : locationService.findAll()) {
						System.out.println("" + locations.getId() + " "
								+ locations.getName());
					}
					startlocation = scanner.next(); 
					System.out
					.println("Please select end point ");
					endlocation = scanner.next(); 
					Location startLoctionObject = locationService.findById(startlocation);
					Location endLoctionObject = locationService.findById(endlocation);
					
					 List<AvailableCabs> availableCabList=	availableCabsService.findCabNearByLocation(startLoctionObject);
					 if(availableCabList.size()>0){
						for (AvailableCabs availableCabs : availableCabList) {
							System.out.println("" + availableCabs.getCabDetail().getCabType().getType() + " "
									+ availableCabs.getCabDetail().getColour());
						}
						System.out
						.println("---------------------------------------------------------------- ");
						System.out
						.println("Book Cab ? Yes/No ");
						waitOrExit = scanner.next();
						if (waitOrExit.equalsIgnoreCase("YES")) {
							TripDetails tripDetails =	buildTripDetails(customer, startLoctionObject,
									endLoctionObject, availableCabList);
							System.out
							.println("Booking confirmed with Number " + tripDetails.getTripNumber() + " Vehicle number " + tripDetails.getCabDetail().getCabNumber() );
						}else
						{
							System.out
							.println("Thanks for your login.");
						}
						
						
					 }else
						 System.out
							.println("There is no cabs avaialble.Try after some time. ");	
					
				}
				//for new book select location details and show existing cab details
				
			}
		}

		return null;
	}

	private TripDetails buildTripDetails(Customer customer,
			Location startLoctionObject, Location endLoctionObject,
			List<AvailableCabs> availableCabList) {
		TripDetails tripDetails = new TripDetails();
		tripDetails.setCabDetail(availableCabList.get(0).getCabDetail());
		tripDetails.setCustomer(customer);
		tripDetails.setStartingPoint(startLoctionObject);
		tripDetails.setStartingPoint(endLoctionObject);
		tripDetails.setTripDate(new Date());
		tripDetails.setTripStatus(TripStatus.TRIP_ALLOTED);
		tripDetailsService.createTripDetails(tripDetails);
		return tripDetails;
	}

	private void markCabAvailableOnCustomerCancellation(TripDetails tripDtl) {
		AvailableCabs addCurrentCabAsAvailable=new AvailableCabs();
		addCurrentCabAsAvailable.setCabDetail(tripDtl.getCabDetail());
		addCurrentCabAsAvailable.setLocation(tripDtl.getStartingPoint());
		availableCabsService.createAvailableCabs(addCurrentCabAsAvailable);
	}

	private void createNewCustomer(Scanner scanner) {
		String emailId;
		String mobilenumber;
		String username;
		String address;
		System.out.print(" Please enter email id : ");
		emailId = checkEmailAlreadyPresent(scanner);
		System.out.println(" Please enter mobile number : ");
		mobilenumber = scanner.next();
		System.out.println(" Please enter your Name : ");
		username = scanner.next();
		System.out.println(" Please enter your address : ");
		address = scanner.next();
		Customer newCcustomer = new Customer();
		newCcustomer.setMobileNumber(mobilenumber);
		newCcustomer.setEmailId(emailId);
		newCcustomer.setName(username);
		newCcustomer.setAddress(address);
		newCcustomer = customerService.save(newCcustomer);

		System.out.println(" Customer data saved successfully. "
				+ newCcustomer.getId());
	}

	private String checkEmailAlreadyPresent(Scanner scanner) {
		// TODO: Add loop to check whether email id already present ?
		String emailId;
		emailId = scanner.next();
		Customer customer = customerService
				.findByEmailIdAllIgnoringCase(emailId);

		if (customer != null) {
			System.out
					.println(" This email address already present please reenter ");
			emailId = scanner.next();
		}

		return emailId;
	}
}
