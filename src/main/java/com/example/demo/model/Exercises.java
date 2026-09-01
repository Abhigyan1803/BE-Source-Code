package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="Exercises")
public class Exercises {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String exercise="";
	private String dsBriefing="";
	private String recceTewt="";
	private String bbe="";
	private String verbalOrders="";
	private String smd="";
	private String duration="";
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	private int status=0;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	@ManyToOne
	private Term term;
	
	@OneToOne
	private RespDetails respDetails;

	@ManyToOne
	private TermSeason termSeason;
	
	private String year="";
	
	private String url="";
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getExercise() {
		return exercise;
	}
	public void setExercise(String exercise) {
		this.exercise = exercise;
	}
	public String getDsBriefing() {
		return dsBriefing;
	}
	public void setDsBriefing(String dsBriefing) {
		this.dsBriefing = dsBriefing;
	}
	public String getRecceTewt() {
		return recceTewt;
	}
	public void setRecceTewt(String recceTewt) {
		this.recceTewt = recceTewt;
	}
	public String getBbe() {
		return bbe;
	}
	public void setBbe(String bbe) {
		this.bbe = bbe;
	}
	public String getVerbalOrders() {
		return verbalOrders;
	}
	public void setVerbalOrders(String verbalOrders) {
		this.verbalOrders = verbalOrders;
	}
	public String getSmd() {
		return smd;
	}
	public void setSmd(String smd) {
		this.smd = smd;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public RespDetails getRespDetails() {
		return respDetails;
	}
	public void setRespDetails(RespDetails respDetails) {
		this.respDetails = respDetails;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public TermSeason getTermSeason() {
		return termSeason;
	}
	public void setTermSeason(TermSeason termSeason) {
		this.termSeason = termSeason;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
