package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "adventure")
public class Adventure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String serviceId;
private Long termId;
private Integer status;
@Lob
private String hike;
private String loc;
@Lob
private String remarks;
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
public Long getTermId() {
	return termId;
}
public void setTermId(Long termId) {
	this.termId = termId;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public String getHike() {
	return hike;
}
public void setHike(String hike) {
	this.hike = hike;
}
public String getLoc() {
	return loc;
}
public void setLoc(String loc) {
	this.loc = loc;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}

}
