package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

@Entity
public class IntellectualSkillsSubject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Lob
	private String subjectName;

	private Integer status;
	private Long termId;

	private Integer midTotalMarks;
	private Integer finalTotalMarks;
	@Transient
	private Integer midObtainedMarks;
	@Transient
	private Integer finalObtainedMarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public Integer getMidTotalMarks() {
		return midTotalMarks;
	}

	public void setMidTotalMarks(Integer midTotalMarks) {
		this.midTotalMarks = midTotalMarks;
	}

	public Integer getFinalTotalMarks() {
		return finalTotalMarks;
	}

	public void setFinalTotalMarks(Integer finalTotalMarks) {
		this.finalTotalMarks = finalTotalMarks;
	}

	public Integer getMidObtainedMarks() {
		return midObtainedMarks;
	}

	public void setMidObtainedMarks(Integer midObtainedMarks) {
		this.midObtainedMarks = midObtainedMarks;
	}

	public Integer getFinalObtainedMarks() {
		return finalObtainedMarks;
	}

	public void setFinalObtainedMarks(Integer finalObtainedMarks) {
		this.finalObtainedMarks = finalObtainedMarks;
	}

}
