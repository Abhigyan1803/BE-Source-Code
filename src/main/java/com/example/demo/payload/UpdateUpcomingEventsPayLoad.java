package com.example.demo.payload;

public class UpdateUpcomingEventsPayLoad {

	private Long id = 0l;
	private String title = "";

	private String description = "";

	private Long eventDate = 0l;

	private int status = 0;

	private Boolean isGcEvent;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getEventDate() {
		return eventDate;
	}

	public void setEventDate(Long eventDate) {
		this.eventDate = eventDate;
	}

	public Boolean getIsGcEvent() {
		return isGcEvent;
	}

	public void setIsGcEvent(Boolean isGcEvent) {
		this.isGcEvent = isGcEvent;
	}

}
