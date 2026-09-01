package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EdAssessmentOqFinal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String serviceId;
	private Long termId;
	
	private Double preMidTermTotalMarks;
	private Double preMidTermObtainedMarks;
	
	private Double preFinalTermTotalMarks;
	private Double preFinalTermObtainedMarks;
	
	private Double avgPrePostTotalMarks;
	private Double avgPrePostObtainedMarks;
	
	private Double dcCiTotalMarks;
	private Double dcCiObtainedMarks;
	
	private Double comdtTotalMarks;
	private Double comdtObtainedMarks;
	
	private Double grandTotalMarks; 
	private Double grandObtaionedMarks;
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
	public Double getPreMidTermTotalMarks() {
		return preMidTermTotalMarks;
	}
	public void setPreMidTermTotalMarks(Double preMidTermTotalMarks) {
		this.preMidTermTotalMarks = preMidTermTotalMarks;
	}
	public Double getPreMidTermObtainedMarks() {
		return preMidTermObtainedMarks;
	}
	public void setPreMidTermObtainedMarks(Double preMidTermObtainedMarks) {
		this.preMidTermObtainedMarks = preMidTermObtainedMarks;
	}
	public Double getPreFinalTermTotalMarks() {
		return preFinalTermTotalMarks;
	}
	public void setPreFinalTermTotalMarks(Double preFinalTermTotalMarks) {
		this.preFinalTermTotalMarks = preFinalTermTotalMarks;
	}
	public Double getPreFinalTermObtainedMarks() {
		return preFinalTermObtainedMarks;
	}
	public void setPreFinalTermObtainedMarks(Double preFinalTermObtainedMarks) {
		this.preFinalTermObtainedMarks = preFinalTermObtainedMarks;
	}
	public Double getAvgPrePostTotalMarks() {
		return avgPrePostTotalMarks;
	}
	public void setAvgPrePostTotalMarks(Double avgPrePostTotalMarks) {
		this.avgPrePostTotalMarks = avgPrePostTotalMarks;
	}
	public Double getAvgPrePostObtainedMarks() {
		return avgPrePostObtainedMarks;
	}
	public void setAvgPrePostObtainedMarks(Double avgPrePostObtainedMarks) {
		this.avgPrePostObtainedMarks = avgPrePostObtainedMarks;
	}
	public Double getDcCiTotalMarks() {
		return dcCiTotalMarks;
	}
	public void setDcCiTotalMarks(Double dcCiTotalMarks) {
		this.dcCiTotalMarks = dcCiTotalMarks;
	}
	public Double getDcCiObtainedMarks() {
		return dcCiObtainedMarks;
	}
	public void setDcCiObtainedMarks(Double dcCiObtainedMarks) {
		this.dcCiObtainedMarks = dcCiObtainedMarks;
	}
	public Double getComdtTotalMarks() {
		return comdtTotalMarks;
	}
	public void setComdtTotalMarks(Double comdtTotalMarks) {
		this.comdtTotalMarks = comdtTotalMarks;
	}
	public Double getComdtObtainedMarks() {
		return comdtObtainedMarks;
	}
	public void setComdtObtainedMarks(Double comdtObtainedMarks) {
		this.comdtObtainedMarks = comdtObtainedMarks;
	}
	public Double getGrandTotalMarks() {
		return grandTotalMarks;
	}
	public void setGrandTotalMarks(Double grandTotalMarks) {
		this.grandTotalMarks = grandTotalMarks;
	}
	public Double getGrandObtaionedMarks() {
		return grandObtaionedMarks;
	}
	public void setGrandObtaionedMarks(Double grandObtaionedMarks) {
		this.grandObtaionedMarks = grandObtaionedMarks;
	}
	
}
