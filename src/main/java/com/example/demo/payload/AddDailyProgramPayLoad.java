package com.example.demo.payload;

import java.util.Date;

public class AddDailyProgramPayLoad {

	
	private String title="";
	private String venue="";
	private Long scheduledDate=0l;
	private Date startTime;
	private Date endTime;

	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public Long getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(Long scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	
}
