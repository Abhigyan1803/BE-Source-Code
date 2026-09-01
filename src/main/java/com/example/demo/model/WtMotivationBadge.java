package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class WtMotivationBadge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long termId;
	
	private String serviceId;
	
	private String badge1;
	
	private String badge2;
	
	private String badge3;
	
	private String badge4;
	
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

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getBadge1() {
		return badge1;
	}

	public void setBadge1(String badge1) {
		this.badge1 = badge1;
	}

	public String getBadge2() {
		return badge2;
	}

	public void setBadge2(String badge2) {
		this.badge2 = badge2;
	}

	public String getBadge3() {
		return badge3;
	}

	public void setBadge3(String badge3) {
		this.badge3 = badge3;
	}

	public String getBadge4() {
		return badge4;
	}

	public void setBadge4(String badge4) {
		this.badge4 = badge4;
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
	
}
