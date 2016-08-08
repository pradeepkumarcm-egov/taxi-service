package org.egov.fuber.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AVAILABLECABS")
public class AvailableCabs {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "location")
	private Location location;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cabDetail")
	private CabDetail cabDetail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public CabDetail getCabDetail() {
		return cabDetail;
	}

	public void setCabDetail(CabDetail cabDetail) {
		this.cabDetail = cabDetail;
	}

}
