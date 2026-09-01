package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
public class OfficersFormerServices implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String preCommissionService;

	private String authorityPermitting;

	private Date dateFrom;

	private Date dateTo;

	private String totalServiceExperience;

	@Lob
	private String remark;

	@Column(name = "JCOPersonalNo")
	private String jCOPersonalNo;

	@Column(name = "JCORank")
	private String jCORank;

	private String offrServicePersonalNo;

	private String offrRank;

	private String otherReckonableService;

	@ManyToOne
	private User user;

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

	public String getAuthorityPermitting() {
		return authorityPermitting;
	}

	public void setAuthorityPermitting(String authorityPermitting) {
		this.authorityPermitting = authorityPermitting;
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

	public String getOffrServicePersonalNo() {
		return offrServicePersonalNo;
	}

	public void setOffrServicePersonalNo(String offrServicePersonalNo) {
		this.offrServicePersonalNo = offrServicePersonalNo;
	}

	public String getOffrRank() {
		return offrRank;
	}

	public void setOffrRank(String offrRank) {
		this.offrRank = offrRank;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getjCOPersonalNo() {
		return jCOPersonalNo;
	}

	public void setjCOPersonalNo(String jCOPersonalNo) {
		this.jCOPersonalNo = jCOPersonalNo;
	}

	public String getjCORank() {
		return jCORank;
	}

	public void setjCORank(String jCORank) {
		this.jCORank = jCORank;
	}

	public String getOtherReckonableService() {
		return otherReckonableService;
	}

	public void setOtherReckonableService(String otherReckonableService) {
		this.otherReckonableService = otherReckonableService;
	}

}
