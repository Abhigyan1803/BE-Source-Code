package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class HallOfFameOfficer implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8298088232575055638L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String officerRank;

	private String officerName;

	private String officerRegiment;

	private String officerBattalion;

	private String awardMedal;

	private String yearAwarded;

	private String recognition;

	@Lob
	private String description;

	private String officerImage;

	private Integer isForeign;

	private String country;

	private Integer status;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getOfficerRegiment() {
		return officerRegiment;
	}

	public void setOfficerRegiment(String officerRegiment) {
		this.officerRegiment = officerRegiment;
	}

	public String getOfficerBattalion() {
		return officerBattalion;
	}

	public void setOfficerBattalion(String officerBattalion) {
		this.officerBattalion = officerBattalion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAwardMedal() {
		return awardMedal;
	}

	public void setAwardMedal(String awardMedal) {
		this.awardMedal = awardMedal;
	}

	public String getYearAwarded() {
		return yearAwarded;
	}

	public void setYearAwarded(String yearAwarded) {
		this.yearAwarded = yearAwarded;
	}

	public String getRecognition() {
		return recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOfficerImage() {
		return officerImage;
	}

	public void setOfficerImage(String officerImage) {
		this.officerImage = officerImage;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getIsForeign() {
		return isForeign;
	}

	public void setIsForeign(Integer isForeign) {
		this.isForeign = isForeign;
	}

}
