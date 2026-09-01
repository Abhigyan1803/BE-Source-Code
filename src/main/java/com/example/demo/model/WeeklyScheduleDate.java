package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
//@Table(name="weekly_schedule_date")
public class WeeklyScheduleDate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date date;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,targetEntity = Schedule.class)
	private List<Schedule> dailySchedule;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Schedule> getDailySchedule() {
		return dailySchedule;
	}

	public void setDailySchedule(List<Schedule> dailySchedule) {
		this.dailySchedule = dailySchedule;
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

}
