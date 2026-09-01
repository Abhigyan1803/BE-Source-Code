package com.example.demo.payload;

import java.util.List;

public class OqEqtnPayload {
	
	private Integer totalRecords;
	private List<OqEqtnFilterPayload> oqEqtnFilterPayload;
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<OqEqtnFilterPayload> getOqEqtnFilterPayload() {
		return oqEqtnFilterPayload;
	}
	public void setOqEqtnFilterPayload(List<OqEqtnFilterPayload> oqEqtnFilterPayload) {
		this.oqEqtnFilterPayload = oqEqtnFilterPayload;
	}
	
	

}
