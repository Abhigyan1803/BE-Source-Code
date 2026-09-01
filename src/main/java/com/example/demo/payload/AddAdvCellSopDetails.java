package com.example.demo.payload;


public class AddAdvCellSopDetails {
	
	private String name="";
	private String description="";
	private int status=0;
	private long AcTypeId=0l;
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getAcTypeId() {
		return AcTypeId;
	}
	public void setAcTypeId(long acTypeId) {
		AcTypeId = acTypeId;
	}
	
	

}
