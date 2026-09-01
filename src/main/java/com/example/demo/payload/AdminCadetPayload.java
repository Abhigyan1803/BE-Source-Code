package com.example.demo.payload;

import java.util.List;

import com.example.demo.model.Cadet;

public class AdminCadetPayload {

	private Integer totalRecords;
	private List<Cadet> cadetList;

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<Cadet> getCadetList() {
		return cadetList;
	}

	public void setCadetList(List<Cadet> cadetList) {
		this.cadetList = cadetList;
	}

}
