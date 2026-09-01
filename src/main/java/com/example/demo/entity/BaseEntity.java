package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -7664704413532546100L;
	private Long id;
	private Date createdDate;
	private Date modifiedDate;
	private boolean isDeleted = false;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", precision = 19, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", precision = 38, scale = 0, updatable = false, nullable = true)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date date) {
		this.createdDate = date;
	}

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date", precision = 38, scale = 0, nullable = true)
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "is_deleted", nullable = false)
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
