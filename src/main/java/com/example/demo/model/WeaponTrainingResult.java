package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class WeaponTrainingResult implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String serviceId;

	private Double grandTotal;

	private Integer wtt;

	private Integer spotTest;

	private Integer maxGrandTotal;

	private Integer maxWtt;

	private Integer maxSpotTest;

	// private String gPoint;

	private String remark;

	private Long termId;

	private Long userId;

	@OneToMany
	List<CadetWeaponTrainingMainResult1> cadetWTMainResultlist;

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

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Integer getWtt() {
		return wtt;
	}

	public void setWtt(Integer wtt) {
		this.wtt = wtt;
	}

	public Integer getSpotTest() {
		return spotTest;
	}

	public void setSpotTest(Integer spotTest) {
		this.spotTest = spotTest;
	}

	// public String getgPoint() {
	// return gPoint;
	// }
	//
	// public void setgPoint(String gPoint) {
	// this.gPoint = gPoint;
	// }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<CadetWeaponTrainingMainResult1> getCadetWTMainResultlist() {
		return cadetWTMainResultlist;
	}

	public void setCadetWTMainResultlist(List<CadetWeaponTrainingMainResult1> cadetWTMainResultlist) {
		this.cadetWTMainResultlist = cadetWTMainResultlist;
	}

	public Integer getMaxWtt() {
		return maxWtt;
	}

	public void setMaxWtt(Integer maxWtt) {
		this.maxWtt = maxWtt;
	}

	public Integer getMaxSpotTest() {
		return maxSpotTest;
	}

	public void setMaxSpotTest(Integer maxSpotTest) {
		this.maxSpotTest = maxSpotTest;
	}

	public Integer getMaxGrandTotal() {
		return maxGrandTotal;
	}

	public void setMaxGrandTotal(Integer maxGrandTotal) {
		this.maxGrandTotal = maxGrandTotal;
	}

	@Override
	public String toString() {
		return "WeaponTrainingResult [id=" + id + ", serviceId=" + serviceId + ", grandTotal=" + grandTotal + ", wtt="
				+ wtt + ", spotTest=" + spotTest + ", maxGrandTotal=" + maxGrandTotal + ", maxWtt=" + maxWtt
				+ ", maxSpotTest=" + maxSpotTest + ", remark=" + remark + ", termId=" + termId
				+ ", cadetWTMainResultlist=" + cadetWTMainResultlist + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
