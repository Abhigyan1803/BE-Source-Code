package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Table
@Entity(name = "vaccination_details")
public class VaccinationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nameOfVaccine;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date DateOfFistDose;
	private String fistDoseReferenceId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date DateOfSecDose;
	private String secDoseReferenceId;

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

	public String getNameOfVaccine() {
		return nameOfVaccine;
	}

	public void setNameOfVaccine(String nameOfVaccine) {
		this.nameOfVaccine = nameOfVaccine;
	}

	public Date getDateOfFistDose() {
		return DateOfFistDose;
	}

	public void setDateOfFistDose(Date dateOfFistDose) {
		DateOfFistDose = dateOfFistDose;
	}

	public String getFistDoseReferenceId() {
		return fistDoseReferenceId;
	}

	public void setFistDoseReferenceId(String fistDoseReferenceId) {
		this.fistDoseReferenceId = fistDoseReferenceId;
	}

	public Date getDateOfSecDose() {
		return DateOfSecDose;
	}

	public void setDateOfSecDose(Date dateOfSecDose) {
		DateOfSecDose = dateOfSecDose;
	}

	public String getSecDoseReferenceId() {
		return secDoseReferenceId;
	}

	public void setSecDoseReferenceId(String secDoseReferenceId) {
		this.secDoseReferenceId = secDoseReferenceId;
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

}
