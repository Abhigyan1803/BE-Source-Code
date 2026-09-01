package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name="battalion_organization")
public class BattalionOrganizationChart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name="";
	
	@Column(name="member_position")
	private String position="";
	
	@Column(name="member_subPosition")
	private String subPosition="";
	
	@Column(name="member_rank")
	private String rank="";
	
	private String image="";
	
	private String award="";
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;
	
	private int status=0;
	
	@OneToOne
	private Battalion battalionType;
	
	private Long companyId=0l;
	
    @Transient
	private BattalionCompany battalionCompany=null;
	
	@OneToOne
	private TrgBattalionPost battalionPost;

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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSubPosition() {
		return subPosition;
	}

	public void setSubPosition(String subPosition) {
		this.subPosition = subPosition;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Battalion getBattalionType() {
		return battalionType;
	}

	public void setBattalionType(Battalion battalionType) {
		this.battalionType = battalionType;
	}

	public BattalionCompany getBattalionCompany() {
		return battalionCompany;
	}

	public void setBattalionCompany(BattalionCompany battalionCompany) {
		this.battalionCompany = battalionCompany;
	}

	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award;
	}

	public TrgBattalionPost getBattalionPost() {
		return battalionPost;
	}

	public void setBattalionPost(TrgBattalionPost battalionPost) {
		this.battalionPost = battalionPost;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	
	
	
	

}
