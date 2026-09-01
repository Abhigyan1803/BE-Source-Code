package com.example.demo.payload;

import javax.persistence.Lob;

public class GeneralInstructionReqPayload {
	
    private String docName="";
	
    @Lob
	private String description="";
	
	private int acType=0; //Adventure cell type
	
	private int term=0;
	
	private String year="";
	
	private int status=0; // 0-inactive , 1-active	

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAcType() {
		return acType;
	}

	public void setAcType(int acType) {
		this.acType = acType;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
