package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class CadetOtherExams implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String examName;

	@Column(name = "g_point_I_term")
	private String gPointITerm;

	@Column(name = "g_point_II_term")
	private String gPointIITerm;

	@Column(name = "g_point_III_term")
	private String gPointIIITerm;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getgPointITerm() {
		return gPointITerm;
	}

	public void setgPointITerm(String gPointITerm) {
		this.gPointITerm = gPointITerm;
	}

	public String getgPointIITerm() {
		return gPointIITerm;
	}

	public void setgPointIITerm(String gPointIITerm) {
		this.gPointIITerm = gPointIITerm;
	}

	public String getgPointIIITerm() {
		return gPointIIITerm;
	}

	public void setgPointIIITerm(String gPointIIITerm) {
		this.gPointIIITerm = gPointIIITerm;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

}
