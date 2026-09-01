package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class RosIndianLanguage implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String language;

	private String examinationPassed;

	private Boolean canRead;

	private Boolean canSpeak;

	private Boolean canWrite;

	private Date examinationDate;

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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getExaminationDate() {
		return examinationDate;
	}

	public void setExaminationDate(Date examinationDate) {
		this.examinationDate = examinationDate;
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

	public Boolean getCanRead() {
		return canRead;
	}

	public void setCanRead(Boolean canRead) {
		this.canRead = canRead;
	}

	public Boolean getCanSpeak() {
		return canSpeak;
	}

	public void setCanSpeak(Boolean canSpeak) {
		this.canSpeak = canSpeak;
	}

	public Boolean getCanWrite() {
		return canWrite;
	}

	public void setCanWrite(Boolean canWrite) {
		this.canWrite = canWrite;
	}

	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	public String getExaminationPassed() {
		return examinationPassed;
	}

	public void setExaminationPassed(String examinationPassed) {
		this.examinationPassed = examinationPassed;
	}

}
