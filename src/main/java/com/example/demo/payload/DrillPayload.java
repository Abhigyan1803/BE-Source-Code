package com.example.demo.payload;

import java.util.List;

public class DrillPayload {
	private Integer totalRecords;
	private List<DrillFilterPayload> drillFilterPayload;
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<DrillFilterPayload> getDrillFilterPayload() {
		return drillFilterPayload;
	}
	public void setDrillFilterPayload(List<DrillFilterPayload> drillFilterPayload) {
		this.drillFilterPayload = drillFilterPayload;
	}
}
