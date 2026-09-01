package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Table
@Entity(name = "family_details")
public class FamilyDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fatherName;
	private String monthlyIncome;
	private String name_of_next_of_kin_showing_rel;
	private String address_of_next_of_kin_showing_rel;
	private String relation;
	private String father_profession;
	private String currentStatus;
	private String furnishDetail;
	private String rankType;
	private String armedForce;
	private String unit;
	private String choiceofArms;
	//@Column(name = "rank_name")
	private String rankName;

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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getName_of_next_of_kin_showing_rel() {
		return name_of_next_of_kin_showing_rel;
	}

	public void setName_of_next_of_kin_showing_rel(String name_of_next_of_kin_showing_rel) {
		this.name_of_next_of_kin_showing_rel = name_of_next_of_kin_showing_rel;
	}

	public String getAddress_of_next_of_kin_showing_rel() {
		return address_of_next_of_kin_showing_rel;
	}

	public void setAddress_of_next_of_kin_showing_rel(String address_of_next_of_kin_showing_rel) {
		this.address_of_next_of_kin_showing_rel = address_of_next_of_kin_showing_rel;
	}

	public String getFather_profession() {
		return father_profession;
	}

	public void setFather_profession(String father_profession) {
		this.father_profession = father_profession;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getFurnishDetail() {
		return furnishDetail;
	}

	public void setFurnishDetail(String furnishDetail) {
		this.furnishDetail = furnishDetail;
	}

	public String getRankType() {
		return rankType;
	}

	public void setRankType(String rankType) {
		this.rankType = rankType;
	}

	public String getArmedForce() {
		return armedForce;
	}

	public void setArmedForce(String armedForce) {
		this.armedForce = armedForce;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getChoiceofArms() {
		return choiceofArms;
	}

	public void setChoiceofArms(String choiceofArms) {
		this.choiceofArms = choiceofArms;
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

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	

}
