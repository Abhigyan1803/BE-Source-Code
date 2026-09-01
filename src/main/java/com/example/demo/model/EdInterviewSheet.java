package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "ed_interviewsheet")
public class EdInterviewSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serviceId;
	private Long termId;
	private Integer status;
	private Date appdate;
	@Lob
	private String appearence;
	@Lob
	private String familyback;
	private Date famDate;
	private Boolean isViewByGc;

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

	public Date getAppdate() {
		return appdate;
	}

	public void setAppdate(Date appdate) {
		this.appdate = appdate;
	}

	public String getAppearence() {
		return appearence;
	}

	public void setAppearence(String appearence) {
		this.appearence = appearence;
	}

	public String getFamilyback() {
		return familyback;
	}

	public void setFamilyback(String familyback) {
		this.familyback = familyback;
	}

	public Date getFamDate() {
		return famDate;
	}

	public void setFamDate(Date famDate) {
		this.famDate = famDate;
	}

	public String getWorkExp() {
		return workExp;
	}

	public void setWorkExp(String workExp) {
		this.workExp = workExp;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getInitialAss() {
		return initialAss;
	}

	public void setInitialAss(String initialAss) {
		this.initialAss = initialAss;
	}

	public Date getIniDate() {
		return iniDate;
	}

	public void setIniDate(Date iniDate) {
		this.iniDate = iniDate;
	}

	public String getMisc() {
		return misc;
	}

	public void setMisc(String misc) {
		this.misc = misc;
	}

	public Date getMisDate() {
		return misDate;
	}

	public void setMisDate(Date misDate) {
		this.misDate = misDate;
	}

	public String getAnyPts() {
		return anyPts;
	}

	public void setAnyPts(String anyPts) {
		this.anyPts = anyPts;
	}

	public Date getAnyDate() {
		return anyDate;
	}

	public void setAnyDate(Date anyDate) {
		this.anyDate = anyDate;
	}

	public String getiHaveExp() {
		return iHaveExp;
	}

	public void setiHaveExp(String iHaveExp) {
		this.iHaveExp = iHaveExp;
	}

	public Date getIhavDate() {
		return ihavDate;
	}

	public void setIhavDate(Date ihavDate) {
		this.ihavDate = ihavDate;
	}

	@Lob
	private String workExp;
	private Date workDate;
	@Lob
	private String initialAss;
	private Date iniDate;
	@Lob
	private String misc;
	private Date misDate;

	@Lob
	private String anyPts;
	private Date anyDate;
	@Lob
	private String iHaveExp;
	private Date ihavDate;

	private String appGcInitialsWithDate;
	private String famiGcInitialsWithDate;
	private String workGcInitialsWithDate;
	private String iniGcInitialsWithDate;
	private String misGcInitialsWithDate;
	private String anyGcInitialsWithDate;
	private String ihavGcInitialsWithDate;

	public Boolean getIsViewByGc() {
		return isViewByGc;
	}

	public void setIsViewByGc(Boolean isViewByGc) {
		this.isViewByGc = isViewByGc;
	}

	public String getAppGcInitialsWithDate() {
		return appGcInitialsWithDate;
	}

	public void setAppGcInitialsWithDate(String appGcInitialsWithDate) {
		this.appGcInitialsWithDate = appGcInitialsWithDate;
	}

	public String getFamiGcInitialsWithDate() {
		return famiGcInitialsWithDate;
	}

	public void setFamiGcInitialsWithDate(String famiGcInitialsWithDate) {
		this.famiGcInitialsWithDate = famiGcInitialsWithDate;
	}

	public String getWorkGcInitialsWithDate() {
		return workGcInitialsWithDate;
	}

	public void setWorkGcInitialsWithDate(String workGcInitialsWithDate) {
		this.workGcInitialsWithDate = workGcInitialsWithDate;
	}

	public String getIniGcInitialsWithDate() {
		return iniGcInitialsWithDate;
	}

	public void setIniGcInitialsWithDate(String iniGcInitialsWithDate) {
		this.iniGcInitialsWithDate = iniGcInitialsWithDate;
	}

	public String getMisGcInitialsWithDate() {
		return misGcInitialsWithDate;
	}

	public void setMisGcInitialsWithDate(String misGcInitialsWithDate) {
		this.misGcInitialsWithDate = misGcInitialsWithDate;
	}

	public String getAnyGcInitialsWithDate() {
		return anyGcInitialsWithDate;
	}

	public void setAnyGcInitialsWithDate(String anyGcInitialsWithDate) {
		this.anyGcInitialsWithDate = anyGcInitialsWithDate;
	}

	public String getIhavGcInitialsWithDate() {
		return ihavGcInitialsWithDate;
	}

	public void setIhavGcInitialsWithDate(String ihavGcInitialsWithDate) {
		this.ihavGcInitialsWithDate = ihavGcInitialsWithDate;
	}

}
