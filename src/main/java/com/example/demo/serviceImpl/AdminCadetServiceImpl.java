package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.controller.AdminWeekController;
import com.example.demo.model.AuthTable;
import com.example.demo.model.Battalion;
import com.example.demo.model.Cadet;
import com.example.demo.model.CadetEducation;
import com.example.demo.model.CommissioningDetails;
import com.example.demo.model.FamilyDetails;
import com.example.demo.model.ForeignLanguages;
import com.example.demo.model.GcsPunishments;
import com.example.demo.model.ProfessionalDetails;
import com.example.demo.model.Term;
import com.example.demo.model.VaccinationDetails;
import com.example.demo.payload.AdminCadetPayload;
import com.example.demo.payload.CadetFilterPayload;
import com.example.demo.payload.CadetPayLoad;
import com.example.demo.payload.EdCadetPayload;
import com.example.demo.repository.AdminBattalionRepo;
import com.example.demo.repository.AdminCadetEducationRepo;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.CommissioningDetailsRepo;
import com.example.demo.repository.FamilyDetailsRepo;
import com.example.demo.repository.ForeignLanguagesRepo;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.ProfessionalDetailsRepo;
import com.example.demo.repository.TermRepo;
import com.example.demo.repository.VaccinationDetailsRepo;
import com.example.demo.service.AdminCadetService;
import com.example.demo.service.GcsPunishmentsService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ConstantVar;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.google.common.base.Objects;

@Service
public class AdminCadetServiceImpl implements AdminCadetService {

	@Autowired
	AdminCadetRepo cadetRepo;

	@Autowired
	AdminBattalionRepo battalionRepo;

	@Autowired
	LoginRepository loginRepo;

	@Autowired
	AdminCadetEducationRepo cadetEduRepo;

	@Autowired
	private ForeignLanguagesRepo foreignLanguagesRepo;

	@Autowired
	private FamilyDetailsRepo familyDetailsRepo;

	@Autowired
	private ProfessionalDetailsRepo professionalDetailsRepo;

	@Autowired
	private CommissioningDetailsRepo commissioningDetailsRepo;

	@Autowired
	private VaccinationDetailsRepo vaccinationDetailsRepo;

	@Autowired
	TermRepo termRepo;

	@Autowired
	GcsPunishmentsService gcsPunishmentsService;
	
	private static Logger logger=LoggerFactory.getLogger(AdminCadetServiceImpl.class);


	@Override
	public Cadet createCadetDetail(Cadet cadet, List<MultipartFile> file, String url, String uploadDir) {
		
		logger.info("inside createCadetDetail service password=>"+cadet.getPassword());
		CopyOnWriteArrayList<CadetEducation> cadetEdu = new CopyOnWriteArrayList<>();
		Cadet cadteSave = new Cadet();
		for (int i = 0; i < file.size(); i++) {
			String doc = "";
			doc = FileUploader.uploadProfileImage(file.get(i), uploadDir);
			if (null != doc && i == 0 && file.get(0) != null) {
				cadet.setProfileImg(url + doc);
			}

			else if (null != doc && i == 1) {
				cadet.setAdharImg(url + doc);
			} else if (null != doc && i == 2) {
				cadet.setPanImg(url + doc);
			} else if (i > 2) {
				for (CadetEducation ce : cadet.getCadetEducation()) {
					System.out.println(cadet.getCadetEducation().size()

							+ ce.getExamination());
					if (i == 3 && doc != null) {

						ce.setCertImg(url + doc);
						cadetEdu.add(ce);

						i++;

					} else if (i == 4) {
						doc = FileUploader.uploadProfileImage(file.get(i), uploadDir);
						if (doc != "") {
							ce.setCertImg(url + doc);
						} else {
							ce.setCertImg(doc);
						}
						cadetEdu.add(ce);
						i++;

					} else if (i == 5) {
						doc = FileUploader.uploadProfileImage(file.get(i), uploadDir);
						if (doc != "") {
							ce.setCertImg(url + doc);
						} else {
							ce.setCertImg(doc);
						}
						cadetEdu.add(ce);
						i++;

					} else if (i == 6) {
						doc = FileUploader.uploadProfileImage(file.get(i), uploadDir);
						if (doc != "") {
							ce.setCertImg(url + doc);
						} else {
							ce.setCertImg(doc);
						}
						cadetEdu.add(ce);
						i++;
					}

				}

			}

		}
		// cadet.setServiceId("A-" + cadetRepo.findAllOrderByIdDesc() != null ?
		// cadetRepo.findAllOrderByIdDesc() : "1");
		cadet.setCadetEducation(cadetEdu);
		try {
			String pwd = new BCryptPasswordEncoder().encode(cadet.getPassword());
			cadet.setPassword(pwd);
			cadteSave = cadetRepo.save(cadet);

			Battalion battalion = battalionRepo.findByShortName(cadet.getBattalian());
			// Auth Table
			AuthTable alreadyAdded=loginRepo.findByUsername(cadteSave.getServiceId()); 
			
			if (cadteSave != null && pwd != null) {
				
				if(java.util.Objects.isNull(alreadyAdded)) {
				AuthTable authData = new AuthTable();
				authData.setEmail(cadet.getEmail().trim());
				authData.setUsername(cadet.getUsername().trim());
				authData.setHasRole("3"); //formultipleroles
				authData.setName(cadet.getName().trim());
				authData.setPassword(pwd);
				authData.setBattalionId(battalion.getId());
				authData = loginRepo.save(authData);
			}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return cadteSave;
	}

	// @Override
	// public Page<Cadet> getAllCadetList(Pageable paginationData) {
	//
	//// Page<Cadet> list = cadetRepo.findAllByStatusOrderByIdDesc(ConstantVar.ONE,
	// paginationData);
	// Page<Cadet> list = cadetRepo.findAllByOrderByIdDesc(paginationData);
	// return list;
	// }

	@Override
	public List<Cadet> getAllCadetList(String status) {

		// Page<Cadet> list = cadetRepo.findAllByStatusOrderByIdDesc(ConstantVar.ONE,
		// paginationData);
		List<Cadet> list = null;
		Integer[] deletedStatus = { 2 };
		if (status.equals("All")) {
			// list = cadetRepo.findAllByOrderByIdDesc()
			list = cadetRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			// return list;
		} else {
			list = cadetRepo.findByBattalianAndStatusNotInOrderByIdDesc(status, deletedStatus);
			// return list;
		}
		for (Cadet cad : list) {
			Optional<Term> term = termRepo.findById(cad.getTerm());
			cad.setTermName(term.get().getName());
		}
		return list;
	}

	@Override
	public Cadet getCadetById(Long id) {
		Optional<Cadet> result = cadetRepo.findById(id);
		Cadet cadet = null;
		if (result.isPresent()) {
			cadet = result.get();
			if(cadet.getStatus()==1) {
			Collections.sort(cadet.getCadetEducation(), (edu1, edu2) -> {
				if (edu1.getId() > edu2.getId()) {
					return 1;
				} else {
					return -1;
				}
			});
			}else {
				return null;
			}
		}
		return cadet;
	}

	@Override
	public Cadet updateCadetDetail(Cadet cadet, List<MultipartFile> file, String url, String uploadDir) {
		Cadet cad = null;

		CadetEducation ceduData = null;// 26 june 2021 -start

		CopyOnWriteArrayList<CadetEducation> cadetEdu = new CopyOnWriteArrayList<>();
		List<ProfessionalDetails> professionalList = new ArrayList<ProfessionalDetails>();
		List<ForeignLanguages> foreignLangList = new ArrayList<ForeignLanguages>();
		Optional<Cadet> c = cadetRepo.findById(cadet.getId());
		if (c.isPresent()) {
			cad = c.get();

			if (cadet.getServiceId() != null) {
				cad.setServiceId(cadet.getServiceId());
			}

			if (cadet.getUsername() != null) {
				cad.setUsername(cadet.getUsername());
			}
			if (cadet.getPassword() != null && !cadet.getPassword().trim().isEmpty()) {
				String pwd = new BCryptPasswordEncoder().encode(cadet.getPassword());
				cad.setPassword(pwd);
			//	cad.setPassword(cadet.getPassword());
			}
			if (cadet.getAcademyNo() != null) {
				cad.setAcademyNo(cadet.getAcademyNo());
			}
			if (cadet.getAccDate() != null) {
				cad.setAccDate(cadet.getAccDate());
			}
			if (cadet.getTermSession() != null) {
				cad.setTermSession(cadet.getTermSession());
			}
			if (cadet.getMiscellaneous() != null) {
				cad.setMiscellaneous(cadet.getMiscellaneous());
			}

			if (cadet.getSerialNo() != null) {
				cad.setSerialNo(cadet.getSerialNo());
			}

			if (cadet.getBattalian() != null) {
				cad.setBattalian(cadet.getBattalian());
			}

			if (cadet.getCompany() != null) {
				cad.setCompany(cadet.getCompany());
			}

			if (cadet.getYear() != null) {
				cad.setYear(cadet.getYear());
			}

			if (cadet.getCourse() != null) {
				cad.setCourse(cadet.getCourse());
			}

			if (cadet.getTerm() != null) {
				cad.setTerm(cadet.getTerm());
			}

			if (cadet.getCourseSerNo() != null) {
				cad.setCourseSerNo(cadet.getCourseSerNo());
			}

			if (cadet.getEntry() != null) {
				cad.setEntry(cadet.getEntry());
			}

			if (cadet.getName() != null) {
				cad.setName(cadet.getName());
			}

			if (cadet.getUpscRollNo() != null) {
				cad.setUpscRollNo(cadet.getUpscRollNo());
			}

			if (cadet.getSsbSerNo() != null) {
				cad.setSsbSerNo(cadet.getSsbSerNo());
			}
			if (cadet.getSsbBatchNo() != null) {
				cad.setSsbBatchNo(cadet.getSsbBatchNo());
			}
			if (cadet.getChestNo() != null) {
				cad.setChestNo(cadet.getChestNo());
			}

			if (cadet.getDob() != null) {
				cad.setDob(cadet.getDob());
			}

			if (cadet.getBirthPlace() != null) {
				cad.setBirthPlace(cadet.getBirthPlace());
			}

			if (cadet.getReligion() != null) {
				cad.setReligion(cadet.getReligion());
			}

			if (cadet.getCast() != null) {
				cad.setCast(cadet.getCast());
			}

			if (cadet.getBloodGroup() != null) {
				cad.setBloodGroup(cadet.getBloodGroup());
			}

			if (cadet.getNationality() != null) {
				cad.setNationality(cadet.getNationality());
			}

			if (cadet.getIdentificationMarks() != null) {
				cad.setIdentificationMarks(cadet.getIdentificationMarks());
			}

			// if (cadet.getAddress() != null) { cad.setAddress(cadet.getAddress()); }

			if (cadet.getBelongsTo() != null) {
				cad.setBelongsTo(cadet.getBelongsTo());
			}

			if (cadet.getContactNo() != null) {
				cad.setContactNo(cadet.getContactNo());
			}

			if (cadet.getAddressLine1() != null) {
				cad.setAddressLine1(cadet.getAddressLine1());
			}
			if (cadet.getAddressLine2() != null) {
				cad.setAddressLine2(cadet.getAddressLine2());
			}
			if (cadet.getVillage() != null) {
				cad.setVillage(cadet.getVillage());
			}
			if (cadet.getTehsil() != null) {
				cad.setTehsil(cadet.getTehsil());
			}
			if (cadet.getPostOffice() != null) {
				cad.setPostOffice(cadet.getPostOffice());
			}
			if (cadet.getCity() != null) {
				cad.setCity(cadet.getCity());
			}
			if (cadet.getDistrict() != null) {
				cad.setDistrict(cadet.getDistrict());
			}
			if (cadet.getState() != null) {
				cad.setState(cadet.getState());
			}
			if (cadet.getPincode() != null) {
				cad.setPincode(cadet.getPincode());
			}
			if (cadet.getAddressState() != null) {
				cad.setAddressState(cadet.getAddressState());
			}
			if (cadet.getEmail() != null) {
				cad.setEmail(cadet.getEmail());
			}

			/*
			 * if (cadet.getFatherName() != null) {
			 * cad.setFatherName(cadet.getFatherName()); }
			 *
			 * if (cadet.getFatherProfession() != null) {
			 * cad.setFatherProfession(cadet.getFatherProfession()); }
			 *
			 * if (cadet.getParentMonthlyIncome() != null) {
			 * cad.setParentMonthlyIncome(cadet.getParentMonthlyIncome()); }
			 *
			 * if (cadet.getProfessionPriorIMA() != null) {
			 * cad.setProfessionPriorIMA(cadet.getProfessionPriorIMA()); }
			 */

			if (cadet.getFamilyDetails() != null) {
				if (cadet.getFamilyDetails().getId() != null && cadet.getFamilyDetails().getId() != 0) {
					Optional<FamilyDetails> familyDtls = familyDetailsRepo.findById(cadet.getFamilyDetails().getId());
					if (cadet.getFamilyDetails().getFatherName() != null) {
						familyDtls.get().setFatherName(cadet.getFamilyDetails().getFatherName());
					}
					if (cadet.getFamilyDetails().getMonthlyIncome() != null) {
						familyDtls.get().setMonthlyIncome(cadet.getFamilyDetails().getMonthlyIncome());
					}
					if (cadet.getFamilyDetails().getAddress_of_next_of_kin_showing_rel() != null) {
						familyDtls.get().setAddress_of_next_of_kin_showing_rel(
								cadet.getFamilyDetails().getAddress_of_next_of_kin_showing_rel());
					}
					if (cadet.getFamilyDetails().getName_of_next_of_kin_showing_rel() != null) {
						familyDtls.get().setName_of_next_of_kin_showing_rel(
								cadet.getFamilyDetails().getName_of_next_of_kin_showing_rel());
					}
					if (cadet.getFamilyDetails().getRelation() != null) {
						familyDtls.get().setRelation(cadet.getFamilyDetails().getRelation());
					}
					if (cadet.getFamilyDetails().getFather_profession() != null) {
						familyDtls.get().setFather_profession(cadet.getFamilyDetails().getFather_profession());
					}
					if (cadet.getFamilyDetails().getCurrentStatus() != null) {
						familyDtls.get().setCurrentStatus(cadet.getFamilyDetails().getCurrentStatus());
					}
					if (cadet.getFamilyDetails().getFurnishDetail() != null) {
						familyDtls.get().setFurnishDetail(cadet.getFamilyDetails().getFurnishDetail());
					}
					if (cadet.getFamilyDetails().getRankType() != null) {
						familyDtls.get().setRankType(cadet.getFamilyDetails().getRankType());
					}
					if (cadet.getFamilyDetails().getArmedForce() != null) {
						familyDtls.get().setArmedForce(cadet.getFamilyDetails().getArmedForce());
					}
					if (cadet.getFamilyDetails().getUnit() != null) {
						familyDtls.get().setUnit(cadet.getFamilyDetails().getUnit());
					}
					if (cadet.getFamilyDetails().getChoiceofArms() != null) {
						familyDtls.get().setChoiceofArms(cadet.getFamilyDetails().getChoiceofArms());
					}
					if (cadet.getFamilyDetails().getRankName() != null) {
						familyDtls.get().setRankName(cadet.getFamilyDetails().getRankName());
					}
					// familyDtls.get().setUpdatedAt(new
					// SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
					familyDtls.get().setUpdatedAt(new Date());
					// familyList.add(familyDtls.get());
					cad.setFamilyDetails(familyDtls.get());
				}
			}

			if (cadet.getProfessionalDetails() != null && cadet.getProfessionalDetails().size() != 0
					&& !cadet.getProfessionalDetails().isEmpty()) {
				Optional<ProfessionalDetails> proData = null;
				for (ProfessionalDetails proDtls : cadet.getProfessionalDetails()) {
					if (proDtls.getId() != null && proDtls.getId() != 0) {
						proData = professionalDetailsRepo.findById(proDtls.getId());
						if (proDtls.getProfession() != null) {
							proData.get().setProfession(proData.get().getProfession());
						}
						if (proDtls.getCompanyName() != null) {
							proData.get().setCompanyName(proData.get().getCompanyName());
						}
						if (proDtls.getDateofResignation() != null) {
							proData.get().setDateofResignation(proData.get().getDateofResignation());
						}
						if (proDtls.getDuration() != null) {
							proData.get().setDuration(proData.get().getDuration());
						}
						proData.get().setUpdatedAt(new Date());
						professionalList.add(proData.get());
					} else {
						professionalList.add(proDtls);
					}
					// professionalList.add(proData.get());
				}
			}

			if (cadet.getGovtEmp() != null) {
				cad.setGovtEmp(cadet.getGovtEmp());
			}

			if (cadet.getIsArmy() != null) {
				cad.setIsArmy(cadet.getIsArmy());
			}

			if (cadet.getIsNavy() != null) {
				cad.setIsNavy(cadet.getIsNavy());
			}

			if (cadet.getIsAirForce() != null) {
				cad.setIsAirForce(cadet.getIsAirForce());
			}

			if (cadet.getIsTA() != null) {
				cad.setIsTA(cadet.getIsTA());
			}

			if (cadet.getIsCivil() != null) {
				cad.setIsCivil(cadet.getIsCivil());
			}

			if (cadet.getIsGovernment() != null) {
				cad.setIsGovernment(cadet.getIsGovernment());
			}

			if (cadet.getIsSemiGovernment() != null) {
				cad.setIsSemiGovernment(cadet.getIsSemiGovernment());
			}

			if (cadet.getMaritalStatus() != null) {
				cad.setMaritalStatus(cadet.getMaritalStatus());
			}

			if (cadet.getAdharNo() != null) {
				cad.setAdharNo(cadet.getAdharNo());
			}

			if (cadet.getBelongsTo() != null) {
				cad.setBelongsTo(cadet.getBelongsTo());
			}

			if (cadet.getMemberOfNCC_OTU() != null) {
				cad.setMemberOfNCC_OTU(cadet.getMemberOfNCC_OTU());
			}

			if (cadet.getMemberOfNCC() != null) {
				cad.setMemberOfNCC(cadet.getMemberOfNCC());
			}

			if (cadet.getMemberOfOTU() != null) {
				cad.setMemberOfOTU(cadet.getMemberOfOTU());
			}

			if (cadet.getCadetRank() != null) {
				cad.setCadetRank(cadet.getCadetRank());
			}

			if (cadet.getCompany() != null) {
				cad.setCompany(cadet.getCompany());
			}

			if (cadet.getNCCDate() != null) {
				cad.setNCCDate(cadet.getNCCDate());
			}

			if (cadet.getCertObtained() != null) {
				cad.setCertObtained(cadet.getCertObtained());
			}

			if (cadet.getDivision() != null) {
				cad.setDivision(cadet.getDivision());
			}

			if (cadet.getTrainingPeriod() != null) {
				cad.setTrainingPeriod(cadet.getTrainingPeriod());
			}

			/*
			 * if (cadet.getAddressOfNextKinRelation() != null) {
			 * cad.setAddressOfNextKinRelation(cadet.getAddressOfNextKinRelation()); }
			 *
			 * if (cadet.getNameOfNextKinRelation() != null) {
			 * cad.setNameOfNextKinRelation(cadet.getNameOfNextKinRelation()); }
			 */
			if (cadet.getSports() != null) {
				cad.setSports(cadet.getSports());
			}

			if (cadet.getSportsLevel() != null) {
				cad.setSportsLevel(cadet.getSportsLevel());
			}

			if (cadet.getHobies() != null) {
				cad.setHobies(cadet.getHobies());
			}

			if (cadet.getEverTrainedWithIMAOTANDA() != null) {
				cad.setEverTrainedWithIMAOTANDA(cadet.getEverTrainedWithIMAOTANDA());
			}

			if (cadet.getIsTrainedWithIMA() != null) {
				cad.setIsTrainedWithIMA(cadet.getIsTrainedWithIMA());
			}

			if (cadet.getIsTrainedWithNDA() != null) {
				cad.setIsTrainedWithNDA(cadet.getIsTrainedWithNDA());
			}

			if (cadet.getIsTrainedWithOTA() != null) {
				cad.setIsTrainedWithOTA(cadet.getIsTrainedWithOTA());
			}

			if (cadet.getTrainingAcademyNo() != null) {
				cad.setTrainingAcademyNo(cadet.getTrainingAcademyNo());
			}

			if (cadet.getTrainingCourseSerNo() != null) {
				cad.setTrainingCourseSerNo(cadet.getTrainingCourseSerNo());
			}

			if (cadet.getTrainingJoiningDate() != null) {
				cad.setTrainingJoiningDate(cadet.getTrainingJoiningDate());
			}

			if (cadet.getTrainingLeavingDate() != null) {
				cad.setTrainingLeavingDate(cadet.getTrainingLeavingDate());
			}

			if (cadet.getReasionOfLeaving() != null) {
				cad.setReasionOfLeaving(cadet.getReasionOfLeaving());
			}

			if (cadet.getCompany() != null) {
				cad.setCompany(cadet.getCompany());
			}

			if (cadet.getIMA_JoiningDate() != null) {
				cad.setIMA_JoiningDate(cadet.getIMA_JoiningDate());
			}

			if (cadet.getStatus() != null) {
				cad.setStatus(cadet.getStatus());
			}

			if (cadet.getMotheTongue() != null) {
				cad.setMotheTongue(cadet.getMotheTongue());
			}

			if (cadet.getForeignLanguages() != null && cadet.getForeignLanguages().size() != 0
					&& !cadet.getForeignLanguages().isEmpty()) {
				Optional<ForeignLanguages> flData = null;
				for (ForeignLanguages fl : cadet.getForeignLanguages()) {
					if (fl.getId() != null && fl.getId() != 0) {
						flData = foreignLanguagesRepo.findById(fl.getId());
						if (fl.getLanguage() != null) {
							flData.get().setLanguage(fl.getLanguage());
						}
						if (fl.getQualification() != null) {
							flData.get().setQualification(fl.getQualification());
						}
						if (fl.getUniversity() != null) {
							flData.get().setUniversity(fl.getUniversity());
						}
						if (fl.getProRead() != null) {
							flData.get().setProRead(fl.getProRead());
						}
						if (fl.getProSpeak() != null) {
							flData.get().setProSpeak(fl.getProSpeak());
						}
						if (fl.getProWrite() != null) {
							flData.get().setProWrite(fl.getProWrite());
						}
						if (fl.getStatus() != null) {
							flData.get().setStatus(fl.getStatus());
						}
						flData.get().setUpdatedAt(new Date());
						foreignLangList.add(flData.get());
					} else {
						foreignLangList.add(fl);
					}
				}
			}

			if (cadet.getCommissioningDetails() != null) {
				Optional<CommissioningDetails> commDtls = null;
				if (cadet.getCommissioningDetails().getId() != null && cadet.getCommissioningDetails().getId() != 0) {
					commDtls = commissioningDetailsRepo.findById(cadet.getCommissioningDetails().getId());
					if (cadet.getCommissioningDetails().getIC_Number() != null) {
						commDtls.get().setIC_Number(cadet.getCommissioningDetails().getIC_Number());
					}
					if (cadet.getCommissioningDetails().getChoice_of_Arms() != null) {
						commDtls.get().setChoice_of_Arms(cadet.getCommissioningDetails().getChoice_of_Arms());
					}
					if (cadet.getCommissioningDetails().getUnit_Posted_To() != null) {
						commDtls.get().setUnit_Posted_To(cadet.getCommissioningDetails().getUnit_Posted_To());
					}
					if (cadet.getCommissioningDetails().getDate_of_Commissioning() != null) {
						commDtls.get()
								.setDate_of_Commissioning(cadet.getCommissioningDetails().getDate_of_Commissioning());
					}
					commDtls.get().setUpdatedAt(new Date());
				}
				// CommissioningDtlsList.add(commDtls.get());
				cad.setCommissioningDetails(commDtls.get());

			}

			if (cadet.getVaccinationDetails() != null) {
				Optional<VaccinationDetails> vaccDtls = null;
				if (cadet.getVaccinationDetails().getId() != null && cadet.getVaccinationDetails().getId() != 0) {
					vaccDtls = vaccinationDetailsRepo.findById(cadet.getVaccinationDetails().getId());
					if (cadet.getVaccinationDetails().getFistDoseReferenceId() != null) {
						vaccDtls.get().setFistDoseReferenceId(cadet.getVaccinationDetails().getFistDoseReferenceId());
					}
					if (cadet.getVaccinationDetails().getDateOfFistDose() != null) {
						vaccDtls.get().setDateOfFistDose(cadet.getVaccinationDetails().getDateOfFistDose());
					}
					if (cadet.getVaccinationDetails().getSecDoseReferenceId() != null) {
						vaccDtls.get().setSecDoseReferenceId(cadet.getVaccinationDetails().getSecDoseReferenceId());
					}
					if (cadet.getVaccinationDetails().getDateOfSecDose() != null) {
						vaccDtls.get().setDateOfSecDose(cadet.getVaccinationDetails().getDateOfSecDose());
					}
					if (cadet.getVaccinationDetails().getNameOfVaccine() != null) {
						vaccDtls.get().setNameOfVaccine(cadet.getVaccinationDetails().getNameOfVaccine());
					}
					vaccDtls.get().setUpdatedAt(new Date());
				}
				cad.setVaccinationDetails(vaccDtls.get());
			}

			// -----------------------------------------------------------

			if (null != file && !file.isEmpty()) {
				for (int i = 0; i < file.size(); i++) {
					String doc = "";
					if (file.get(i) != null) {
						doc = FileUploader.uploadProfileImage(file.get(i), uploadDir);
					}

					if (doc != "" && i == 0) {
						cad.setProfileImg(url + doc); // 9/june/2021

					}

					else if (doc != "" && i == 1) {
						cad.setAdharImg(url + doc);
					} else if (doc != "" && i == 2) {
						cad.setPanImg(url + doc);
					} else if (i > 2 && cad.getCadetEducation() != null && cad.getCadetEducation().size() != 0
							&& !cad.getCadetEducation().isEmpty()) { // 9/june/2021
						List<CadetEducation> educationList = cad.getCadetEducation();
						Collections.sort(educationList, (edu1, edu2) -> {
							if (edu1.getId() > edu2.getId()) {
								return 1;
							} else {
								return -1;
							}
						});
						if (educationList.size() > 0 && i == 3) {
							if (file.get(i) != null && doc != "") {
								CadetEducation ce1 = educationList.get(0);
								ce1.setCertImg(url + doc);
								cadetEdu.add(ce1);
							} else {
								cadetEdu.add(educationList.get(0));
							}
						}
						if (educationList.size() > 1 && i == 4) {
							if (file.get(i) != null && i == 4 && doc != "") {
								CadetEducation ce1 = educationList.get(1);
								ce1.setCertImg(url + doc);
								cadetEdu.add(ce1);
							} else {
								cadetEdu.add(educationList.get(1));
							}
						}
						if (educationList.size() > 2 && i == 5) {
							if (file.get(i) != null && i == 5 && doc != "") {
								CadetEducation ce1 = educationList.get(2);
								ce1.setCertImg(url + doc);
								cadetEdu.add(ce1);
							} else {
								cadetEdu.add(educationList.get(2));
							}
						}
						if (educationList.size() > 3 && i == 6) {
							if (file.get(i) != null && i == 6 && doc != "") {
								CadetEducation ce1 = educationList.get(3);
								ce1.setCertImg(url + doc);
								cadetEdu.add(ce1);
							} else {
								cadetEdu.add(educationList.get(3));
							}
						}

//						for (CadetEducation ce : educationList) {
//							if (file.get(i) != null && i == 3) {
//								doc = FileUploader.uploadProfileImage(file.get(i), uploadDir);
//								ce.setCertImg(url + doc);
//								cadetEdu.add(ce);
//
//								i++;
//							} else if (file.get(i) != null && i == 4) {
//
//								doc = FileUploader.uploadProfileImage(file.get(i), uploadDir);
//								ce.setCertImg(url + doc);
//								cadetEdu.add(ce);
//								i++;
//
//							} else if (file.get(i) != null && i == 5) {
//								doc = FileUploader.uploadProfileImage(file.get(i), uploadDir);
//								ce.setCertImg(url + doc);
//								cadetEdu.add(ce);
//								i++;
//
//							} else if (file.get(i) != null && i == 6) {
//								doc = FileUploader.uploadProfileImage(file.get(i), uploadDir);
//								ce.setCertImg(url + doc);
//								cadetEdu.add(ce);
//								i++;
//							}
						// index++;
//////////////////////////////////////////////////////////////////////////////////////////
//							if (i == 3 && doc != "") {
//
//								ce.setCertImg(url + doc);
//								cadetEdu.add(ce);
//
//								i++;
//
//							} else if (i == 4) {
//
//								// doc = FileUploader.uploadProfileImage(file.get(i), uploadDir);
//								if (doc != "") {
//									ce.setCertImg(url + doc);
//								}
////								else {
////									ce.setCertImg(doc);
////								}
//								cadetEdu.add(ce);
//								i++;
//
//							} else if (i == 5) {
//								// doc = FileUploader.uploadProfileImage(file.get(i), uploadDir);
//								if (doc != "") {
//									ce.setCertImg(url + doc);
//								}
////								else {
////									ce.setCertImg(doc);
////								}
//								cadetEdu.add(ce);
//								i++;
//
//							} else if (i == 6) {
//								// doc = FileUploader.uploadProfileImage(file.get(i), uploadDir);
//								if (doc != "") {
//									ce.setCertImg(url + doc);
//								}
////								else {
////									ce.setCertImg(doc);
////								}
//								cadetEdu.add(ce);
//								i++;
//							}
//							// index++;

//						}

					}

				}
			}
			// 26 june 2021 -start

			else {

				if (cadet.getCadetEducation() != null && cadet.getCadetEducation().size() != 0
						&& !cadet.getCadetEducation().isEmpty()) {
					for (CadetEducation ce : cadet.getCadetEducation()) {
						if (ce.getId() != null && ce.getId() != 0) {
							Optional<CadetEducation> cedu = cadetEduRepo.findById(ce.getId());
							if (cedu.isPresent()) {
								ceduData = cedu.get();
								ceduData.setExamination(ce.getExamination() != null ? ce.getExamination() : "");
								ceduData.setYear(ce.getYear() != null ? ce.getExamination() : "");
								// add all setter in th same way
								cadetEdu.add(ceduData);
							} else {
								cadetEdu.add(ce);
							}

						}

					}
				}

			} // 26 june 2021 -end
		}
		// cad.setCadetEducation(cadetEdu);

		// 30-June-2021

		//

		if (!cadetEdu.isEmpty() || cadetEdu.size() != 0) {
			cad.setCadetEducation(cadetEdu);
		}

		if (!professionalList.isEmpty() || professionalList.size() != 0) {
			cad.setProfessionalDetails(professionalList);
		}
		if (!foreignLangList.isEmpty() || foreignLangList.size() != 0) {
			cad.setForeignLanguages(foreignLangList);
		}

		try {
			cad = cadetRepo.save(cad);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return cad;
	}

	@Override
	public Cadet checkServiceId(String serviceId) {
		Integer[] deletedStatus = { 2 };
		Cadet cad = cadetRepo.findByServiceIdAndStatusNotIn(serviceId, deletedStatus);
		return cad;
	}

	@Override
	public Cadet getDataByUsernameAndBattalian(String username, Integer battalionId, ServletRequest request) {
		Optional<Battalion> btln = battalionRepo.findById(battalionId);
		Cadet list = cadetRepo.findByUsernameAndStatusAndBattalian(username, ConstantVar.ONE,
				btln.get().getShortName());
		if (list == null) {
			FileWritting.createLog((HttpServletRequest) request,
					username + ",cadet login," + "failed," + ConstantMessage.USER_NOT_EXIST + "," + new Date());
		}
		Optional<Term> term = termRepo.findById(list.getTerm());
		list.setTermName(term.get().getName());
		return list;
	}

	@Override
	public Cadet getCadetByServiceId(String serviceId) {
		Cadet cad = cadetRepo.findByServiceId(serviceId);
		if (cad != null && cad.getStatus()==1) {
			Optional<Term> term = termRepo.findById(cad.getTerm());
			cad.setTermName(term.get().getName());
			return cad;
		} else {
			return null;
		}
	}

	@Override
	public Cadet getCadetByServiceIdAndTermId(String serviceId, Long termId) {
		Cadet cad = cadetRepo.findByServiceIdAndTerm(serviceId, termId);
		if (cad != null && cad.getStatus()==1) {
			Optional<Term> term = termRepo.findById(cad.getTerm());
			cad.setTermName(term.get().getName());
			return cad;
		} else {
			return null;
		}

	}

	@Override
	public Cadet updateCadetByServiceId(CadetPayLoad cadetPayload) {
		Cadet cad = null;
		if (cadetPayload != null && cadetPayload.getId() != null && cadetPayload.getId() != 0) {
			Optional<Cadet> c = cadetRepo.findById(cadetPayload.getId());
			if (c.isPresent()) {
				cad = c.get();
				if (cadetPayload.getAchievements() != null) {
					cad.setAchievements(cadetPayload.getAchievements());
				}
				if (cadetPayload.getAppt() != null) {
					cad.setAppt(cadetPayload.getAppt());
				}
				if (cadetPayload.getArmAndService() != null) {
					cad.setArmAndService(cadetPayload.getArmAndService());
				}
				if (cadetPayload.getBankAccountNo() != null) {
					cad.setBankAccountNo(cadetPayload.getBankAccountNo());
				}
				if (cadetPayload.getDetailsOfNOk() != null) {
					cad.setDetailsOfNOk(cadetPayload.getDetailsOfNOk());
				}
				if (cadetPayload.getfGPA() != null) {
					cad.setfGPA(cadetPayload.getfGPA());
				}
				if (cadetPayload.getMedalAward() != null) {
					cad.setMedalAward(cadetPayload.getMedalAward());
				}
				if (cadetPayload.getMerit() != null) {
					cad.setMerit(cadetPayload.getMerit());
				}
				if (cadetPayload.getNoOfChild() != null) {
					cad.setNoOfChild(cadetPayload.getNoOfChild());
				}
				if (cadetPayload.getPay() != null) {
					cad.setPay(cadetPayload.getPay());
				}
				if (cadetPayload.getPostAndOrg() != null) {
					cad.setPostAndOrg(cadetPayload.getPostAndOrg());
				}
				if (cadetPayload.getSpouseName() != null) {
					cad.setSpouseName(cadetPayload.getSpouseName());
				}
				if (cadetPayload.getSsbPlan() != null) {
					cad.setSsbPlan(cadetPayload.getSsbPlan());
				}
				if (cadetPayload.getVisaExpiredDate() != null) {
					cad.setVisaExpiredDate(cadetPayload.getVisaExpiredDate());
				}
				if (cadetPayload.getSpouseDetails() != null) {
					cad.setSpouseDetails(cadetPayload.getSpouseDetails());
				}
				if (cadetPayload.getMarriageDate() != null) {
					cad.setMarriageDate(cadetPayload.getMarriageDate());
				}
				if (cadetPayload.getOrderNo() != null) {
					cad.setOrderNo(cadetPayload.getOrderNo());
				}

				cad = cadetRepo.save(cad);
				return cad;

			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	@Override
	public EdCadetPayload getCadetsByTermIdAndBattaionAndCompany(Long termId, String battalion, String company,
			Pageable pageable) {

		List<CadetFilterPayload> cadetFilterList = new ArrayList<CadetFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		EdCadetPayload edCadetPayload = new EdCadetPayload();

		if (termId != null) {
			if (battalion != null) {
				if (company != null) {
					cadetList = cadetRepo.findAllByTermAndBattalianAndCompanyAndStatus(termId, battalion, company, 1,
							pageable);
					totalRecords = cadetRepo.findAllByTermAndBattalianAndCompanyAndStatus(termId, battalion, company, 1)
							.size();
				} else {
					cadetList = cadetRepo.findAllByTermAndBattalianAndStatus(termId, battalion, 1, pageable);
					totalRecords = cadetRepo.findAllByTermAndBattalianAndStatus(termId, battalion, 1).size();
				}
			} else {
				cadetList = cadetRepo.findAllByTermAndStatus(termId, 1, pageable);
				totalRecords = cadetRepo.findAllByTermAndStatus(termId, 1).size();
			}
		} else {
			if (battalion != null) {
				if (company != null) {
					cadetList = cadetRepo.findAllByBattalianAndCompanyAndStatus(battalion, company, 1, pageable);
					totalRecords = cadetRepo.findAllByBattalianAndCompanyAndStatus(battalion, company, 1).size();
				} else {
					cadetList = cadetRepo.findAllByBattalianAndStatus(battalion, 1, pageable);
					totalRecords = cadetRepo.findAllByBattalianAndStatus(battalion, 1).size();
				}
			} else {
				cadetList = cadetRepo.findAllByStatus(1, pageable);
				totalRecords = cadetRepo.findAllByStatus(1).size();
			}
		}

		if (cadetList != null && cadetList.size() != 0) {
			for (Cadet cad : cadetList) {
				CadetFilterPayload cadFilterPayload = new CadetFilterPayload();
				cadFilterPayload.setId(cad.getId());
				cadFilterPayload.setTermId(cad.getTerm());
				cadFilterPayload.setName(cad.getName());
				cadFilterPayload.setBattalian(cad.getBattalian());
				cadFilterPayload.setCompany(cad.getCompany());
				cadFilterPayload.setRank(cad.getCadetRank());
				cadFilterPayload.setServiceId(cad.getServiceId());
				cadFilterPayload.setCourse(cad.getCourse());
				cadFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				cadFilterPayload.setNationality(cad.getNationality());
				cadFilterPayload.setEntry(cad.getEntry());
				Term term = termRepo.findById(cad.getTerm()).get();
				cadFilterPayload.setTermName(term.getName());
				// get GC punishments points if available
				List<GcsPunishments> punishmentsList = gcsPunishmentsService.getGcsPunishmentsList(cad.getServiceId(),
						null, 1);
				if (punishmentsList.size() > 0) {
					Integer punishmentsPoints = 0;
					for (GcsPunishments gcsPunishments : punishmentsList) {
						punishmentsPoints += gcsPunishments.getPoints() == null ? 0 : gcsPunishments.getPoints();
					}
					cadFilterPayload.setPoints(punishmentsPoints);
				}
				cadetFilterList.add(cadFilterPayload);

			}
			edCadetPayload.setTotalRecords(totalRecords);
			edCadetPayload.setCadetFilterPayload(cadetFilterList);
			return edCadetPayload;
		} else {
			return null;
		}

	}

	@Override
	public EdCadetPayload getCadetsBySearch(String serviceId, Pageable pageable) {
		List<CadetFilterPayload> cadetFilterList = new ArrayList<CadetFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		EdCadetPayload edCadetPayload = new EdCadetPayload();

		if (serviceId != null && !serviceId.trim().equals("")) {
			cadetList = cadetRepo.findByStatusAndServiceIdLike(1, "%" + serviceId + "%", pageable);
			totalRecords = cadetRepo.findByStatusAndServiceIdLike(1, "%" + serviceId + "%").size();
		} else {
			cadetList = cadetRepo.findAllByStatus(1, pageable);
			// cadetList = pageCadet.toList();
			totalRecords = cadetRepo.findAllByStatus(1).size();
		}

		if (cadetList != null && cadetList.size() != 0) {
			for (Cadet cad : cadetList) {
				CadetFilterPayload cadFilterPayload = new CadetFilterPayload();
				cadFilterPayload.setId(cad.getId());
				cadFilterPayload.setTermId(cad.getTerm());
				cadFilterPayload.setName(cad.getName());
				cadFilterPayload.setBattalian(cad.getBattalian());
				cadFilterPayload.setCompany(cad.getCompany());
				cadFilterPayload.setRank(cad.getCadetRank());
				cadFilterPayload.setServiceId(cad.getServiceId());
				cadFilterPayload.setCourse(cad.getCourse());
				cadFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				cadFilterPayload.setNationality(cad.getNationality());
				cadFilterPayload.setEntry(cad.getEntry());
				Term term = termRepo.findById(cad.getTerm()).get();
				cadFilterPayload.setTermName(term.getName());
				// get GC punishments points if available
				List<GcsPunishments> punishmentsList = gcsPunishmentsService.getGcsPunishmentsList(cad.getServiceId(),
						null, 1);
				if (punishmentsList.size() > 0) {
					Integer punishmentsPoints = 0;
					for (GcsPunishments gcsPunishments : punishmentsList) {
						punishmentsPoints += gcsPunishments.getPoints() == null ? 0 : gcsPunishments.getPoints();
						;
					}
					cadFilterPayload.setPoints(punishmentsPoints);
				}
				cadetFilterList.add(cadFilterPayload);

			}
			edCadetPayload.setTotalRecords(totalRecords);
			edCadetPayload.setCadetFilterPayload(cadetFilterList);
			return edCadetPayload;
		} else {
			return null;
		}
	}

	@Override
	public String updateCadetTermById(List<Cadet> resultList) {
		String result = "failed";
		if (resultList.size() > 0) {
			for (Cadet cadet : resultList) {
				if (cadet != null && cadet.getId() != null && cadet.getId() != 0) {
					Optional<Cadet> cadRes = cadetRepo.findById(cadet.getId());
					Cadet cad = null;
					if (cadRes.isPresent()) {
						cad = cadRes.get();
						if (cadet.getTerm() != null) {
							if (cadet.getTerm() == 3 && cadet.getStatus() != null && cadet.getStatus() == 3) {
								// LocalDate currentdate = LocalDate.now();
								// System.out.println("current year =>" + currentdate.getYear());
								// cadet.setPassoutYear(currentdate.getYear() + "");
								cad.setPassoutYear(cadet.getPassoutYear());
								cad.setPassoutSession(cadet.getPassoutSession());
								cad.setStatus(cadet.getStatus());
							}
							cad.setTerm(cadet.getTerm());
							cadetRepo.save(cad);
							result = "success";
						}
					}
				}
			}
		}
		return result;

	}

	@Override
	public EdCadetPayload getCadetsByTermIdAndBattaionAndCompanyWithoutPagination(Long termId, String battalion,
			String company) {
		List<CadetFilterPayload> cadetFilterList = new ArrayList<CadetFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		EdCadetPayload edCadetPayload = new EdCadetPayload();

		if (termId != null) {
			if (battalion != null) {
				if (company != null) {
					cadetList = cadetRepo.findAllByTermAndBattalianAndCompanyAndStatus(termId, battalion, company, 1);
					totalRecords = cadetList.size();
				} else {
					cadetList = cadetRepo.findAllByTermAndBattalianAndStatus(termId, battalion, 1);
					totalRecords = cadetList.size();
				}
			} else {
				cadetList = cadetRepo.findAllByTermAndStatus(termId, 1);
				totalRecords = cadetList.size();
			}
		} else {
			if (battalion != null) {
				if (company != null) {
					cadetList = cadetRepo.findAllByBattalianAndCompanyAndStatus(battalion, company, 1);
					totalRecords = cadetList.size();
				} else {
					cadetList = cadetRepo.findAllByBattalianAndStatus(battalion, 1);
					totalRecords = cadetList.size();
				}
			} else {
				cadetList = cadetRepo.findAllByStatus(1);
				totalRecords = cadetList.size();
			}
		}

		if (cadetList != null && cadetList.size() != 0) {
			for (Cadet cad : cadetList) {
				CadetFilterPayload cadFilterPayload = new CadetFilterPayload();
				cadFilterPayload.setId(cad.getId());
				cadFilterPayload.setTermId(cad.getTerm());
				cadFilterPayload.setName(cad.getName());
				cadFilterPayload.setBattalian(cad.getBattalian());
				cadFilterPayload.setCompany(cad.getCompany());
				cadFilterPayload.setRank(cad.getCadetRank());
				cadFilterPayload.setServiceId(cad.getServiceId());
				cadFilterPayload.setCourse(cad.getCourse());
				cadFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				cadFilterPayload.setNationality(cad.getNationality());
				cadFilterPayload.setEntry(cad.getEntry());
				Term term = termRepo.findById(cad.getTerm()).get();
				cadFilterPayload.setTermName(term.getName());
				// get GC punishments points if available
				List<GcsPunishments> punishmentsList = gcsPunishmentsService.getGcsPunishmentsList(cad.getServiceId(),
						null, 1);
				if (punishmentsList.size() > 0) {
					Integer punishmentsPoints = 0;
					for (GcsPunishments gcsPunishments : punishmentsList) {
						punishmentsPoints += gcsPunishments.getPoints() == null ? 0 : gcsPunishments.getPoints();
						;
					}
					cadFilterPayload.setPoints(punishmentsPoints);
				}
				cadetFilterList.add(cadFilterPayload);

			}
			edCadetPayload.setTotalRecords(totalRecords);
			edCadetPayload.setCadetFilterPayload(cadetFilterList);
			return edCadetPayload;
		} else {
			return null;
		}
	}

	@Override
	public EdCadetPayload getCadetsBySearchWithoutPagination(String serviceId) {
		List<CadetFilterPayload> cadetFilterList = new ArrayList<CadetFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		EdCadetPayload edCadetPayload = new EdCadetPayload();

		if (serviceId != null && !serviceId.trim().equals("")) {
			cadetList = cadetRepo.findByStatusAndServiceIdLike(1, "%" + serviceId + "%");
			totalRecords = cadetList.size();
		} else {
			cadetList = cadetRepo.findAllByStatus(1);
			// cadetList = pageCadet.toList();
			totalRecords = cadetList.size();
		}

		if (cadetList != null && cadetList.size() != 0) {
			for (Cadet cad : cadetList) {
				CadetFilterPayload cadFilterPayload = new CadetFilterPayload();
				cadFilterPayload.setId(cad.getId());
				cadFilterPayload.setTermId(cad.getTerm());
				cadFilterPayload.setName(cad.getName());
				cadFilterPayload.setBattalian(cad.getBattalian());
				cadFilterPayload.setCompany(cad.getCompany());
				cadFilterPayload.setRank(cad.getCadetRank());
				cadFilterPayload.setServiceId(cad.getServiceId());
				cadFilterPayload.setCourse(cad.getCourse());
				cadFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				cadFilterPayload.setNationality(cad.getNationality());
				cadFilterPayload.setEntry(cad.getEntry());
				Term term = termRepo.findById(cad.getTerm()).get();
				cadFilterPayload.setTermName(term.getName());
				// get GC punishments points if available
				List<GcsPunishments> punishmentsList = gcsPunishmentsService.getGcsPunishmentsList(cad.getServiceId(),
						null, 1);
				if (punishmentsList.size() > 0) {
					Integer punishmentsPoints = 0;
					for (GcsPunishments gcsPunishments : punishmentsList) {
						punishmentsPoints += gcsPunishments.getPoints() == null ? 0 : gcsPunishments.getPoints();
						;
					}
					cadFilterPayload.setPoints(punishmentsPoints);
				}
				cadetFilterList.add(cadFilterPayload);

			}
			edCadetPayload.setTotalRecords(totalRecords);
			edCadetPayload.setCadetFilterPayload(cadetFilterList);
			return edCadetPayload;
		} else {
			return null;
		}
	}

	@Override
	public AdminCadetPayload getAllCadetListWithFilterAndPagination(String status, Long termId, String battalion,
			String company, Pageable pageable) {
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		AdminCadetPayload adminCadetPayload = new AdminCadetPayload();
		if (status == null || status.trim().isEmpty() || status.equals("All")) {
			Integer[] deletedStatus = { 2, 3 };
			if (termId != null) {
				if (battalion != null) {
					if (company != null) {
						cadetList = cadetRepo.findAllByTermAndBattalianAndCompanyAndStatusNotIn(termId, battalion,
								company, deletedStatus, pageable);
						totalRecords = cadetRepo.findAllByTermAndBattalianAndCompanyAndStatusNotIn(termId, battalion,
								company, deletedStatus).size();
					} else {
						cadetList = cadetRepo.findAllByTermAndBattalianAndStatusNotIn(termId, battalion, deletedStatus,
								pageable);
						totalRecords = cadetRepo
								.findAllByTermAndBattalianAndStatusNotIn(termId, battalion, deletedStatus).size();
					}
				} else {
					cadetList = cadetRepo.findAllByTermAndStatusNotIn(termId, deletedStatus, pageable);
					totalRecords = cadetRepo.findAllByTermAndStatusNotIn(termId, deletedStatus).size();
				}
			} else {
				if (battalion != null) {
					if (company != null) {
						cadetList = cadetRepo.findAllByBattalianAndCompanyAndStatusNotIn(battalion, company,
								deletedStatus, pageable);
						totalRecords = cadetRepo
								.findAllByBattalianAndCompanyAndStatusNotIn(battalion, company, deletedStatus).size();
					} else {
						cadetList = cadetRepo.findAllByBattalianAndStatusNotIn(battalion, deletedStatus, pageable);
						totalRecords = cadetRepo.findAllByBattalianAndStatusNotIn(battalion, deletedStatus).size();
					}
				} else {
					cadetList = cadetRepo.findAllByStatusNotIn(deletedStatus, pageable);
					totalRecords = cadetRepo.findAllByStatusNotIn(deletedStatus).size();
				}
			}
		} else {
			Integer status1 = Integer.parseInt(status);
			if (termId != null) {
				if (battalion != null) {
					if (company != null) {
						cadetList = cadetRepo.findAllByTermAndBattalianAndCompanyAndStatus(termId, battalion, company,
								status1, pageable);
						totalRecords = cadetRepo
								.findAllByTermAndBattalianAndCompanyAndStatus(termId, battalion, company, status1)
								.size();
					} else {
						cadetList = cadetRepo.findAllByTermAndBattalianAndStatus(termId, battalion, status1, pageable);
						totalRecords = cadetRepo.findAllByTermAndBattalianAndStatus(termId, battalion, status1).size();
					}
				} else {
					cadetList = cadetRepo.findAllByTermAndStatus(termId, status1, pageable);
					totalRecords = cadetRepo.findAllByTermAndStatus(termId, status1).size();
				}
			} else {
				if (battalion != null) {
					if (company != null) {
						cadetList = cadetRepo.findAllByBattalianAndCompanyAndStatus(battalion, company, status1,
								pageable);
						totalRecords = cadetRepo.findAllByBattalianAndCompanyAndStatus(battalion, company, status1)
								.size();
					} else {
						cadetList = cadetRepo.findAllByBattalianAndStatus(battalion, status1, pageable);
						totalRecords = cadetRepo.findAllByBattalianAndStatus(battalion, status1).size();
					}
				} else {
					cadetList = cadetRepo.findAllByStatus(status1, pageable);
					totalRecords = cadetRepo.findAllByStatus(status1).size();
				}
			}
		}

		for (Cadet cad : cadetList) {
			Optional<Term> term = termRepo.findById(cad.getTerm());
			cad.setTermName(term.get().getName());
		}
		adminCadetPayload.setTotalRecords(totalRecords);
		adminCadetPayload.setCadetList(cadetList);
		return adminCadetPayload;
	}

	@Override
	public AdminCadetPayload getAdminCadetsBySearch(String status, String serviceId, Pageable pageable) {
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		AdminCadetPayload adminCadetPayload = new AdminCadetPayload();
		if (status == null || status.trim().isEmpty() || status.equals("All")) {
			Integer[] deletedStatus = { 2, 3 };
			if (serviceId != null && !serviceId.trim().equals("")) {
				cadetList = cadetRepo.findByStatusNotInAndServiceIdLike(deletedStatus, "%" + serviceId + "%", pageable);
				totalRecords = cadetRepo.findByStatusNotInAndServiceIdLike(deletedStatus, "%" + serviceId + "%").size();
			} else {
				cadetList = cadetRepo.findAllByStatusNotIn(deletedStatus, pageable);
				totalRecords = cadetRepo.findAllByStatusNotIn(deletedStatus).size();
			}
		} else {
			Integer status1 = Integer.parseInt(status);
			if (serviceId != null && !serviceId.trim().equals("")) {
				cadetList = cadetRepo.findByStatusAndServiceIdLike(status1, "%" + serviceId + "%", pageable);
				totalRecords = cadetRepo.findByStatusAndServiceIdLike(status1, "%" + serviceId + "%").size();
			} else {
				cadetList = cadetRepo.findAllByStatus(status1, pageable);
				totalRecords = cadetRepo.findAllByStatus(status1).size();
			}
		}
		for (Cadet cad : cadetList) {
			Optional<Term> term = termRepo.findById(cad.getTerm());
			cad.setTermName(term.get().getName());
		}
		adminCadetPayload.setTotalRecords(totalRecords);
		adminCadetPayload.setCadetList(cadetList);
		return adminCadetPayload;

	}
	////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Cadet createCadetDetailNew(Cadet cadet, String url, String uploadDir) {
		CopyOnWriteArrayList<CadetEducation> cadetEdu = new CopyOnWriteArrayList<>();
		Cadet cadteSave = new Cadet();
//		for (int i = 0; i < file.size(); i++) {
//			String doc = "";
//			doc = FileUploader.uploadProfileImage(file.get(i), uploadDir);
//			if (null != doc && i == 0 && file.get(0) != null) {
//				cadet.setProfileImg(url + doc);
//			}
//
//			else if (null != doc && i == 1) {
//				cadet.setAdharImg(url + doc);
//			} else if (null != doc && i == 2) {
//				cadet.setPanImg(url + doc);
//			} else if (i > 2) {
//				for (CadetEducation ce : cadet.getCadetEducation()) {
//					System.out.println(cadet.getCadetEducation().size()
//
//							+ ce.getExamination());
//					if (i == 3 && doc != null) {
//
//						ce.setCertImg(url + doc);
//						cadetEdu.add(ce);
//
//						i++;
//
//					} else if (i == 4) {
//						doc = FileUploader.uploadProfileImage(file.get(i), uploadDir);
//						if (doc != "") {
//							ce.setCertImg(url + doc);
//						} else {
//							ce.setCertImg(doc);
//						}
//						cadetEdu.add(ce);
//						i++;
//
//					} else if (i == 5) {
//						doc = FileUploader.uploadProfileImage(file.get(i), uploadDir);
//						if (doc != "") {
//							ce.setCertImg(url + doc);
//						} else {
//							ce.setCertImg(doc);
//						}
//						cadetEdu.add(ce);
//						i++;
//
//					} else if (i == 6) {
//						doc = FileUploader.uploadProfileImage(file.get(i), uploadDir);
//						if (doc != "") {
//							ce.setCertImg(url + doc);
//						} else {
//							ce.setCertImg(doc);
//						}
//						cadetEdu.add(ce);
//						i++;
//					}
//
//				}
//
//			}
//
//		}
		// cadet.setServiceId("A-" + cadetRepo.findAllOrderByIdDesc() != null ?
		// cadetRepo.findAllOrderByIdDesc() : "1");
		cadet.setCadetEducation(cadetEdu);
		try {
			String pwd = new BCryptPasswordEncoder().encode(cadet.getPassword());
			cadet.setPassword(pwd);
			cadteSave = cadetRepo.save(cadet);

			Battalion battalion = battalionRepo.findByShortName(cadet.getBattalian());
			// Auth Table
			if (cadteSave != null && pwd != null) {
				AuthTable authData = new AuthTable();
				authData.setEmail(cadet.getEmail().trim());
				authData.setUsername(cadet.getUsername().trim());
				authData.setHasRole("3"); //formultipleroles
				authData.setName(cadet.getName().trim());
				authData.setPassword(pwd);
				authData.setBattalionId(battalion.getId());
				authData = loginRepo.save(authData);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return cadteSave;
	}


}