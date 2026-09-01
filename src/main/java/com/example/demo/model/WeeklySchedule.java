package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class WeeklySchedule implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Date weekStartDate;

	private Date weekEndDate;

	@OneToOne
	private Week week;

	private String year;

	@OneToOne
	private Term term;

	@OneToOne
	private TermSeason sessionTerm;

	@OneToOne
	private Battalion battalian;

	@OrderBy("date  ASC")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,targetEntity = WeeklyScheduleDate.class)
	private List<WeeklyScheduleDate> weeklyScheduleDate;

	private Integer status;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_at;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public TermSeason getSessionTerm() {
		return sessionTerm;
	}

	public void setSessionTerm(TermSeason sessionTerm) {
		this.sessionTerm = sessionTerm;
	}

	public Battalion getBattalian() {
		return battalian;
	}

	public void setBattalian(Battalion battalian) {
		this.battalian = battalian;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<WeeklyScheduleDate> getWeeklyScheduleDate() {
		return weeklyScheduleDate;
	}

	public void setWeeklyScheduleDate(List<WeeklyScheduleDate> weeklyScheduleDate) {
		this.weeklyScheduleDate = weeklyScheduleDate;
	}

}
