package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class IntellectualSkillsSubjectResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	private Integer totalMarks;
//
//	private Double obtainedMarks;

	private Integer MidTotalMarks;
	private Integer finalTotalMarks;
	private Double midObtainedMarks;
	private Double finalObtainedMarks;

	private String serviceId;

	private Long subjectId;

	private Long termId;

	private Integer status;
	@Transient
	private String subjectName;

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

//	public Integer getTotalMarks() {
//		return totalMarks;
//	}
//
//	public void setTotalMarks(Integer totalMarks) {
//		this.totalMarks = totalMarks;
//	}
//
//	public Double getObtainedMarks() {
//		return obtainedMarks;
//	}
//
//	public void setObtainedMarks(Double obtainedMarks) {
//		this.obtainedMarks = obtainedMarks;
//	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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

	public Integer getMidTotalMarks() {
		return MidTotalMarks;
	}

	public void setMidTotalMarks(Integer midTotalMarks) {
		MidTotalMarks = midTotalMarks;
	}

	public Integer getFinalTotalMarks() {
		return finalTotalMarks;
	}

	public void setFinalTotalMarks(Integer finalTotalMarks) {
		this.finalTotalMarks = finalTotalMarks;
	}

	public Double getMidObtainedMarks() {
		return midObtainedMarks;
	}

	public void setMidObtainedMarks(Double midObtainedMarks) {
		this.midObtainedMarks = midObtainedMarks;
	}

	public Double getFinalObtainedMarks() {
		return finalObtainedMarks;
	}

	public void setFinalObtainedMarks(Double finalObtainedMarks) {
		this.finalObtainedMarks = finalObtainedMarks;
	}

}
