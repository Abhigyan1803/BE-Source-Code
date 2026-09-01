package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "home_about_entries_sub_details")
public class HomeAboutEntriesSubDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String subTitle;
	@Lob
	private String description;
	
	private Integer status;
	
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

	
	@Column(name = "home_about_entries_details_fk")
	private Long homeAboutEntriesDetailsFk;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getSubTitle() {
		return subTitle;
	}


	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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


	public Long getHomeAboutEntriesDetailsFk() {
		return homeAboutEntriesDetailsFk;
	}


	public void setHomeAboutEntriesDetailsFk(Long homeAboutEntriesDetailsFk) {
		this.homeAboutEntriesDetailsFk = homeAboutEntriesDetailsFk;
	}
}
