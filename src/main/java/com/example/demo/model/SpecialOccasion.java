package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class SpecialOccasion implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String officerRank;

	private String postedBranch;

	private String officerName;

	private String spouseName;

	private String relation;

	private Date officerDOB;

	private Date spouseDOB;

	private Date marriageAnniversary;

	private Integer status;

	private Integer icNumber;
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

	public String getOfficerRank() {
		return officerRank;
	}

	public void setOfficerRank(String officerRank) {
		this.officerRank = officerRank;
	}

	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Date getOfficerDOB() {
		return officerDOB;
	}

	public void setOfficerDOB(Date officerDOB) {
		this.officerDOB = officerDOB;
	}

	public Date getSpouseDOB() {
		return spouseDOB;
	}

	public void setSpouseDOB(Date spouseDOB) {
		this.spouseDOB = spouseDOB;
	}

	public Date getMarriageAnniversary() {
		return marriageAnniversary;
	}

	public void setMarriageAnniversary(Date marriageAnniversary) {
		this.marriageAnniversary = marriageAnniversary;
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

	public String getPostedBranch() {
		return postedBranch;
	}

	public void setPostedBranch(String postedBranch) {
		this.postedBranch = postedBranch;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIcNumber() {
		return icNumber;
	}

	public void setIcNumber(Integer icNumber) {
		this.icNumber = icNumber;
	}

}
