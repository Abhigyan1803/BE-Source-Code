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
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class CadetWeaponTrainingMainResult1 implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String serviceId;

	private Long termId;

	// private String markParam;

	private Integer marks;

	private Integer maxMarks;

	private Integer maxGPoint;

	private Integer status;

	@OneToMany
	private List<CadetWeaponTrainingResult1> cadetWTResultlist;

	private String std;

	private Double gPoint;

	private String remark;

	private Long weaponId;

	@Transient
	private String weaponName;

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

	public Integer getMarks() {
		return marks;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
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

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// public String getMarkParam() {
	// return markParam;
	// }
	//
	// public void setMarkParam(String markParam) {
	// this.markParam = markParam;
	// }

	public Integer getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(Integer maxMarks) {
		this.maxMarks = maxMarks;
	}

	public Long getWeaponId() {
		return weaponId;
	}

	public void setWeaponId(Long weaponId) {
		this.weaponId = weaponId;
	}

	public String getStd() {
		return std;
	}

	public void setStd(String std) {
		this.std = std;
	}

	public Double getgPoint() {
		return gPoint;
	}

	public void setgPoint(Double gPoint) {
		this.gPoint = gPoint;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany
	public List<CadetWeaponTrainingResult1> getCadetWTResultlist() {
		return cadetWTResultlist;
	}

	public void setCadetWTResultlist(List<CadetWeaponTrainingResult1> cadetWTResultlist) {
		this.cadetWTResultlist = cadetWTResultlist;
	}

	public String getWeaponName() {
		return weaponName;
	}

	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}

	public Integer getMaxGPoint() {
		return maxGPoint;
	}

	public void setMaxGPoint(Integer maxGPoint) {
		this.maxGPoint = maxGPoint;
	}

	@Override
	public String toString() {
		return "CadetWeaponTrainingMainResult1 [id=" + id + ", serviceId=" + serviceId + ", termId=" + termId
				+ ", marks=" + marks + ", maxMarks=" + maxMarks + ", maxGPoint=" + maxGPoint + ", status=" + status
				+ ", cadetWTResultlist=" + cadetWTResultlist + ", std=" + std + ", gPoint=" + gPoint + ", remark="
				+ remark + ", weaponId=" + weaponId + ", weaponName=" + weaponName + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
