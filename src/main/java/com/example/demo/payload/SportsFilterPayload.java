package com.example.demo.payload;

import com.example.demo.model.SportsResult;

public class SportsFilterPayload {

	private Long id;

	private String serviceId;

	private String name;

	private String battalian;

	private String company;

	private String rank;

	private Long termId;

	private String termName;

	private String course;
	private String courseSerNo;
	private String nationality;
	private SportsResult sportsResult;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBattalian() {
		return battalian;
	}

	public void setBattalian(String battalian) {
		this.battalian = battalian;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public SportsResult getSportsResult() {
		return sportsResult;
	}

	public void setSportsResult(SportsResult sportsResult) {
		this.sportsResult = sportsResult;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getCourseSerNo() {
		return courseSerNo;
	}

	public void setCourseSerNo(String courseSerNo) {
		this.courseSerNo = courseSerNo;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

}
