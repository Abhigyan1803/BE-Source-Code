package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="session_week")
public class SessionWeek {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date weekStartDate;
	
	private Date weekEndDate;
	
	private String week="";
	
	@ManyToOne
	private SessionByYear sessionYear;
	
	private int status=0;
	
	private Date createdAt;
	
	private Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getWeekStartDate() {
		return weekStartDate;
	}

	public void setWeekStartDate(Date weekStartDate) {
		this.weekStartDate = weekStartDate;
	}

	public Date getWeekEndDate() {
		return weekEndDate;
	}

	public void setWeekEndDate(Date weekEndDate) {
		this.weekEndDate = weekEndDate;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public SessionByYear getSessionYear() {
		return sessionYear;
	}

	public void setSessionYear(SessionByYear sessionYear) {
		this.sessionYear = sessionYear;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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
