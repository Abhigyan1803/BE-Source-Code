package com.example.demo.payload;

import java.util.Date;

public class MsgCommandantReqPayload {
	
	 private Long id;
	 
	    private String message="";
		
		private String name="";
		
		private String award="";
		
		private String designation="";
		
		private String organization="";
		
		private int status=0;  //1-Active , 0-Deactivate
		
		private String createdAt="";
		
		private Long date=0l;
		
		public String getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAward() {
			return award;
		}

		public void setAward(String award) {
			this.award = award;
		}

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public String getOrganization() {
			return organization;
		}

		public void setOrganization(String organization) {
			this.organization = organization;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public Long getDate() {
			return date;
		}

		public void setDate(Long date) {
			this.date = date;
		}

		
	    
	   
	
}
