package org.egov.fuber.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CITY")
public class City implements Serializable {

	private static final long serialVersionUID = 220973515708766785L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	protected City() {
	}

	public City(Long i, String firstname) {
		super();
		this.id = i;
		this.name = firstname;
	}

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

	@Override
	public String toString() {
		return getName();
	}
}
