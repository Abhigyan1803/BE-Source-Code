package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="oq_pl_result")
public class OqPlResult {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String serviceId;

	@ManyToOne
	private OqSubjectAttribute oqSubjectAttribute;

	private Integer plCdrMarks;

	private Long termId;

	private Integer status;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private Long apptType;

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

	public OqSubjectAttribute getOqSubjectAttribute() {
		return oqSubjectAttribute;
	}

	public void setOqSubjectAttribute(OqSubjectAttribute oqSubjectAttribute) {
		this.oqSubjectAttribute = oqSubjectAttribute;
	}

	public Integer getPlCdrMarks() {
		return plCdrMarks;
	}

	public void setPlCdrMarks(Integer plCdrMarks) {
		this.plCdrMarks = plCdrMarks;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getApptType() {
		return apptType;
	}

	public void setApptType(Long apptType) {
		this.apptType = apptType;
	}
	
	
	

}
