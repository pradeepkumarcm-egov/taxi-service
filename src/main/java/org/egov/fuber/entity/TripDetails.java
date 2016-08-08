package org.egov.fuber.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TRIPDETAILS")
public class TripDetails {

	@Id
	@GeneratedValue
	private Long id;

	@GeneratedValue
	private String tripNumber;
	@NotNull
	private Date tripDate;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "customer")
	private Customer customer;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "cabDetail")
	private CabDetail cabDetail;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "startingPoint")
	private Location startingPoint;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "endingPoint")
	private Location endingPoint;

	@Enumerated(EnumType.STRING)
	private TripStatus tripStatus;
	private Date tripStartTime;
	private Date tripEndTime;
	private BigDecimal amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTripNumber() {
		return tripNumber;
	}

	public void setTripNumber(String tripNumber) {
		this.tripNumber = tripNumber;
	}

	public Date getTripDate() {
		return tripDate;
	}

	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CabDetail getCabDetail() {
		return cabDetail;
	}

	public void setCabDetail(CabDetail cabDetail) {
		this.cabDetail = cabDetail;
	}

	public Location getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(Location startingPoint) {
		this.startingPoint = startingPoint;
	}

	public Location getEndingPoint() {
		return endingPoint;
	}

	public void setEndingPoint(Location endingPoint) {
		this.endingPoint = endingPoint;
	}

	public TripStatus getTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(TripStatus tripStatus) {
		this.tripStatus = tripStatus;
	}

	public Date getTripStartTime() {
		return tripStartTime;
	}

	public void setTripStartTime(Date tripStartTime) {
		this.tripStartTime = tripStartTime;
	}

	public Date getTripEndTime() {
		return tripEndTime;
	}

	public void setTripEndTime(Date tripEndTime) {
		this.tripEndTime = tripEndTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
