package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question_bank")
public class QuestionBank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_bank_id")
	private Long questionBankId;

	@Column(name = "question_bank_url")
	private String queBankurl;

	private String questionBankName;

	@Column(name = "academicTermId_fk")
	private Long academicTermIdfk;

	public Long getQuestionBankId() {
		return questionBankId;
	}

	public void setQuestionBankId(Long questionBankId) {
		this.questionBankId = questionBankId;
	}

	public String getQueBankurl() {
		return queBankurl;
	}

	public void setQueBankurl(String queBankurl) {
		this.queBankurl = queBankurl;
	}

	public Long getAcademicTermIdfk() {
		return academicTermIdfk;
	}

	public void setAcademicTermIdfk(Long academicTermIdfk) {
		this.academicTermIdfk = academicTermIdfk;
	}

	public String getQuestionBankName() {
		return questionBankName;
	}

	public void setQuestionBankName(String questionBankName) {
		this.questionBankName = questionBankName;
	}

}
