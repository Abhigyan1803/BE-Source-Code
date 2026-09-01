package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
@Entity
@Table(name = "eDossier_lve")
public class EDossierLve {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String serviceId;
private Long termId;
private Integer status;
private Date lveFrom;
private Date lveTo;
@Lob
private String reason;
private String address;
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
public Date getLveFrom() {
	return lveFrom;
}
public void setLveFrom(Date lveFrom) {
	this.lveFrom = lveFrom;
}
public Date getLveTo() {
	return lveTo;
}
public void setLveTo(Date lveTo) {
	this.lveTo = lveTo;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}

}
