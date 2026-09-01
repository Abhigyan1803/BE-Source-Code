package com.example.demo.payload;

import java.util.List;

public class ServiceBmt2Payload {
	private Integer totalRecords;
	private List<ServiceBmt2FilterPayload> serviceBmt2FilterPayload;
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<ServiceBmt2FilterPayload> getServiceBmt2FilterPayload() {
		return serviceBmt2FilterPayload;
	}
	public void setServiceBmt2FilterPayload(List<ServiceBmt2FilterPayload> serviceBmt2FilterPayload) {
		this.serviceBmt2FilterPayload = serviceBmt2FilterPayload;
	}
	
}
