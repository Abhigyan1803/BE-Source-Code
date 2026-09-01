package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class PtMotivationAwards {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long termId;
	
	private String serviceId;
	
	private Integer status;
	
	private String meritCard;
	
	private String halfBlue;
	
	private String blue;
	
	private String blazer;
	
	private Integer totalMarks;
	
	private Double obtainedMarks;
	
	@CreationTimestamp()
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@CreationTimestamp()
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMeritCard() {
		return meritCard;
	}

	public void setMeritCard(String meritCard) {
		this.meritCard = meritCard;
	}

	public String getHalfBlue() {
		return halfBlue;
	}

	public void setHalfBlue(String halfBlue) {
		this.halfBlue = halfBlue;
	}

	public String getBlue() {
		return blue;
	}

	public void setBlue(String blue) {
		this.blue = blue;
	}

	public String getBlazer() {
		return blazer;
	}

	public void setBlazer(String blazer) {
		this.blazer = blazer;
	}

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}

	public Double getObtainedMarks() {
		return obtainedMarks;
	}

	public void setObtainedMarks(Double obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
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
