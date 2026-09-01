package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class EdCampMarks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String serviceId;

	private Long termId;

	@Lob
	private String campPerformance;

	private String spotTest;

	private String coyPerformance;

	private String totalCampMarks;

	private String signature;

	private Integer Status;

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

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public String getCampPerformance() {
		return campPerformance;
	}

	public void setCampPerformance(String campPerformance) {
		this.campPerformance = campPerformance;
	}

	public String getSpotTest() {
		return spotTest;
	}

	public void setSpotTest(String spotTest) {
		this.spotTest = spotTest;
	}

	public String getCoyPerformance() {
		return coyPerformance;
	}

	public void setCoyPerformance(String coyPerformance) {
		this.coyPerformance = coyPerformance;
	}

	public String getTotalCampMarks() {
		return totalCampMarks;
	}

	public void setTotalCampMarks(String totalCampMarks) {
		this.totalCampMarks = totalCampMarks;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
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
