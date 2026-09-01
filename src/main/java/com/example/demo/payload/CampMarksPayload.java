package com.example.demo.payload;

import java.util.List;

public class CampMarksPayload {
	private Integer totalRecords;
	private List<CampMarksFilterPayload> campMarksFilterPayload;
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<CampMarksFilterPayload> getCampMarksFilterPayload() {
		return campMarksFilterPayload;
	}
	public void setCampMarksFilterPayload(List<CampMarksFilterPayload> campMarksFilterPayload) {
		this.campMarksFilterPayload = campMarksFilterPayload;
	}


}
