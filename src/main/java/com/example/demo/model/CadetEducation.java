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
public class CadetEducation implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String examination;

	private String year;

	private String schoolCollege;

	private String university;

	private String divisionClass;

	private String subjectTaken;

	private String percentage;

	private String certImg;

//	@ManyToOne
//	private Cadet cadet;

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

	public String getExamination() {
		return examination;
	}

	public void setExamination(String examination) {
		this.examination = examination;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSchoolCollege() {
		return schoolCollege;
	}

	public void setSchoolCollege(String schoolCollege) {
		this.schoolCollege = schoolCollege;
	}

	public String getDivisionClass() {
		return divisionClass;
	}

	public void setDivisionClass(String divisionClass) {
		this.divisionClass = divisionClass;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getSubjectTaken() {
		return subjectTaken;
	}

	public void setSubjectTaken(String subjectTaken) {
		this.subjectTaken = subjectTaken;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

//	public Cadet getCadet() {
//		return cadet;
//	}
//
//	public void setCadet(Cadet cadet) {
//		this.cadet = cadet;
//	}

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

	public String getCertImg() {
		return certImg;
	}

	public void setCertImg(String certImg) {
		this.certImg = certImg;
	}

}
