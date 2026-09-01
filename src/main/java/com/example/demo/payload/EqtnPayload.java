package com.example.demo.payload;

import java.util.List;

public class EqtnPayload {
	private Integer totalRecords;
	private List<EqtnFilterPayload> EqtnFilterPayload;
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<EqtnFilterPayload> getEqtnFilterPayload() {
		return EqtnFilterPayload;
	}
	public void setEqtnFilterPayload(List<EqtnFilterPayload> eqtnFilterPayload) {
		EqtnFilterPayload = eqtnFilterPayload;
	}
	
}
