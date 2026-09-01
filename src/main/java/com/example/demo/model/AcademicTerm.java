package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "academic_term")
public class AcademicTerm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "academic_term_id")
	private Long academicTermId;

	@Column(name = "term_id")
	private long termId;
	@Column(name = "paper")
	private String paper;
	@Column(name = "subject_name")
	private String subjectName;

	private Integer status;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@OneToMany(targetEntity = TermTopic.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "academicTermId_fk", referencedColumnName = "academic_term_id")
	List<TermTopic> termTopic;

	@OneToMany(targetEntity = QuestionBank.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "academicTermId_fk", referencedColumnName = "academic_term_id")
	List<QuestionBank> questionBank;

	@OneToMany(targetEntity = PreviousTermPaper.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "academicTermId_fk", referencedColumnName = "academic_term_id")
	List<PreviousTermPaper> previousTermPaper;

	public Long getAcademicTermId() {
		return academicTermId;
	}

	public void setAcademicTermId(Long academicTermId) {
		this.academicTermId = academicTermId;
	}

	public long getTermId() {
		return termId;
	}

	public void setTermId(long termId) {
		this.termId = termId;
	}

	public String getPaper() {
		return paper;
	}

	public void setPaper(String paper) {
		this.paper = paper;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public List<TermTopic> getTermTopic() {
		return termTopic;
	}

	public void setTermTopic(List<TermTopic> termTopic) {
		this.termTopic = termTopic;
	}

	public List<QuestionBank> getQuestionBank() {
		return questionBank;
	}

	public void setQuestionBank(List<QuestionBank> questionBank) {
		this.questionBank = questionBank;
	}

	public List<PreviousTermPaper> getPreviousTermPaper() {
		return previousTermPaper;
	}

	public void setPreviousTermPaper(List<PreviousTermPaper> previousTermPaper) {
		this.previousTermPaper = previousTermPaper;
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

}
