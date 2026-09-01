package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@DynamicUpdate
public class Officer implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String personalNumber;

	// @Column(name = "rank")
	private String rankName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date substantiveDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date actingDate;

	private String regimentCrops;

	private Boolean isPcWefCommission;
	private Boolean isPcSlCommission;
	private Boolean isSscCommission;
	private Boolean isEcCommission;
	private Boolean isTaCommission;
	private String authority;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date commissionDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date substantivePromotionSeniorityDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date firstCommissionDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	private String placeOfBirth;
	private String nationality;
	private String religiousDenomination;
	private String motherTongue;
	private String medicalCategory;
	private String previousOccupation;
	private String accountNumber;
	private String idCardNo;
	private String jcoPersonalNumber;
	private String jcoRank;
	private String offrPersonalNumber;
	private String offrRank;
	private String otherReckonableServiceDetails;
	private String perAddLine1;
	private String perAddLine2;
	private String perVillage;
	private String perTehsil;
	private String perPostOffice;
	private String perCity;
	private String perDistrict;
	private String perState;
	private String perPinCode;
	private String officerBankersName;
	private String officerBankersAddLine1;
	private String officerBankersAddLine2;
	private String officerBankersVillage;
	private String officerBankersTehsil;
	private String officerBankersPostOffice;
	private String officerBankersCity;
	private String officerBankersDistrict;
	private String officerBankersState;
	private String officerBankersPinCode;
	private String whetherLocation;
	private Boolean isDeathCumRetirementGratuity;
	private Boolean isArmyGroupInsurance;
	private Boolean isDSOPFund;
	private String confirmationPlace;
	private Boolean declareConfirmation;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date confirmationDate;
	private Integer status;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosAcademicQualification> academicList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosProfessionalQualification> professionalList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosExperienceInCivilTrade> experienceInCivilTradeList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosFormerService> formerServiceList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosPassingOutTrainingEstablishment> passingOutTrainingEstablishmentList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosCourse> courseList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosPromotionExamination> promotionExaminationList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosForeignLanguage> foreignlanguageList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosIndianLanguage> indianLanguageList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosRegimentalDuty> regimentalDutyList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosStaffEmploymentOtherRegimental> staffEmploymentOtherRegimentalList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosExtraRegimentalEmployment> extraRegimentalEmploymentList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosDecoration> decorationList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosNextKin> nextKinList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosInsurancePolicy> insurancePolicyList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosFamilyDetailsA> familyDetailsAList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosFamilyDetailsB> familyDetailsBList;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<RosAnnualLeave> annualLeaveList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public Date getSubstantiveDate() {
		return substantiveDate;
	}

	public void setSubstantiveDate(Date substantiveDate) {
		this.substantiveDate = substantiveDate;
	}

	public Date getActingDate() {
		return actingDate;
	}

	public void setActingDate(Date actingDate) {
		this.actingDate = actingDate;
	}

	public String getRegimentCrops() {
		return regimentCrops;
	}

	public void setRegimentCrops(String regimentCrops) {
		this.regimentCrops = regimentCrops;
	}

	public Boolean getIsPcWefCommission() {
		return isPcWefCommission;
	}

	public void setIsPcWefCommission(Boolean isPcWefCommission) {
		this.isPcWefCommission = isPcWefCommission;
	}

	public Boolean getIsPcSlCommission() {
		return isPcSlCommission;
	}

	public void setIsPcSlCommission(Boolean isPcSlCommission) {
		this.isPcSlCommission = isPcSlCommission;
	}

	public Boolean getIsSscCommission() {
		return isSscCommission;
	}

	public void setIsSscCommission(Boolean isSscCommission) {
		this.isSscCommission = isSscCommission;
	}

	public Boolean getIsEcCommission() {
		return isEcCommission;
	}

	public void setIsEcCommission(Boolean isEcCommission) {
		this.isEcCommission = isEcCommission;
	}

	public Boolean getIsTaCommission() {
		return isTaCommission;
	}

	public void setIsTaCommission(Boolean isTaCommission) {
		this.isTaCommission = isTaCommission;
	}

	public Date getCommissionDate() {
		return commissionDate;
	}

	public void setCommissionDate(Date commissionDate) {
		this.commissionDate = commissionDate;
	}

	public Date getSubstantivePromotionSeniorityDate() {
		return substantivePromotionSeniorityDate;
	}

	public void setSubstantivePromotionSeniorityDate(Date substantivePromotionSeniorityDate) {
		this.substantivePromotionSeniorityDate = substantivePromotionSeniorityDate;
	}

	public Date getFirstCommissionDate() {
		return firstCommissionDate;
	}

	public void setFirstCommissionDate(Date firstCommissionDate) {
		this.firstCommissionDate = firstCommissionDate;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getReligiousDenomination() {
		return religiousDenomination;
	}

	public void setReligiousDenomination(String religiousDenomination) {
		this.religiousDenomination = religiousDenomination;
	}

	public String getMotherTongue() {
		return motherTongue;
	}

	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}

	public String getMedicalCategory() {
		return medicalCategory;
	}

	public void setMedicalCategory(String medicalCategory) {
		this.medicalCategory = medicalCategory;
	}

	public String getPreviousOccupation() {
		return previousOccupation;
	}

	public void setPreviousOccupation(String previousOccupation) {
		this.previousOccupation = previousOccupation;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getJcoPersonalNumber() {
		return jcoPersonalNumber;
	}

	public void setJcoPersonalNumber(String jcoPersonalNumber) {
		this.jcoPersonalNumber = jcoPersonalNumber;
	}

	public String getJcoRank() {
		return jcoRank;
	}

	public void setJcoRank(String jcoRank) {
		this.jcoRank = jcoRank;
	}

	public String getOffrPersonalNumber() {
		return offrPersonalNumber;
	}

	public void setOffrPersonalNumber(String offrPersonalNumber) {
		this.offrPersonalNumber = offrPersonalNumber;
	}

	public String getOffrRank() {
		return offrRank;
	}

	public void setOffrRank(String offrRank) {
		this.offrRank = offrRank;
	}

	public String getOtherReckonableServiceDetails() {
		return otherReckonableServiceDetails;
	}

	public void setOtherReckonableServiceDetails(String otherReckonableServiceDetails) {
		this.otherReckonableServiceDetails = otherReckonableServiceDetails;
	}

	public String getPerAddLine1() {
		return perAddLine1;
	}

	public void setPerAddLine1(String perAddLine1) {
		this.perAddLine1 = perAddLine1;
	}

	public String getPerAddLine2() {
		return perAddLine2;
	}

	public void setPerAddLine2(String perAddLine2) {
		this.perAddLine2 = perAddLine2;
	}

	public String getPerVillage() {
		return perVillage;
	}

	public void setPerVillage(String perVillage) {
		this.perVillage = perVillage;
	}

	public String getPerTehsil() {
		return perTehsil;
	}

	public void setPerTehsil(String perTehsil) {
		this.perTehsil = perTehsil;
	}

	public String getPerPostOffice() {
		return perPostOffice;
	}

	public void setPerPostOffice(String perPostOffice) {
		this.perPostOffice = perPostOffice;
	}

	public String getPerCity() {
		return perCity;
	}

	public void setPerCity(String perCity) {
		this.perCity = perCity;
	}

	public String getPerDistrict() {
		return perDistrict;
	}

	public void setPerDistrict(String perDistrict) {
		this.perDistrict = perDistrict;
	}

	public String getPerState() {
		return perState;
	}

	public void setPerState(String perState) {
		this.perState = perState;
	}

	public String getPerPinCode() {
		return perPinCode;
	}

	public void setPerPinCode(String perPinCode) {
		this.perPinCode = perPinCode;
	}

	public String getOfficerBankersName() {
		return officerBankersName;
	}

	public void setOfficerBankersName(String officerBankersName) {
		this.officerBankersName = officerBankersName;
	}

	public String getOfficerBankersAddLine1() {
		return officerBankersAddLine1;
	}

	public void setOfficerBankersAddLine1(String officerBankersAddLine1) {
		this.officerBankersAddLine1 = officerBankersAddLine1;
	}

	public String getOfficerBankersAddLine2() {
		return officerBankersAddLine2;
	}

	public void setOfficerBankersAddLine2(String officerBankersAddLine2) {
		this.officerBankersAddLine2 = officerBankersAddLine2;
	}

	public String getOfficerBankersVillage() {
		return officerBankersVillage;
	}

	public void setOfficerBankersVillage(String officerBankersVillage) {
		this.officerBankersVillage = officerBankersVillage;
	}

	public String getOfficerBankersTehsil() {
		return officerBankersTehsil;
	}

	public void setOfficerBankersTehsil(String officerBankersTehsil) {
		this.officerBankersTehsil = officerBankersTehsil;
	}

	public String getOfficerBankersPostOffice() {
		return officerBankersPostOffice;
	}

	public void setOfficerBankersPostOffice(String officerBankersPostOffice) {
		this.officerBankersPostOffice = officerBankersPostOffice;
	}

	public String getOfficerBankersCity() {
		return officerBankersCity;
	}

	public void setOfficerBankersCity(String officerBankersCity) {
		this.officerBankersCity = officerBankersCity;
	}

	public String getOfficerBankersDistrict() {
		return officerBankersDistrict;
	}

	public void setOfficerBankersDistrict(String officerBankersDistrict) {
		this.officerBankersDistrict = officerBankersDistrict;
	}

	public String getOfficerBankersState() {
		return officerBankersState;
	}

	public void setOfficerBankersState(String officerBankersState) {
		this.officerBankersState = officerBankersState;
	}

	public String getOfficerBankersPinCode() {
		return officerBankersPinCode;
	}

	public void setOfficerBankersPinCode(String officerBankersPinCode) {
		this.officerBankersPinCode = officerBankersPinCode;
	}

	public String getWhetherLocation() {
		return whetherLocation;
	}

	public void setWhetherLocation(String whetherLocation) {
		this.whetherLocation = whetherLocation;
	}

	public Boolean getIsDeathCumRetirementGratuity() {
		return isDeathCumRetirementGratuity;
	}

	public void setIsDeathCumRetirementGratuity(Boolean isDeathCumRetirementGratuity) {
		this.isDeathCumRetirementGratuity = isDeathCumRetirementGratuity;
	}

	public Boolean getIsArmyGroupInsurance() {
		return isArmyGroupInsurance;
	}

	public void setIsArmyGroupInsurance(Boolean isArmyGroupInsurance) {
		this.isArmyGroupInsurance = isArmyGroupInsurance;
	}

	public Boolean getIsDSOPFund() {
		return isDSOPFund;
	}

	public void setIsDSOPFund(Boolean isDSOPFund) {
		this.isDSOPFund = isDSOPFund;
	}

	public String getConfirmationPlace() {
		return confirmationPlace;
	}

	public void setConfirmationPlace(String confirmationPlace) {
		this.confirmationPlace = confirmationPlace;
	}

	public Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
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

	public List<RosAcademicQualification> getAcademicList() {
		return academicList;
	}

	public void setAcademicList(List<RosAcademicQualification> academicList) {
		this.academicList = academicList;
	}

	public List<RosProfessionalQualification> getProfessionalList() {
		return professionalList;
	}

	public void setProfessionalList(List<RosProfessionalQualification> professionalList) {
		this.professionalList = professionalList;
	}

	public List<RosExperienceInCivilTrade> getExperienceInCivilTradeList() {
		return experienceInCivilTradeList;
	}

	public void setExperienceInCivilTradeList(List<RosExperienceInCivilTrade> experienceInCivilTradeList) {
		this.experienceInCivilTradeList = experienceInCivilTradeList;
	}

	public List<RosFormerService> getFormerServiceList() {
		return formerServiceList;
	}

	public void setFormerServiceList(List<RosFormerService> formerServiceList) {
		this.formerServiceList = formerServiceList;
	}

	public List<RosPassingOutTrainingEstablishment> getPassingOutTrainingEstablishmentList() {
		return passingOutTrainingEstablishmentList;
	}

	public void setPassingOutTrainingEstablishmentList(
			List<RosPassingOutTrainingEstablishment> passingOutTrainingEstablishmentList) {
		this.passingOutTrainingEstablishmentList = passingOutTrainingEstablishmentList;
	}

	public List<RosCourse> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<RosCourse> courseList) {
		this.courseList = courseList;
	}

	public List<RosPromotionExamination> getPromotionExaminationList() {
		return promotionExaminationList;
	}

	public void setPromotionExaminationList(List<RosPromotionExamination> promotionExaminationList) {
		this.promotionExaminationList = promotionExaminationList;
	}

	public List<RosForeignLanguage> getForeignlanguageList() {
		return foreignlanguageList;
	}

	public void setForeignlanguageList(List<RosForeignLanguage> foreignlanguageList) {
		this.foreignlanguageList = foreignlanguageList;
	}

	public List<RosIndianLanguage> getIndianLanguageList() {
		return indianLanguageList;
	}

	public void setIndianLanguageList(List<RosIndianLanguage> indianLanguageList) {
		this.indianLanguageList = indianLanguageList;
	}

	public List<RosRegimentalDuty> getRegimentalDutyList() {
		return regimentalDutyList;
	}

	public void setRegimentalDutyList(List<RosRegimentalDuty> regimentalDutyList) {
		this.regimentalDutyList = regimentalDutyList;
	}

	public List<RosStaffEmploymentOtherRegimental> getStaffEmploymentOtherRegimentalList() {
		return staffEmploymentOtherRegimentalList;
	}

	public void setStaffEmploymentOtherRegimentalList(
			List<RosStaffEmploymentOtherRegimental> staffEmploymentOtherRegimentalList) {
		this.staffEmploymentOtherRegimentalList = staffEmploymentOtherRegimentalList;
	}

	public List<RosExtraRegimentalEmployment> getExtraRegimentalEmploymentList() {
		return extraRegimentalEmploymentList;
	}

	public void setExtraRegimentalEmploymentList(List<RosExtraRegimentalEmployment> extraRegimentalEmploymentList) {
		this.extraRegimentalEmploymentList = extraRegimentalEmploymentList;
	}

	public List<RosDecoration> getDecorationList() {
		return decorationList;
	}

	public void setDecorationList(List<RosDecoration> decorationList) {
		this.decorationList = decorationList;
	}

	public List<RosNextKin> getNextKinList() {
		return nextKinList;
	}

	public void setNextKinList(List<RosNextKin> nextKinList) {
		this.nextKinList = nextKinList;
	}

	public List<RosInsurancePolicy> getInsurancePolicyList() {
		return insurancePolicyList;
	}

	public void setInsurancePolicyList(List<RosInsurancePolicy> insurancePolicyList) {
		this.insurancePolicyList = insurancePolicyList;
	}

	public List<RosFamilyDetailsA> getFamilyDetailsAList() {
		return familyDetailsAList;
	}

	public void setFamilyDetailsAList(List<RosFamilyDetailsA> familyDetailsAList) {
		this.familyDetailsAList = familyDetailsAList;
	}

	public List<RosFamilyDetailsB> getFamilyDetailsBList() {
		return familyDetailsBList;
	}

	public void setFamilyDetailsBList(List<RosFamilyDetailsB> familyDetailsBList) {
		this.familyDetailsBList = familyDetailsBList;
	}

	public List<RosAnnualLeave> getAnnualLeaveList() {
		return annualLeaveList;
	}

	public void setAnnualLeaveList(List<RosAnnualLeave> annualLeaveList) {
		this.annualLeaveList = annualLeaveList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Boolean getDeclareConfirmation() {
		return declareConfirmation;
	}

	public void setDeclareConfirmation(Boolean declareConfirmation) {
		this.declareConfirmation = declareConfirmation;
	}

}
