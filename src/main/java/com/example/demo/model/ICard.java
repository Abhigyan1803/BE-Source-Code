package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "i_card")
public class ICard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String fatherOrHusband_Name;
	private String pers_No;
	private String rankName;
	private String old_ICard_No;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_of_issue;
	private String place_of_Issue;
	private String name_of_issuing_Auth;
	private String permanent_Home_Address_India;
	private String place_of_Birth;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_of_Birth;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_of_CommissionOrEnrolment;
	private String reason_for_Change_ICard;
	private String height;
	private String color_of_Hair;
	private String color_of_Eyes;
	private String Station;
	private Integer status;
	private String request_type;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date todayDate;
	private String remark;
	private String fatherOrHusband_Designation;
	private String permanent_Home_Address_Elsewhere;
	private String unit_crops_ship;
	private String signature_name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date Date_of_Retirement;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPers_No() {
		return pers_No;
	}

	public void setPers_No(String pers_No) {
		this.pers_No = pers_No;
	}

	public String getOld_ICard_No() {
		return old_ICard_No;
	}

	public void setOld_ICard_No(String old_ICard_No) {
		this.old_ICard_No = old_ICard_No;
	}

	public Date getDate_of_issue() {
		return date_of_issue;
	}

	public void setDate_of_issue(Date date_of_issue) {
		this.date_of_issue = date_of_issue;
	}

	public String getPlace_of_Issue() {
		return place_of_Issue;
	}

	public void setPlace_of_Issue(String place_of_Issue) {
		this.place_of_Issue = place_of_Issue;
	}

	public String getName_of_issuing_Auth() {
		return name_of_issuing_Auth;
	}

	public void setName_of_issuing_Auth(String name_of_issuing_Auth) {
		this.name_of_issuing_Auth = name_of_issuing_Auth;
	}

	public String getPlace_of_Birth() {
		return place_of_Birth;
	}

	public void setPlace_of_Birth(String place_of_Birth) {
		this.place_of_Birth = place_of_Birth;
	}

	public Date getDate_of_Birth() {
		return date_of_Birth;
	}

	public void setDate_of_Birth(Date date_of_Birth) {
		this.date_of_Birth = date_of_Birth;
	}

	public Date getDate_of_CommissionOrEnrolment() {
		return date_of_CommissionOrEnrolment;
	}

	public void setDate_of_CommissionOrEnrolment(Date date_of_CommissionOrEnrolment) {
		this.date_of_CommissionOrEnrolment = date_of_CommissionOrEnrolment;
	}

	public String getReason_for_Change_ICard() {
		return reason_for_Change_ICard;
	}

	public void setReason_for_Change_ICard(String reason_for_Change_ICard) {
		this.reason_for_Change_ICard = reason_for_Change_ICard;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getColor_of_Hair() {
		return color_of_Hair;
	}

	public void setColor_of_Hair(String color_of_Hair) {
		this.color_of_Hair = color_of_Hair;
	}

	public String getColor_of_Eyes() {
		return color_of_Eyes;
	}

	public void setColor_of_Eyes(String color_of_Eyes) {
		this.color_of_Eyes = color_of_Eyes;
	}

	public String getStation() {
		return Station;
	}

	public void setStation(String station) {
		Station = station;
	}

	public Date getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(Date todayDate) {
		this.todayDate = todayDate;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRequest_type() {
		return request_type;
	}

	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFatherOrHusband_Name() {
		return fatherOrHusband_Name;
	}

	public void setFatherOrHusband_Name(String fatherOrHusband_Name) {
		this.fatherOrHusband_Name = fatherOrHusband_Name;
	}

	public String getPermanent_Home_Address_India() {
		return permanent_Home_Address_India;
	}

	public void setPermanent_Home_Address_India(String permanent_Home_Address_India) {
		this.permanent_Home_Address_India = permanent_Home_Address_India;
	}

	public String getFatherOrHusband_Designation() {
		return fatherOrHusband_Designation;
	}

	public void setFatherOrHusband_Designation(String fatherOrHusband_Designation) {
		this.fatherOrHusband_Designation = fatherOrHusband_Designation;
	}

	public String getPermanent_Home_Address_Elsewhere() {
		return permanent_Home_Address_Elsewhere;
	}

	public void setPermanent_Home_Address_Elsewhere(String permanent_Home_Address_Elsewhere) {
		this.permanent_Home_Address_Elsewhere = permanent_Home_Address_Elsewhere;
	}

	public String getUnit_crops_ship() {
		return unit_crops_ship;
	}

	public void setUnit_crops_ship(String unit_crops_ship) {
		this.unit_crops_ship = unit_crops_ship;
	}

	public String getSignature_name() {
		return signature_name;
	}

	public void setSignature_name(String signature_name) {
		this.signature_name = signature_name;
	}

	public Date getDate_of_Retirement() {
		return Date_of_Retirement;
	}

	public void setDate_of_Retirement(Date date_of_Retirement) {
		Date_of_Retirement = date_of_Retirement;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
}
