package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "organization_team_member")
public class OrganizationTeamMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private String team_rank="";
	
	private String team_name="";
	
	private String team_position="";
	
	private String team_award="";
	
	private int status=0;
	
	private String team_image="";
	
	@OneToOne
	OrganizationChart organizationChart;
	
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

	public String getTeam_rank() {
		return team_rank;
	}

	public void setTeam_rank(String team_rank) {
		this.team_rank = team_rank;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getTeam_position() {
		return team_position;
	}

	public void setTeam_position(String team_position) {
		this.team_position = team_position;
	}

	public String getTeam_award() {
		return team_award;
	}

	public void setTeam_award(String team_award) {
		this.team_award = team_award;
	}

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTeam_image() {
		return team_image;
	}

	public void setTeam_image(String team_image) {
		this.team_image = team_image;
	}

	public OrganizationChart getOrganizationChart() {
		return organizationChart;
	}

	public void setOrganizationChart(OrganizationChart organizationChart) {
		this.organizationChart = organizationChart;
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
