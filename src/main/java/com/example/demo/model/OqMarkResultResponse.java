package com.example.demo.model;

import java.util.Date;
import java.util.List;

public class OqMarkResultResponse {

	private Long oqMarkResultId;
	private String serviceId;
	private Integer termId;
	private Long entryTypeId;
	private Long gcAppt;
	private Integer totalMarksPlCdr;
	private Double obtainedMarksPlCdr;
	private Integer totalMarksCoyCdr;
	private Double obtainedMarksCoyCdr;
	private Integer totalMarksBnCdr;
	private Double obtainedMarksBnCdr;
	private Integer status;
	private Date createdAt;
	private Date updatedAt;

	List<OqSubjectResultResponse> oqSubjectResultResponse;

	public Long getOqMarkResultId() {
		return oqMarkResultId;
	}

	public void setOqMarkResultId(Long oqMarkResultId) {
		this.oqMarkResultId = oqMarkResultId;
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

	public Long getEntryTypeId() {
		return entryTypeId;
	}

	public void setEntryTypeId(Long entryTypeId) {
		this.entryTypeId = entryTypeId;
	}

	public Long getGcAppt() {
		return gcAppt;
	}

	public void setGcAppt(Long gcAppt) {
		this.gcAppt = gcAppt;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<OqSubjectResultResponse> getOqSubjectResultResponse() {
		return oqSubjectResultResponse;
	}

	public void setOqSubjectResultResponse(List<OqSubjectResultResponse> oqSubjectResultResponse) {
		this.oqSubjectResultResponse = oqSubjectResultResponse;
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
