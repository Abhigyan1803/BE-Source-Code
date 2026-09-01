package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "ros_family_details_a")
public class RosFamilyDetailsA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date marriageDate;

	private String marriagePlace;

	private String law = "";

	private String toWhom = "";

	private String spouseNationality = "";

	private String authority = "";

	@ManyToOne
	private Officer officer;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getMarriageDate() {
		return marriageDate;
	}

	public void setMarriageDate(Date marriageDate) {
		this.marriageDate = marriageDate;
	}

	public String getMarriagePlace() {
		return marriagePlace;
	}

	public void setMarriagePlace(String marriagePlace) {
		this.marriagePlace = marriagePlace;
	}

	public String getLaw() {
		return law;
	}

	public void setLaw(String law) {
		this.law = law;
	}

	public String getToWhom() {
		return toWhom;
	}

	public void setToWhom(String toWhom) {
		this.toWhom = toWhom;
	}

	public String getSpouseNationality() {
		return spouseNationality;
	}

	public void setSpouseNationality(String spouseNationality) {
		this.spouseNationality = spouseNationality;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

}
