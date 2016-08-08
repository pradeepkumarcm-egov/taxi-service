package org.egov.fuber.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Column(name = "name")
	private String name;

	@NotNull
	@Length(max = 128)
	@Column(name = "emailId", unique = true)
	private String emailId;

	@NotNull
	@Column(name = "mobileNumber", unique = true)
	@Length(max = 15)
	private String mobileNumber;
	private String address;

	@OrderBy("ID DESC")
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TripDetails> tripDetails = new HashSet<TripDetails>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<TripDetails> getTripDetails() {
		return tripDetails;
	}

	public void setTripDetails(Set<TripDetails> tripDetails) {
		this.tripDetails = tripDetails;
	}

}
