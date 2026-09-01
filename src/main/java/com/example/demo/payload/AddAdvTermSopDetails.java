package com.example.demo.payload;

import java.util.Date;


public class AddAdvTermSopDetails {

	
	private Date date;
	private String name="";
	private String description="";
	private int status=0;
	private String document="";
	private Long termSeasonId;
	private String year="";
	private Long adventureCellType;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
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

	public Long getAdventureCellType() {
		return adventureCellType;
	}

	public void setAdventureCellType(Long adventureCellType) {
		this.adventureCellType = adventureCellType;
	}
	
	
	
	
	
}
