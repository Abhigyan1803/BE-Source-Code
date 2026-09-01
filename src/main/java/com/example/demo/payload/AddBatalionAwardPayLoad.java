package com.example.demo.payload;


public class AddBatalionAwardPayLoad {
	
	private String name="";
	private String image="";
	private String rank="";
	private String award="";
	private Long companyId;
	private Integer battalionId;
	private int status=0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award;
	}

	public Integer getBattalionId() {
		return battalionId;
	}

	public void setBattalionId(Integer battalionId) {
		this.battalionId = battalionId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	

	
	
	

}
