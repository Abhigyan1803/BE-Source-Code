package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Table
@Entity(name = "foreign_languages")
public class ForeignLanguages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String language;

	private String qualification;

	private String University;

	private Boolean ProRead;

	private Boolean ProWrite;

	private Boolean ProSpeak;

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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getUniversity() {
		return University;
	}

	public void setUniversity(String university) {
		University = university;
	}

	public Boolean getProRead() {
		return ProRead;
	}

	public void setProRead(Boolean proRead) {
		ProRead = proRead;
	}

	public Boolean getProWrite() {
		return ProWrite;
	}

	public void setProWrite(Boolean proWrite) {
		ProWrite = proWrite;
	}

	public Boolean getProSpeak() {
		return ProSpeak;
	}

	public void setProSpeak(Boolean proSpeak) {
		ProSpeak = proSpeak;
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
