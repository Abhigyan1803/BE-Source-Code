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
@Table(name = "oq_subject_result")
public class OqSubjectResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer totalMarksPlCdr;
	private Double obtainedMarksPlCdr;
	private Integer totalMarksCoyCdr;
	private Double obtainedMarksCoyCdr;
	private Integer totalMarksBnCdr;
	private Double obtainedMarksBnCdr;
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

	public Integer getTotalMarksPlCdr() {
		return totalMarksPlCdr;
	}

	public void setTotalMarksPlCdr(Integer totalMarksPlCdr) {
		this.totalMarksPlCdr = totalMarksPlCdr;
	}

	public Double getObtainedMarksPlCdr() {
		return obtainedMarksPlCdr;
	}

	public void setObtainedMarksPlCdr(Double obtainedMarksPlCdr) {
		this.obtainedMarksPlCdr = obtainedMarksPlCdr;
	}

	public Integer getTotalMarksCoyCdr() {
		return totalMarksCoyCdr;
	}

	public void setTotalMarksCoyCdr(Integer totalMarksCoyCdr) {
		this.totalMarksCoyCdr = totalMarksCoyCdr;
	}

	public Double getObtainedMarksCoyCdr() {
		return obtainedMarksCoyCdr;
	}

	public void setObtainedMarksCoyCdr(Double obtainedMarksCoyCdr) {
		this.obtainedMarksCoyCdr = obtainedMarksCoyCdr;
	}

	public Integer getTotalMarksBnCdr() {
		return totalMarksBnCdr;
	}

	public void setTotalMarksBnCdr(Integer totalMarksBnCdr) {
		this.totalMarksBnCdr = totalMarksBnCdr;
	}

	public Double getObtainedMarksBnCdr() {
		return obtainedMarksBnCdr;
	}

	public void setObtainedMarksBnCdr(Double obtainedMarksBnCdr) {
		this.obtainedMarksBnCdr = obtainedMarksBnCdr;
	}

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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}
