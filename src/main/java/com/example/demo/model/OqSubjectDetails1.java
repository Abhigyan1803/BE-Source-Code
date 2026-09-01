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

@Entity
@Table(name = "oq_subject_details1")
public class OqSubjectDetails1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String subjectName;
	private Integer totalMarksPlCdr;
	private Integer totalMarksCoyCdr;
	private Integer totalMarksBnCdr;

	private Integer status;

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

	public Integer getTotalMarksPlCdr() {
		return totalMarksPlCdr;
	}

	public void setTotalMarksPlCdr(Integer totalMarksPlCdr) {
		this.totalMarksPlCdr = totalMarksPlCdr;
	}

	public Integer getTotalMarksCoyCdr() {
		return totalMarksCoyCdr;
	}

	public void setTotalMarksCoyCdr(Integer totalMarksCoyCdr) {
		this.totalMarksCoyCdr = totalMarksCoyCdr;
	}

	public Integer getTotalMarksBnCdr() {
		return totalMarksBnCdr;
	}

	public void setTotalMarksBnCdr(Integer totalMarksBnCdr) {
		this.totalMarksBnCdr = totalMarksBnCdr;
	}

}
