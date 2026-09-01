package com.example.demo.payload;

import java.util.List;

public class EdCadetPayload {

	private Integer totalRecords;
	private List<CadetFilterPayload> cadetFilterPayload;

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<CadetFilterPayload> getCadetFilterPayload() {
		return cadetFilterPayload;
	}

	public void setCadetFilterPayload(List<CadetFilterPayload> cadetFilterPayload) {
		this.cadetFilterPayload = cadetFilterPayload;
	}
}
