package com.example.demo.payload;

import java.util.List;

public class OqDrillPayload {

	private Integer totalRecords;
	private List<OqDrillFilterPayload> oqDrillFilterPayload;
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<OqDrillFilterPayload> getOqDrillFilterPayload() {
		return oqDrillFilterPayload;
	}
	public void setOqDrillFilterPayload(List<OqDrillFilterPayload> oqDrillFilterPayload) {
		this.oqDrillFilterPayload = oqDrillFilterPayload;
	}
	
}
