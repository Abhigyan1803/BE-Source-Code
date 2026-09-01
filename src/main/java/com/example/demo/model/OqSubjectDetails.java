package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="oq_subject_details")
public class OqSubjectDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String subJectName="";

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<OqSubjectAttribute> oqSubjectAttribute;

	private Integer status;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
	
	
	public String getSubJectName() {
		return subJectName;
	}

	public void setSubJectName(String subJectName) {
		this.subJectName = subJectName;
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

	public List<OqSubjectAttribute> getOqSubjectAttribute() {
		return oqSubjectAttribute;
	}

	public void setOqSubjectAttribute(List<OqSubjectAttribute> oqSubjectAttribute) {
		this.oqSubjectAttribute = oqSubjectAttribute;
	}
	
	
	
	
	
}
