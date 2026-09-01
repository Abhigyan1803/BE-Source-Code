package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "previous_term_paper")
public class PreviousTermPaper {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "previous_term_paper_id")
	private Long previousTermPaperId;

	@Column(name = "url")
	private String url;

	@Column(name = "academicTermId_fk")
	private Long academicTermIdfk;

	private String paperName;

	public Long getPreviousTermPaperId() {
		return previousTermPaperId;
	}

	public void setPreviousTermPaperId(Long previousTermPaperId) {
		this.previousTermPaperId = previousTermPaperId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getAcademicTermIdfk() {
		return academicTermIdfk;
	}

	public void setAcademicTermIdfk(Long academicTermIdfk) {
		this.academicTermIdfk = academicTermIdfk;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

}
