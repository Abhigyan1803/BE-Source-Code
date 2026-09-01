package com.example.demo.payload;

import java.util.List;

public class CreditExcellencePayload {
	private Integer totalRecords;
	private List<CreditExcellenceFilterPayload> creditExcellenceFilterPayload;

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<CreditExcellenceFilterPayload> getCreditExcellenceFilterPayload() {
		return creditExcellenceFilterPayload;
	}

	public void setCreditExcellenceFilterPayload(List<CreditExcellenceFilterPayload> creditExcellenceFilterPayload) {
		this.creditExcellenceFilterPayload = creditExcellenceFilterPayload;
	}

}
