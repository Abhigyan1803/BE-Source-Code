package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ed_MidInterview")
public class EdMidInterview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serviceId;
	private Long termId;
	private Integer status;
	private Date date;
	@Lob
	private String details;
	private String capt;
	private String gcInitialsWithDate;
	private String submittedBy;
	@Transient
	private Boolean isViewByGc;

	public String getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getCapt() {
		return capt;
	}

	public void setCapt(String capt) {
		this.capt = capt;
	}

	public String getGcInitialsWithDate() {
		return gcInitialsWithDate;
	}

	public void setGcInitialsWithDate(String gcInitialsWithDate) {
		this.gcInitialsWithDate = gcInitialsWithDate;
	}

	public Boolean getIsViewByGc() {
		return isViewByGc;
	}

	public void setIsViewByGc(Boolean isViewByGc) {
		this.isViewByGc = isViewByGc;
	}

}
