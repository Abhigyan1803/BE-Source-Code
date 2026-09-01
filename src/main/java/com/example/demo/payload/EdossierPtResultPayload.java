package com.example.demo.payload;

import java.util.List;

public class EdossierPtResultPayload {
	private Integer totalRecords;
	private List<EdossierPtResultFilterPayload> edossierPtResultFilterPayload;
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<EdossierPtResultFilterPayload> getEdossierPtResultFilterPayload() {
		return edossierPtResultFilterPayload;
	}
	public void setEdossierPtResultFilterPayload(List<EdossierPtResultFilterPayload> edossierPtResultFilterPayload) {
		this.edossierPtResultFilterPayload = edossierPtResultFilterPayload;
	}

}
