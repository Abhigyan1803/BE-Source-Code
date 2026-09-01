package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "gso2_service_subject_bmt_result")
public class GSO2ServiceSubjectBMTResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long termId;
	private String subjectType;
	private String assesmentTermType;
	private Integer status;
	private Double totalMarks;
	private Double obtainedMarks;
	private String serviceId;
	@Transient
	private Double midObtainedMarks;
	@Transient
	private Double finalObtainedMarks;
	@Transient
	private Long midId;
	@Transient
	private Long finalId;
	private Long userId;
	
	private String remarks;

	public Double getMidObtainedMarks() {
		return midObtainedMarks;
	}

	public void setMidObtainedMarks(Double midObtainedMarks) {
		this.midObtainedMarks = midObtainedMarks;
	}

	public Long getMidId() {
		return midId;
	}

	public void setMidId(Long midId) {
		this.midId = midId;
	}

	public Long getFinalId() {
		return finalId;
	}

	public void setFinalId(Long finalId) {
		this.finalId = finalId;
	}

	public Double getFinalObtainedMarks() {
		return finalObtainedMarks;
	}

	public void setFinalObtainedMarks(Double finalObtainedMarks) {
		this.finalObtainedMarks = finalObtainedMarks;
	}

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

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public String getAssesmentTermType() {
		return assesmentTermType;
	}

	public void setAssesmentTermType(String assesmentTermType) {
		this.assesmentTermType = assesmentTermType;
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

	public Double getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Double totalMarks) {
		this.totalMarks = totalMarks;
	}

	public Double getObtainedMarks() {
		return obtainedMarks;
	}

	public void setObtainedMarks(Double obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
