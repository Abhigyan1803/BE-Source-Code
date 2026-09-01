package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "camp_marks_result")
public class CampMarksResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String serviceId;
	private Integer termId;
	private Long exerciseTypeId;
	private Long gcAppt;
	private Integer totalMarks;
	private Double obtainedMarks;
	private String remarks;
	private Integer status;
	private Long userId;

	@OneToMany
	private List<CampSubjectResult> campSubjectResult;

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

	public Integer getTermId() {
		return termId;
	}

	public void setTermId(Integer termId) {
		this.termId = termId;
	}

	public Long getGcAppt() {
		return gcAppt;
	}

	public void setGcAppt(Long gcAppt) {
		this.gcAppt = gcAppt;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<CampSubjectResult> getCampSubjectResult() {
		return campSubjectResult;
	}

	public void setCampSubjectResult(List<CampSubjectResult> campSubjectResult) {
		this.campSubjectResult = campSubjectResult;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getExerciseTypeId() {
		return exerciseTypeId;
	}

	public void setExerciseTypeId(Long exerciseTypeId) {
		this.exerciseTypeId = exerciseTypeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
