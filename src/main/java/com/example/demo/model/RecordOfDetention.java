package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "recordOfDetention")
public class RecordOfDetention {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String serviceId;
private Long termId;
private Integer status;
private Date wef;
@Lob
private String reason;
@Lob
private String  progress;
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
public Date getWef() {
	return wef;
}
public void setWef(Date wef) {
	this.wef = wef;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public String getProgress() {
	return progress;
}
public void setProgress(String progress) {
	this.progress = progress;
}

}
