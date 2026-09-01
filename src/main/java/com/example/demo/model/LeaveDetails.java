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

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "leave_details")
public class LeaveDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "officer_rank")
	private String officerRank;

	@Column(name = "officer_name")
	private String officerName; 
	
	@Column(name = "battlion_name")
	private String battalionName;
	
	@Column(name = "type_of_leave")
	private String typeOfLeave; 
	
	@Column(name = "leave_from")
	private String leaveFrom; 
	
	@Column(name = "leave_to")
	private String leaveTo; 
	
	@Column(name = "prefix")
	private String prefix; 
	
	@Column(name = "suffix")
	private String suffix; 
	
	@Column(name = "remarks")
	private String remarks; 
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "battlion_id")
	private Integer battalionId;
	
	@ApiModelProperty(hidden = true)
	@Column(name = "created_at")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@ApiModelProperty(hidden = true)
	@Column(name = "updated_at")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getBattalionName() {
		return battalionName;
	}

	public void setBattalionName(String battalionName) {
		this.battalionName = battalionName;
	}

	public String getTypeOfLeave() {
		return typeOfLeave;
	}

	public void setTypeOfLeave(String typeOfLeave) {
		this.typeOfLeave = typeOfLeave;
	}

	public String getLeaveFrom() {
		return leaveFrom;
	}

	public void setLeaveFrom(String leaveFrom) {
		this.leaveFrom = leaveFrom;
	}

	public String getLeaveTo() {
		return leaveTo;
	}

	public void setLeaveTo(String leaveTo) {
		this.leaveTo = leaveTo;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Integer getBattalionId() {
		return battalionId;
	}

	public void setBattalionId(Integer battalionId) {
		this.battalionId = battalionId;
	}
	
	

}