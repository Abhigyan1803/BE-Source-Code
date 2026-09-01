package com.example.demo.payload;

import java.util.Date;

public class WeeklyFilter2 {
	
	private Long termId;
	
	private Integer battalianId;
	
	private Date dt;

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
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
