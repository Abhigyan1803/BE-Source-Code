package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sun.istack.NotNull;

@Entity
@DynamicUpdate
public class Cadet implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String serviceId;

	@Column(unique = true)
	private String username;

	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	private String serialNo;

	private String academyNo;

	private String battalian;

	private String company;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date accDate;

	private String termSession;

	private String year;
	private String passoutYear;
	private String passoutSession;

	private String course;

	// private String term;
	private Long term;

	@Transient
	private String termName;

	private String accStatus;

	private String miscellaneous;

	private String adharImg;

	private String panImg;

	private String courseSerNo;

	private String entry;

	private String name;

	private String profileImg;

	private String upscRollNo;

	private String ssbSerNo;

	private String ssbBatchNo;

	private String chestNo;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;

	private String birthPlace;

	private String religion;

	private String cast;

	private String bloodGroup;

	private String nationality;

	private String identificationMarks;

	// private String fatherName;

	// private String address;

	private String contactNo;

	@Column(unique = true)
	private String email;

	// private String fatherProfession;
	//
	// private String parentMonthlyIncome;

	// private String professionPriorIMA;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<ProfessionalDetails> professionalDetails;

	private String govtEmp;

	private Integer isArmy;

	private Integer isNavy;

	private Integer isAirForce;

	private Integer isTA;

	private Integer isCivil;

	private Integer isGovernment;

	private Integer isSemiGovernment;

	private String state;

	private String maritalStatus;

	private String adharNo;

	private String belongsTo;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CadetEducation> cadetEducation;

	private Integer memberOfNCC_OTU;

	private Integer memberOfNCC;

	private Integer memberOfOTU;

	@Column(name = "cadet_rank")
	private String cadetRank;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String NCCDate;

	private String certObtained;

	private String division;

	private String trainingPeriod;

	// private String nameOfNextKinRelation;

	// private String addressOfNextKinRelation;

	private String sports;

	private String sportsLevel;

	private String hobies;

	private Integer everTrainedWithIMAOTANDA;

	private Integer isTrainedWithIMA;

	private Integer isTrainedWithOTA;

	private Integer isTrainedWithNDA;

	private String trainingAcademyNo;

	private String trainingCourseSerNo;

	private String trainingJoiningDate;

	private String trainingLeavingDate;

	@Lob
	private String reasionOfLeaving;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date IMA_JoiningDate;

	private Integer status;

	private Integer isFirstLogin;

	private String motheTongue;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<ForeignLanguages> foreignLanguages;

	private String addressLine1;
	private String addressLine2;
	private String village;
	private String tehsil;
	private String postOffice;
	private String city;
	private String district;
	private String AddressState;
	private String pincode;

	@Column(length = 100)
	private String postAndOrg;

	@Column(length = 100)
	private String armAndService;

	@Column(length = 100)
	private String medalAward;

	@Column(length = 100)
	private String fGPA;

	@Column(length = 100)
	private String achievements;

	private Date visaExpiredDate;

	@Column(length = 50)
	private String spouseName;

	@Column(length = 10)
	private String noOfChild;

	@Column(length = 200)
	private String detailsOfNOk;

	@Column(length = 20)
	private String bankAccountNo;

	@Column(length = 100)
	private String ssbPlan;

	@Column(length = 100)
	private String spouseDetails;

	@Column(length = 20)
	private String marriageDate;

	@Column(length = 20)
	private String orderNo;

	public String getPostAndOrg() {
		return postAndOrg;
	}

	public void setPostAndOrg(String postAndOrg) {
		this.postAndOrg = postAndOrg;
	}

	public String getArmAndService() {
		return armAndService;
	}

	public void setArmAndService(String armAndService) {
		this.armAndService = armAndService;
	}

	public String getMedalAward() {
		return medalAward;
	}

	public void setMedalAward(String medalAward) {
		this.medalAward = medalAward;
	}

	public String getfGPA() {
		return fGPA;
	}

	public void setfGPA(String fGPA) {
		this.fGPA = fGPA;
	}

	public String getAchievements() {
		return achievements;
	}

	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}

	public Date getVisaExpiredDate() {
		return visaExpiredDate;
	}

	public void setVisaExpiredDate(Date visaExpiredDate) {
		this.visaExpiredDate = visaExpiredDate;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getNoOfChild() {
		return noOfChild;
	}

	public void setNoOfChild(String noOfChild) {
		this.noOfChild = noOfChild;
	}

	public String getDetailsOfNOk() {
		return detailsOfNOk;
	}

	public void setDetailsOfNOk(String detailsOfNOk) {
		this.detailsOfNOk = detailsOfNOk;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getSsbPlan() {
		return ssbPlan;
	}

	public void setSsbPlan(String ssbPlan) {
		this.ssbPlan = ssbPlan;
	}

	public Double getPay() {
		return pay;
	}

	public void setPay(Double pay) {
		this.pay = pay;
	}

	public String getAppt() {
		return appt;
	}

	public void setAppt(String appt) {
		this.appt = appt;
	}

	public String getMerit() {
		return merit;
	}

	public void setMerit(String merit) {
		this.merit = merit;
	}

	private Double pay;

	@Column(length = 100)
	private String appt;

	@Column(length = 20)
	private String merit;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "family_details_id")
	private FamilyDetails familyDetails;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "commissioning_details_id")
	private CommissioningDetails commissioningDetails;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vaccination_details_id")
	private VaccinationDetails vaccinationDetails;

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

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getCourseSerNo() {
		return courseSerNo;
	}

	public void setCourseSerNo(String courseSerNo) {
		this.courseSerNo = courseSerNo;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getUpscRollNo() {
		return upscRollNo;
	}

	public void setUpscRollNo(String upscRollNo) {
		this.upscRollNo = upscRollNo;
	}

	public String getSsbSerNo() {
		return ssbSerNo;
	}

	public void setSsbSerNo(String ssbSerNo) {
		this.ssbSerNo = ssbSerNo;
	}

	public String getSsbBatchNo() {
		return ssbBatchNo;
	}

	public void setSsbBatchNo(String ssbBatchNo) {
		this.ssbBatchNo = ssbBatchNo;
	}

	public String getChestNo() {
		return chestNo;
	}

	public void setChestNo(String chestNo) {
		this.chestNo = chestNo;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getIdentificationMarks() {
		return identificationMarks;
	}

	public void setIdentificationMarks(String identificationMarks) {
		this.identificationMarks = identificationMarks;
	}

	// public String getFatherName() {
	// return fatherName;
	// }
	//
	// public void setFatherName(String fatherName) {
	// this.fatherName = fatherName;
	// }

	// public String getAddress() {
	// return address;
	// }
	//
	// public void setAddress(String address) {
	// this.address = address;
	// }

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// public String getFatherProfession() {
	// return fatherProfession;
	// }
	//
	// public void setFatherProfession(String fatherProfession) {
	// this.fatherProfession = fatherProfession;
	// }
	//
	// public String getParentMonthlyIncome() {
	// return parentMonthlyIncome;
	// }
	//
	// public void setParentMonthlyIncome(String parentMonthlyIncome) {
	// this.parentMonthlyIncome = parentMonthlyIncome;
	// }

	// public String getProfessionPriorIMA() {
	// return professionPriorIMA;
	// }
	//
	// public void setProfessionPriorIMA(String professionPriorIMA) {
	// this.professionPriorIMA = professionPriorIMA;
	// }

	public String getGovtEmp() {
		return govtEmp;
	}

	public void setGovtEmp(String govtEmp) {
		this.govtEmp = govtEmp;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getAdharNo() {
		return adharNo;
	}

	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}

	public String getBelongsTo() {
		return belongsTo;
	}

	public void setBelongsTo(String belongsTo) {
		this.belongsTo = belongsTo;
	}

	public Integer getMemberOfNCC_OTU() {
		return memberOfNCC_OTU;
	}

	public void setMemberOfNCC_OTU(Integer memberOfNCC_OTU) {
		this.memberOfNCC_OTU = memberOfNCC_OTU;
	}

	public String getCadetRank() {
		return cadetRank;
	}

	public void setCadetRank(String cadetRank) {
		this.cadetRank = cadetRank;
	}

	public String getNCCDate() {
		return NCCDate;
	}

	public void setNCCDate(String nCCDate) {
		NCCDate = nCCDate;
	}

	public String getCertObtained() {
		return certObtained;
	}

	public void setCertObtained(String certObtained) {
		this.certObtained = certObtained;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getTrainingPeriod() {
		return trainingPeriod;
	}

	public void setTrainingPeriod(String trainingPeriod) {
		this.trainingPeriod = trainingPeriod;
	}

	// public String getNameOfNextKinRelation() {
	// return nameOfNextKinRelation;
	// }
	//
	// public void setNameOfNextKinRelation(String nameOfNextKinRelation) {
	// this.nameOfNextKinRelation = nameOfNextKinRelation;
	// }
	//
	// public String getAddressOfNextKinRelation() {
	// return addressOfNextKinRelation;
	// }
	//
	// public void setAddressOfNextKinRelation(String addressOfNextKinRelation) {
	// this.addressOfNextKinRelation = addressOfNextKinRelation;
	// }

	public String getSports() {
		return sports;
	}

	public void setSports(String sports) {
		this.sports = sports;
	}

	public String getSportsLevel() {
		return sportsLevel;
	}

	public void setSportsLevel(String sportsLevel) {
		this.sportsLevel = sportsLevel;
	}

	public String getHobies() {
		return hobies;
	}

	public void setHobies(String hobies) {
		this.hobies = hobies;
	}

	public Integer getEverTrainedWithIMAOTANDA() {
		return everTrainedWithIMAOTANDA;
	}

	public void setEverTrainedWithIMAOTANDA(Integer everTrainedWithIMAOTANDA) {
		this.everTrainedWithIMAOTANDA = everTrainedWithIMAOTANDA;
	}

	public String getTrainingAcademyNo() {
		return trainingAcademyNo;
	}

	public void setTrainingAcademyNo(String trainingAcademyNo) {
		this.trainingAcademyNo = trainingAcademyNo;
	}

	public String getTrainingCourseSerNo() {
		return trainingCourseSerNo;
	}

	public void setTrainingCourseSerNo(String trainingCourseSerNo) {
		this.trainingCourseSerNo = trainingCourseSerNo;
	}

	public String getTrainingJoiningDate() {
		return trainingJoiningDate;
	}

	public void setTrainingJoiningDate(String trainingJoiningDate) {
		this.trainingJoiningDate = trainingJoiningDate;
	}

	public String getTrainingLeavingDate() {
		return trainingLeavingDate;
	}

	public void setTrainingLeavingDate(String trainingLeavingDate) {
		this.trainingLeavingDate = trainingLeavingDate;
	}

	public String getReasionOfLeaving() {
		return reasionOfLeaving;
	}

	public void setReasionOfLeaving(String reasionOfLeaving) {
		this.reasionOfLeaving = reasionOfLeaving;
	}

	public Date getIMA_JoiningDate() {
		return IMA_JoiningDate;
	}

	public void setIMA_JoiningDate(Date iMA_JoiningDate) {
		IMA_JoiningDate = iMA_JoiningDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public List<CadetEducation> getCadetEducation() {
		return cadetEducation;
	}

	public void setCadetEducation(List<CadetEducation> cadetEducation) {
		this.cadetEducation = cadetEducation;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getBattalian() {
		return battalian;
	}

	public void setBattalian(String battalian) {
		this.battalian = battalian;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTermSession() {
		return termSession;
	}

	public void setTermSession(String termSession) {
		this.termSession = termSession;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Long getTerm() {
		return term;
	}

	public void setTerm(Long term) {
		this.term = term;
	}

	public String getAdharImg() {
		return adharImg;
	}

	public void setAdharImg(String adharImg) {
		this.adharImg = adharImg;
	}

	public String getPanImg() {
		return panImg;
	}

	public void setPanImg(String panImg) {
		this.panImg = panImg;
	}

	public Integer getIsTrainedWithIMA() {
		return isTrainedWithIMA;
	}

	public void setIsTrainedWithIMA(Integer isTrainedWithIMA) {
		this.isTrainedWithIMA = isTrainedWithIMA;
	}

	public Integer getIsTrainedWithOTA() {
		return isTrainedWithOTA;
	}

	public void setIsTrainedWithOTA(Integer isTrainedWithOTA) {
		this.isTrainedWithOTA = isTrainedWithOTA;
	}

	public Integer getIsTrainedWithNDA() {
		return isTrainedWithNDA;
	}

	public void setIsTrainedWithNDA(Integer isTrainedWithNDA) {
		this.isTrainedWithNDA = isTrainedWithNDA;
	}

	public Integer getIsArmy() {
		return isArmy;
	}

	public void setIsArmy(Integer isArmy) {
		this.isArmy = isArmy;
	}

	public Integer getIsNavy() {
		return isNavy;
	}

	public void setIsNavy(Integer isNavy) {
		this.isNavy = isNavy;
	}

	public Integer getIsAirForce() {
		return isAirForce;
	}

	public void setIsAirForce(Integer isAirForce) {
		this.isAirForce = isAirForce;
	}

	public Integer getIsTA() {
		return isTA;
	}

	public void setIsTA(Integer isTA) {
		this.isTA = isTA;
	}

	public Integer getIsCivil() {
		return isCivil;
	}

	public void setIsCivil(Integer isCivil) {
		this.isCivil = isCivil;
	}

	public Integer getIsGovernment() {
		return isGovernment;
	}

	public void setIsGovernment(Integer isGovernment) {
		this.isGovernment = isGovernment;
	}

	public Integer getIsSemiGovernment() {
		return isSemiGovernment;
	}

	public void setIsSemiGovernment(Integer isSemiGovernment) {
		this.isSemiGovernment = isSemiGovernment;
	}

	public Integer getMemberOfNCC() {
		return memberOfNCC;
	}

	public void setMemberOfNCC(Integer memberOfNCC) {
		this.memberOfNCC = memberOfNCC;
	}

	public Integer getMemberOfOTU() {
		return memberOfOTU;
	}

	public void setMemberOfOTU(Integer memberOfOTU) {
		this.memberOfOTU = memberOfOTU;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(Integer isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public List<ProfessionalDetails> getProfessionalDetails() {
		return professionalDetails;
	}

	public void setProfessionalDetails(List<ProfessionalDetails> professionalDetails) {
		this.professionalDetails = professionalDetails;
	}

	public String getMotheTongue() {
		return motheTongue;
	}

	public void setMotheTongue(String motheTongue) {
		this.motheTongue = motheTongue;
	}

	public List<ForeignLanguages> getForeignLanguages() {
		return foreignLanguages;
	}

	public void setForeignLanguages(List<ForeignLanguages> foreignLanguages) {
		this.foreignLanguages = foreignLanguages;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getTehsil() {
		return tehsil;
	}

	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}

	public String getPostOffice() {
		return postOffice;
	}

	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddressState() {
		return AddressState;
	}

	public void setAddressState(String addressState) {
		AddressState = addressState;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	// public FamilyDetails getFamilyDetails() {
	// return familyDetails;
	// }
	//
	// public void setFamilyDetails(FamilyDetails familyDetails) {
	// this.familyDetails = familyDetails;
	// }
	//
	// public CommissioningDetails getCommissioningDetails() {
	// return commissioningDetails;
	// }
	//
	// public void setCommissioningDetails(CommissioningDetails
	// commissioningDetails) {
	// this.commissioningDetails = commissioningDetails;
	// }

	public String getAcademyNo() {
		return academyNo;
	}

	public void setAcademyNo(String academyNo) {
		this.academyNo = academyNo;
	}

	public Date getAccDate() {
		return accDate;
	}

	public void setAccDate(Date accDate) {
		this.accDate = accDate;
	}

	public String getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}

	public String getMiscellaneous() {
		return miscellaneous;
	}

	public void setMiscellaneous(String miscellaneous) {
		this.miscellaneous = miscellaneous;
	}

	public FamilyDetails getFamilyDetails() {
		return familyDetails;
	}

	public void setFamilyDetails(FamilyDetails familyDetails) {
		this.familyDetails = familyDetails;
	}

	public CommissioningDetails getCommissioningDetails() {
		return commissioningDetails;
	}

	public void setCommissioningDetails(CommissioningDetails commissioningDetails) {
		this.commissioningDetails = commissioningDetails;
	}

	public VaccinationDetails getVaccinationDetails() {
		return vaccinationDetails;
	}

	public void setVaccinationDetails(VaccinationDetails vaccinationDetails) {
		this.vaccinationDetails = vaccinationDetails;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(String spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	public String getMarriageDate() {
		return marriageDate;
	}

	public void setMarriageDate(String marriageDate) {
		this.marriageDate = marriageDate;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPassoutYear() {
		return passoutYear;
	}

	public void setPassoutYear(String passoutYear) {
		this.passoutYear = passoutYear;
	}

	public String getPassoutSession() {
		return passoutSession;
	}

	public void setPassoutSession(String passoutSession) {
		this.passoutSession = passoutSession;
	}

}
