package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Table
@Entity(name = "commissioning_details")
public class CommissioningDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String IC_Number;
	private String unit_Posted_To;

	private String choice_of_Arms;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_of_Commissioning;

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

	public String getIC_Number() {
		return IC_Number;
	}

	public void setIC_Number(String iC_Number) {
		IC_Number = iC_Number;
	}

	public String getUnit_Posted_To() {
		return unit_Posted_To;
	}

	public void setUnit_Posted_To(String unit_Posted_To) {
		this.unit_Posted_To = unit_Posted_To;
	}

	public String getChoice_of_Arms() {
		return choice_of_Arms;
	}

	public void setChoice_of_Arms(String choice_of_Arms) {
		this.choice_of_Arms = choice_of_Arms;
	}

	public Date getDate_of_Commissioning() {
		return date_of_Commissioning;
	}

	public void setDate_of_Commissioning(Date date_of_Commissioning) {
		this.date_of_Commissioning = date_of_Commissioning;
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
	

}
