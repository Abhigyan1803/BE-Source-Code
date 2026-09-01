package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "drill_result")
public class DRILLResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String serviceId;
	private Long termId;
	private Integer totalMarks;
	private Double obtainedMarks;
	private String remarks;
	private Integer status;
	private String drillType;
	private String clearedIn;
	private String lastAttemptType;
	private Long userId;

	@Transient
	private Double m1ObtainedMarks;
	@Transient
	private Double m2ObtainedMarks;
	@Transient
	private Double c1ObtainedMarks;
	@Transient
	private Double c2ObtainedMarks;

	@OneToMany(cascade = CascadeType.ALL)
	private List<DRILLSubjectResult> dRILLSubjectResult;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "drill_attempt_id")
//	private DRILLAttemptResult dRILLAttemptResult;

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

	/*
	 * public Long getExerciseTypeId() { return exerciseTypeId; }
	 *
	 * public void setExerciseTypeId(Long exerciseTypeId) { this.exerciseTypeId =
	 * exerciseTypeId; }
	 *
	 * public Long getGcAppt() { return gcAppt; }
	 *
	 * public void setGcAppt(Long gcAppt) { this.gcAppt = gcAppt; }
	 */
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<DRILLSubjectResult> getdRILLSubjectResult() {
		return dRILLSubjectResult;
	}

	public void setdRILLSubjectResult(List<DRILLSubjectResult> dRILLSubjectResult) {
		this.dRILLSubjectResult = dRILLSubjectResult;
	}

//	public DRILLAttemptResult getdRILLAttemptResult() {
//		return dRILLAttemptResult;
//	}
//
//	public void setdRILLAttemptResult(DRILLAttemptResult dRILLAttemptResult) {
//		this.dRILLAttemptResult = dRILLAttemptResult;
//	}

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

	public String getDrillType() {
		return drillType;
	}

	public void setDrillType(String drillType) {
		this.drillType = drillType;
	}

	public String getClearedIn() {
		return clearedIn;
	}

	public void setClearedIn(String clearedIn) {
		this.clearedIn = clearedIn;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLastAttemptType() {
		return lastAttemptType;
	}

	public void setLastAttemptType(String lastAttemptType) {
		this.lastAttemptType = lastAttemptType;
	}

}
