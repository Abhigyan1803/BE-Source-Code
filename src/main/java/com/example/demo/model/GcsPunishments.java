package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gc_punsihments")
public class GcsPunishments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String offence;
	private Date date;
	private String punshmentAwarded;
	private Integer points;
	private String awardedBy;
	private String serviceId;
	private Long termId;
	private Date createdAt;
	private Date updatedAt;
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOffence() {
		return offence;
	}

	public void setOffence(String offence) {
		this.offence = offence;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPunshmentAwarded() {
		return punshmentAwarded;
	}

	public void setPunshmentAwarded(String punshmentAwarded) {
		this.punshmentAwarded = punshmentAwarded;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getAwardedBy() {
		return awardedBy;
	}

	public void setAwardedBy(String awardedBy) {
		this.awardedBy = awardedBy;
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

}
