package org.egov.fuber.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Table(name = "CABDETAIL")
public class CabDetail {
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Column(name = "cabNumber", unique = true)
	private String cabNumber;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "cabType")
	private CabType cabType;
	private String colour;
	private String driverName;
	private boolean active;

	@NotNull
	@Length(max = 128)
	@Column(name = "emailId", unique = true)
	private String emailId;

	@NotNull
	@Column(name = "mobileNumber", unique = true)
	@Length(max = 15)
	private String mobileNumber;

	@OrderBy("ID DESC")
	@OneToMany(mappedBy = "cabDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TripDetails> tripDetails = new HashSet<TripDetails>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCabNumber() {
		return cabNumber;
	}

	public void setCabNumber(String cabNumber) {
		this.cabNumber = cabNumber;
	}

	public CabType getCabType() {
		return cabType;
	}

	public void setCabType(CabType cabType) {
		this.cabType = cabType;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Set<TripDetails> getTripDetails() {
		return tripDetails;
	}

	public void setTripDetails(Set<TripDetails> tripDetails) {
		this.tripDetails = tripDetails;
	}

}
