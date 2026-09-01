package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "home_about_entries_details")
public class HomeAboutEntriesDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title; 
	
	private Integer status;
	
	@OneToMany(targetEntity = HomeAboutEntriesSubDetails.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "home_about_entries_details_fk", referencedColumnName = "id")
	private List<HomeAboutEntriesSubDetails> homeAboutEntriesSubDetails;
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<HomeAboutEntriesSubDetails> getHomeAboutEntriesSubDetails() {
		return homeAboutEntriesSubDetails;
	}

	public void setHomeAboutEntriesSubDetails(List<HomeAboutEntriesSubDetails> homeAboutEntriesSubDetails) {
		this.homeAboutEntriesSubDetails = homeAboutEntriesSubDetails;
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
