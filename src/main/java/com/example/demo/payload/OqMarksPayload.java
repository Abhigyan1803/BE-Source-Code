package com.example.demo.payload;

import java.util.List;

public class OqMarksPayload {
	private Integer totalRecords;
	private List<OqMarksFilterPayload> oqMarksFilterPayload;
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<OqMarksFilterPayload> getOqMarksFilterPayload() {
		return oqMarksFilterPayload;
	}
	public void setOqMarksFilterPayload(List<OqMarksFilterPayload> oqMarksFilterPayload) {
		this.oqMarksFilterPayload = oqMarksFilterPayload;
	}

}
