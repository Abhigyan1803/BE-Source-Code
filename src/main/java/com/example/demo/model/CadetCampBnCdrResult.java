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
@Table(name="camp_bncdr_result")
public class CadetCampBnCdrResult {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String serviceId;

	@ManyToOne
	private CampAttribute campAttributes;
	
	private Integer bnCdrMarks;

	private Long termId;

	private Integer status;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	
	private Long apptType;
	
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

	public CampAttribute getCampAttributes() {
		return campAttributes;
	}

	public void setCampAttributes(CampAttribute campAttributes) {
		this.campAttributes = campAttributes;
	}


	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getBnCdrMarks() {
		return bnCdrMarks;
	}

	public void setBnCdrMarks(Integer bnCdrMarks) {
		this.bnCdrMarks = bnCdrMarks;
	}

	public Long getApptType() {
		return apptType;
	}

	public void setApptType(Long apptType) {
		this.apptType = apptType;
	}

		
	
	
	
	}
