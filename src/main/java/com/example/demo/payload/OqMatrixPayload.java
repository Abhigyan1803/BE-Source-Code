package com.example.demo.payload;

import java.util.List;

public class OqMatrixPayload {
	private Integer totalRecords;
	private List<OqMatrixFilterPayload> oqMatrixFilterPayload;

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<OqMatrixFilterPayload> getOqMatrixFilterPayload() {
		return oqMatrixFilterPayload;
	}

	public void setOqMatrixFilterPayload(List<OqMatrixFilterPayload> oqMatrixFilterPayload) {
		this.oqMatrixFilterPayload = oqMatrixFilterPayload;
	}
}
