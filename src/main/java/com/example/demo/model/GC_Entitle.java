package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GC_Entitle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long cadetId;
	private String cadetName;
	private String type = "";
	private String title = "";
	private Integer status = 1;

	private String Details = "";

	private String Address = "";

	/*
	 * public Entitle(Long id, String type,String title, Integer status, String
	 * details, String address) { super(); this.id = id; this.type = type;
	 * this.title = title; this.status = status; Details = details; Address =
	 * address; }
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDetails() {
		return Details;
	}

	public void setDetails(String details) {
		Details = details;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getCadetId() {
		return cadetId;
	}

	public void setCadetId(Long cadetId) {
		this.cadetId = cadetId;
	}

	public String getCadetName() {
		return cadetName;
	}

	public void setCadetName(String cadetName) {
		this.cadetName = cadetName;
	}

}
