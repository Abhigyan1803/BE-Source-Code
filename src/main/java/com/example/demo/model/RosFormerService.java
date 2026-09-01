package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class RosFormerService implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String preCommissionService;

	private String regtCorpsOfficeDeptt;

	private Date dateFrom;

	private Date dateTo;

	private String totalServiceExperience;

	@Lob
	private String remark;

	@ManyToOne
	private Officer officer;

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

	public String getPreCommissionService() {
		return preCommissionService;
	}

	public void setPreCommissionService(String preCommissionService) {
		this.preCommissionService = preCommissionService;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getTotalServiceExperience() {
		return totalServiceExperience;
	}

	public void setTotalServiceExperience(String totalServiceExperience) {
		this.totalServiceExperience = totalServiceExperience;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRegtCorpsOfficeDeptt() {
		return regtCorpsOfficeDeptt;
	}

	public void setRegtCorpsOfficeDeptt(String regtCorpsOfficeDeptt) {
		this.regtCorpsOfficeDeptt = regtCorpsOfficeDeptt;
	}

}
