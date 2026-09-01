package com.example.demo.payload;

import com.example.demo.model.Battalion;
import com.example.demo.model.BattalionCompany;

public class CadetLoginPayload {

	private Long id;

	private String serviceId;

	private String username;

	private String serialNo;

	private Battalion battalian;

	private BattalionCompany company;

	private String termSession;

	private Long term;

	private String termName;

	private String name;

	private String profileImg;

	private String year;

	private String hasRole;  //Akash 08/08/2023 V1
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Battalion getBattalian() {
		return battalian;
	}

	public void setBattalian(Battalion battalian) {
		this.battalian = battalian;
	}

	public BattalionCompany getCompany() {
		return company;
	}

	public void setCompany(BattalionCompany company) {
		this.company = company;
	}

	public String getTermSession() {
		return termSession;
	}

	public void setTermSession(String termSession) {
		this.termSession = termSession;
	}

	public Long getTerm() {
		return term;
	}

	public void setTerm(Long term) {
		this.term = term;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getHasRole() {
		return hasRole;
	}

	public void setHasRole(String hasRole) {
		this.hasRole = hasRole;
	}

}
