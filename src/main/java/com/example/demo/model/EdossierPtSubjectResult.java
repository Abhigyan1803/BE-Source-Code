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
public class EdossierPtSubjectResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer totalMarks;

	private Double m1ObtainedMarks;

	private Double m2ObtainedMarks;

	private Double c1ObtainedMarks;

	private Double c2ObtainedMarks;

	private String serviceId;

	private Long subjectId;

	private Long termId;

	private Integer status;

	@Transient
	private String subjectName;

	private String subjectCategory;

	private String subjectType;

	private String lastAttemptType;

	private String clearedIn;

	private String resultGrade;

	private String resultSubGrade;

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

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getLastAttemptType() {
		return lastAttemptType;
	}

	public void setLastAttemptType(String lastAttemptType) {
		this.lastAttemptType = lastAttemptType;
	}

	public String getResultGrade() {
		return resultGrade;
	}

	public void setResultGrade(String resultGrade) {
		this.resultGrade = resultGrade;
	}

	public String getResultSubGrade() {
		return resultSubGrade;
	}

	public void setResultSubGrade(String resultSubGrade) {
		this.resultSubGrade = resultSubGrade;
	}

	public String getSubjectCategory() {
		return subjectCategory;
	}

	public void setSubjectCategory(String subjectCategory) {
		this.subjectCategory = subjectCategory;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
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

	public Double getM1ObtainedMarks() {
		return m1ObtainedMarks;
	}

	public void setM1ObtainedMarks(Double m1ObtainedMarks) {
		this.m1ObtainedMarks = m1ObtainedMarks;
	}

	public Double getM2ObtainedMarks() {
		return m2ObtainedMarks;
	}

	public void setM2ObtainedMarks(Double m2ObtainedMarks) {
		this.m2ObtainedMarks = m2ObtainedMarks;
	}

	public Double getC1ObtainedMarks() {
		return c1ObtainedMarks;
	}

	public void setC1ObtainedMarks(Double c1ObtainedMarks) {
		this.c1ObtainedMarks = c1ObtainedMarks;
	}

	public Double getC2ObtainedMarks() {
		return c2ObtainedMarks;
	}

	public void setC2ObtainedMarks(Double c2ObtainedMarks) {
		this.c2ObtainedMarks = c2ObtainedMarks;
	}

	public String getClearedIn() {
		return clearedIn;
	}

	public void setClearedIn(String clearedIn) {
		this.clearedIn = clearedIn;
	}
}
