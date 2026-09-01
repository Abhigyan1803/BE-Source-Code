package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class CadetWeaponTrainingResult1 implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String serviceId;

	// @ManyToOne
	// private WeaponAttributes attributes;

	@Transient
	private String attributeName;

	private Long attributeId;

	private Long weaponId;

	private Integer marks;

	private Integer maxMarks;

	private Long termId;

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

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	// public WeaponAttributes getAttributes() {
	// return attributes;
	// }
	//
	// public void setAttributes(WeaponAttributes attributes) {
	// this.attributes = attributes;
	// }

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

	public Long getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}

	public Long getWeaponId() {
		return weaponId;
	}

	public void setWeaponId(Long weaponId) {
		this.weaponId = weaponId;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public Integer getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(Integer maxMarks) {
		this.maxMarks = maxMarks;
	}

	@Override
	public String toString() {
		return "CadetWeaponTrainingResult1 [id=" + id + ", serviceId=" + serviceId + ", attributeName=" + attributeName
				+ ", attributeId=" + attributeId + ", weaponId=" + weaponId + ", marks=" + marks + ", maxMarks="
				+ maxMarks + ", termId=" + termId + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

}
