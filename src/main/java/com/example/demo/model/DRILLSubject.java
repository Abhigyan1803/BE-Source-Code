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
@Table(name = "drill_subject")
public class DRILLSubject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String subjectName;

	private Integer totalMarks;

	private Long termId;

	@Transient
	private String termName;

	private Integer status;

	private String drillType;

	@Transient
	private Double m1ObtainedMarks;

	@Transient
	private Double m2ObtainedMarks;

	@Transient
	private Double c1ObtainedMarks;

	@Transient
	private Double c2ObtainedMarks;

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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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

	@Override
	public String toString() {
		return "DRILLSubject [id=" + id + ", subjectName=" + subjectName + ", totalMark=" + totalMarks + ", termId="
				+ termId + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	public String getDrillType() {
		return drillType;
	}

	public void setDrillType(String drillType) {
		this.drillType = drillType;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
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

}
