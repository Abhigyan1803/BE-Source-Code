package com.example.demo.payload;

import java.util.Date;

public class WeeklyFilter {

	private Long weekId;

	private Long termId;

	private Long termSeasonId;

	private String year;

	private Integer battalianId;

	private Date dt;

	public Long getWeekId() {
		return weekId;
	}

	public void setWeekId(Long weekId) {
		this.weekId = weekId;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public Long getTermSeasonId() {
		return termSeasonId;
	}

	public void setTermSeasonId(Long termSeasonId) {
		this.termSeasonId = termSeasonId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getBattalianId() {
		return battalianId;
	}

	public void setBattalianId(Integer battalianId) {
		this.battalianId = battalianId;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

}
