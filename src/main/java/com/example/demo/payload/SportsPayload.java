package com.example.demo.payload;

import java.util.List;

public class SportsPayload {

	private Integer totalRecords;
	private List<SportsFilterPayload> sportsFilterPayload;

	public List<SportsFilterPayload> getSportsFilterPayload() {
		return sportsFilterPayload;
	}

	public void setSportsFilterPayload(List<SportsFilterPayload> sportsFilterPayload) {
		this.sportsFilterPayload = sportsFilterPayload;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

}
