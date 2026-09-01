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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
public class User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String username;

	private String firstName;

	private String lastName;

	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@Column(unique = true, length = 255)
	private String serviceId;

	private String department;

	@Column(name = "battalian_id")
	private Integer battalianId;// 2 may added

//	@ManyToOne
//	private Battalion battalian;

	// 24 may
	private Integer is_first_login;

	private Date last_login;

	private Date last_password_change;

	private String personalNumber;

	private String officerRank;

	private Date substantiveDate;

	private Date actingDate;

	private String regiment;

	private String typeOfCommission;

	private Date typeOfCommissionDate;

	private String typeOfCommissionAuthority;

	private Date dateOfSeniorityForSubstantivePromotion;

	private Date dateOfFirstCommission;

	@Column(name = "DOB")
	private Date dOB;

	private String placeOfBirth;

	@OneToOne(cascade = CascadeType.ALL)
	private Nationality nationality;

	@OneToOne(cascade = CascadeType.ALL)
	private ReligiousDenomination religiousDenomination;

	@OneToOne(cascade = CascadeType.ALL)
	private MotherTongue motherTongue;

	private String medicalCategory;

	private String previousOccupation;

	@Column(name = "CDAAccountNo")
	private String cDAAccountNo;

	private String identityCardNo;

	private String permanentHomeAddress;

	private String officersBankersName;

	private String officersBankersAddress;

	@Column(name = "DRGNominy")
	private Integer dRGNominy;

	@Column(name = "AGINominy")
	private Integer aGINominy;

	@Column(name = "DSOPNominy")
	private Integer dSOPNominy;

	private String willExecutedLocation;

	private String place;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersAcademicQualification> officersEducation;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersProfessionalQualification> officersProfession;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersExperienceInCivilTrades> officersCivilProfession;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersFormerServices> officersFormerServices;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersPassingOutTrainingEstablishment> officersPassingOutTrainingEstablishment;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersCourses> officersCourses;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersPromotionExamination> officersPromotionExamination;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersLanguages> officersLanguages;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersRegimentalDuties> officersRegimentalDuties;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersApartRegimental> officersApartRegimental;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersExtraRegimental> officersExtraRegimental;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersDecorations> officersDecorations;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersNextKin> officersNextKin;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersInsurancePolicies> officersInsurancePolicies;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersFamilyDetails> officersFamilyDetails;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersChildrenDetails> officersChildrenDetails;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OfficersAnnualLeave> officersAnnualLeave;

	private Integer status;

	private Integer isDeleted;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@ManyToOne(fetch = FetchType.EAGER) // 2may commented all
	@JoinColumn(name = "role_id", nullable = false)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	private Role roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getBattalianId() {
		return battalianId;
	}

	public void setBattalianId(Integer battalianId) {
		this.battalianId = battalianId;
	}

//	public Battalion getBattalian() {
//		return battalian;
//	}
//
//	public void setBattalian(Battalion battalian) {
//		this.battalian = battalian;
//	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
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

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIs_first_login() {
		return is_first_login;
	}

	public void setIs_first_login(Integer is_first_login) {
		this.is_first_login = is_first_login;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public Date getLast_password_change() {
		return last_password_change;
	}

	public void setLast_password_change(Date last_password_change) {
		this.last_password_change = last_password_change;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public String getOfficerRank() {
		return officerRank;
	}

	public List<OfficersAcademicQualification> getOfficersEducation() {
		return officersEducation;
	}

	public void setOfficersEducation(List<OfficersAcademicQualification> officersEducation) {
		this.officersEducation = officersEducation;
	}

	public void setOfficerRank(String officerRank) {
		this.officerRank = officerRank;
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

	public String getRegiment() {
		return regiment;
	}

	public void setRegiment(String regiment) {
		this.regiment = regiment;
	}

	public String getTypeOfCommission() {
		return typeOfCommission;
	}

	public void setTypeOfCommission(String typeOfCommission) {
		this.typeOfCommission = typeOfCommission;
	}

	public Date getTypeOfCommissionDate() {
		return typeOfCommissionDate;
	}

	public void setTypeOfCommissionDate(Date typeOfCommissionDate) {
		this.typeOfCommissionDate = typeOfCommissionDate;
	}

	public String getTypeOfCommissionAuthority() {
		return typeOfCommissionAuthority;
	}

	public void setTypeOfCommissionAuthority(String typeOfCommissionAuthority) {
		this.typeOfCommissionAuthority = typeOfCommissionAuthority;
	}

	public Date getDateOfSeniorityForSubstantivePromotion() {
		return dateOfSeniorityForSubstantivePromotion;
	}

	public void setDateOfSeniorityForSubstantivePromotion(Date dateOfSeniorityForSubstantivePromotion) {
		this.dateOfSeniorityForSubstantivePromotion = dateOfSeniorityForSubstantivePromotion;
	}

	public Date getDateOfFirstCommission() {
		return dateOfFirstCommission;
	}

	public void setDateOfFirstCommission(Date dateOfFirstCommission) {
		this.dateOfFirstCommission = dateOfFirstCommission;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public ReligiousDenomination getReligiousDenomination() {
		return religiousDenomination;
	}

	public void setReligiousDenomination(ReligiousDenomination religiousDenomination) {
		this.religiousDenomination = religiousDenomination;
	}

	public MotherTongue getMotherTongue() {
		return motherTongue;
	}

	public void setMotherTongue(MotherTongue motherTongue) {
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

	public String getIdentityCardNo() {
		return identityCardNo;
	}

	public void setIdentityCardNo(String identityCardNo) {
		this.identityCardNo = identityCardNo;
	}

	public String getPermanentHomeAddress() {
		return permanentHomeAddress;
	}

	public void setPermanentHomeAddress(String permanentHomeAddress) {
		this.permanentHomeAddress = permanentHomeAddress;
	}

	public String getWillExecutedLocation() {
		return willExecutedLocation;
	}

	public void setWillExecutedLocation(String willExecutedLocation) {
		this.willExecutedLocation = willExecutedLocation;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public List<OfficersProfessionalQualification> getOfficersProfession() {
		return officersProfession;
	}

	public void setOfficersProfession(List<OfficersProfessionalQualification> officersProfession) {
		this.officersProfession = officersProfession;
	}

	public List<OfficersExperienceInCivilTrades> getOfficersCivilProfession() {
		return officersCivilProfession;
	}

	public void setOfficersCivilProfession(List<OfficersExperienceInCivilTrades> officersCivilProfession) {
		this.officersCivilProfession = officersCivilProfession;
	}

	public List<OfficersFormerServices> getOfficersFormerServices() {
		return officersFormerServices;
	}

	public void setOfficersFormerServices(List<OfficersFormerServices> officersFormerServices) {
		this.officersFormerServices = officersFormerServices;
	}

	public List<OfficersPassingOutTrainingEstablishment> getOfficersPassingOutTrainingEstablishment() {
		return officersPassingOutTrainingEstablishment;
	}

	public void setOfficersPassingOutTrainingEstablishment(
			List<OfficersPassingOutTrainingEstablishment> officersPassingOutTrainingEstablishment) {
		this.officersPassingOutTrainingEstablishment = officersPassingOutTrainingEstablishment;
	}

	public List<OfficersCourses> getOfficersCourses() {
		return officersCourses;
	}

	public void setOfficersCourses(List<OfficersCourses> officersCourses) {
		this.officersCourses = officersCourses;
	}

	public List<OfficersPromotionExamination> getOfficersPromotionExamination() {
		return officersPromotionExamination;
	}

	public void setOfficersPromotionExamination(List<OfficersPromotionExamination> officersPromotionExamination) {
		this.officersPromotionExamination = officersPromotionExamination;
	}

	public List<OfficersLanguages> getOfficersLanguages() {
		return officersLanguages;
	}

	public void setOfficersLanguages(List<OfficersLanguages> officersLanguages) {
		this.officersLanguages = officersLanguages;
	}

	public List<OfficersRegimentalDuties> getOfficersRegimentalDuties() {
		return officersRegimentalDuties;
	}

	public void setOfficersRegimentalDuties(List<OfficersRegimentalDuties> officersRegimentalDuties) {
		this.officersRegimentalDuties = officersRegimentalDuties;
	}

	public List<OfficersApartRegimental> getOfficersApartRegimental() {
		return officersApartRegimental;
	}

	public void setOfficersApartRegimental(List<OfficersApartRegimental> officersApartRegimental) {
		this.officersApartRegimental = officersApartRegimental;
	}

	public List<OfficersExtraRegimental> getOfficersExtraRegimental() {
		return officersExtraRegimental;
	}

	public void setOfficersExtraRegimental(List<OfficersExtraRegimental> officersExtraRegimental) {
		this.officersExtraRegimental = officersExtraRegimental;
	}

	public List<OfficersDecorations> getOfficersDecorations() {
		return officersDecorations;
	}

	public void setOfficersDecorations(List<OfficersDecorations> officersDecorations) {
		this.officersDecorations = officersDecorations;
	}

	public List<OfficersNextKin> getOfficersNextKin() {
		return officersNextKin;
	}

	public void setOfficersNextKin(List<OfficersNextKin> officersNextKin) {
		this.officersNextKin = officersNextKin;
	}

	public List<OfficersInsurancePolicies> getOfficersInsurancePolicies() {
		return officersInsurancePolicies;
	}

	public void setOfficersInsurancePolicies(List<OfficersInsurancePolicies> officersInsurancePolicies) {
		this.officersInsurancePolicies = officersInsurancePolicies;
	}

	public List<OfficersFamilyDetails> getOfficersFamilyDetails() {
		return officersFamilyDetails;
	}

	public void setOfficersFamilyDetails(List<OfficersFamilyDetails> officersFamilyDetails) {
		this.officersFamilyDetails = officersFamilyDetails;
	}

	public List<OfficersChildrenDetails> getOfficersChildrenDetails() {
		return officersChildrenDetails;
	}

	public void setOfficersChildrenDetails(List<OfficersChildrenDetails> officersChildrenDetails) {
		this.officersChildrenDetails = officersChildrenDetails;
	}

	public List<OfficersAnnualLeave> getOfficersAnnualLeave() {
		return officersAnnualLeave;
	}

	public void setOfficersAnnualLeave(List<OfficersAnnualLeave> officersAnnualLeave) {
		this.officersAnnualLeave = officersAnnualLeave;
	}

	public Date getdOB() {
		return dOB;
	}

	public void setdOB(Date dOB) {
		this.dOB = dOB;
	}

	public String getcDAAccountNo() {
		return cDAAccountNo;
	}

	public void setcDAAccountNo(String cDAAccountNo) {
		this.cDAAccountNo = cDAAccountNo;
	}

	public String getOfficersBankersName() {
		return officersBankersName;
	}

	public void setOfficersBankersName(String officersBankersName) {
		this.officersBankersName = officersBankersName;
	}

	public String getOfficersBankersAddress() {
		return officersBankersAddress;
	}

	public void setOfficersBankersAddress(String officersBankersAddress) {
		this.officersBankersAddress = officersBankersAddress;
	}

	public Integer getdRGNominy() {
		return dRGNominy;
	}

	public void setdRGNominy(Integer dRGNominy) {
		this.dRGNominy = dRGNominy;
	}

	public Integer getaGINominy() {
		return aGINominy;
	}

	public void setaGINominy(Integer aGINominy) {
		this.aGINominy = aGINominy;
	}

	public Integer getdSOPNominy() {
		return dSOPNominy;
	}

	public void setdSOPNominy(Integer dSOPNominy) {
		this.dSOPNominy = dSOPNominy;
	}
}
