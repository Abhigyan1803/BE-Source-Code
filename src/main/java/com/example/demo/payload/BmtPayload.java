package com.example.demo.payload;

import java.util.List;

public class BmtPayload {
	private Integer totalRecords;
	private List<BmtFilterPayload> bmtFilterPayload;
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<BmtFilterPayload> getBmtFilterPayload() {
		return bmtFilterPayload;
	}
	public void setBmtFilterPayload(List<BmtFilterPayload> bmtFilterPayload) {
		this.bmtFilterPayload = bmtFilterPayload;
	}

}
