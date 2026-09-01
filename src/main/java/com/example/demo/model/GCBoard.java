package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "gc_board")
public class GCBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title = "";

	private String description = "";

//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	private Date eventStartTime;
//
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	private Date eventEndTime;

	private Date createdAt;

	private Date updatedAt;

	private String document = "";

	private int status = 0;

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

//	public Date getEventStartTime() {
//		return eventStartTime;
//	}
//
//	public void setEventStartTime(Date eventStartTime) {
//		this.eventStartTime = eventStartTime;
//	}
//
//	public Date getEventEndTime() {
//		return eventEndTime;
//	}
//
//	public void setEventEndTime(Date eventEndTime) {
//		this.eventEndTime = eventEndTime;
//	}

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

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
