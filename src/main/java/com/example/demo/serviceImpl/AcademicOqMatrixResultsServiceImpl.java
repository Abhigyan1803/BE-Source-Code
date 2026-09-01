package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.AcademicOqMatrixResult;
import com.example.demo.model.AcademicOqMatrixSubjectResult;
import com.example.demo.model.AcademicOqSubject;
import com.example.demo.model.Cadet;
import com.example.demo.model.OqDrillResult;
import com.example.demo.model.OqEqtnResult;
import com.example.demo.payload.EdOqMatrixPayload;
import com.example.demo.payload.OqMatrixFilterPayload;
import com.example.demo.payload.OqMatrixPayload;
import com.example.demo.payload.OqMatrixTermPayload;
import com.example.demo.repository.AcademicOqMatrixResultRepo;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.OqDrillResultRepository;
import com.example.demo.repository.OqEqtnResultRepository;
//import com.example.demo.repository.CampMarksResultRepo;
import com.example.demo.service.AcademicOqMatrixResultsService;
import com.example.demo.service.AcademicOqMatrixSubjectResultService;
import com.example.demo.service.AcademicOqSubjectService;

@Service
public class AcademicOqMatrixResultsServiceImpl implements AcademicOqMatrixResultsService {

	@Autowired
	private AcademicOqMatrixResultRepo academicOqMatrixResultRepo;

	@Autowired
	private AcademicOqMatrixSubjectResultService academicOqMatrixSubjectResultService;

	@Autowired
	private AcademicOqSubjectService academicOqSubjectService;

	@Autowired
	private AdminCadetRepo cadetRepo;

	@Autowired
	private OqDrillResultRepository drillRepo;

	@Autowired
	private OqEqtnResultRepository eqtnRepo;

	@Override
	public AcademicOqMatrixResult createAcademicOqMarkResult(AcademicOqMatrixResult oqMarksResult) {
		// TODO Auto-generated method stub
		AcademicOqMatrixResult saveOqMarksResult = academicOqMatrixResultRepo.save(oqMarksResult);
		return saveOqMarksResult;
	}

	@Override
	public AcademicOqMatrixResult updateAcademicOqMarkResult(AcademicOqMatrixResult oqMarksResult) {
		// TODO Auto-generated method stub
		AcademicOqMatrixResult oqMarkRslt = null;
		Optional<AcademicOqMatrixResult> oqMarksRsltData = academicOqMatrixResultRepo.findById(oqMarksResult.getId());
		if (oqMarksRsltData.isPresent()) {
			oqMarkRslt = oqMarksRsltData.get();
			if (oqMarkRslt != null) {

				oqMarkRslt.setObtainedMarks(oqMarksResult.getObtainedMarks());
				oqMarkRslt.setRemarks(oqMarksResult.getRemarks());
				oqMarkRslt.setTotalMarks(oqMarksResult.getTotalMarks());
				oqMarkRslt.setUpdatedAt(oqMarksResult.getUpdatedAt());

				List<AcademicOqMatrixSubjectResult> SubListTemp = new ArrayList<AcademicOqMatrixSubjectResult>();
				List<AcademicOqMatrixSubjectResult> SubList = oqMarksResult.getAcademicOqMatrixSubjectResult();
				for (AcademicOqMatrixSubjectResult subject : SubList) {

					AcademicOqMatrixSubjectResult academicOqMatrixSubjectResult = academicOqMatrixSubjectResultService
							.getSubResultById(subject.getId());
					academicOqMatrixSubjectResult.setObtainedMarks(subject.getObtainedMarks());
					academicOqMatrixSubjectResult.setUpdatedAt(new Date());
					SubListTemp.add(academicOqMatrixSubjectResult);
					academicOqMatrixSubjectResultService.updateSubResult(academicOqMatrixSubjectResult);
				}

				oqMarkRslt.setAcademicOqMatrixSubjectResult(SubListTemp);
			}

			oqMarkRslt = academicOqMatrixResultRepo.save(oqMarkRslt);

		}

		return oqMarkRslt;
	}

	@Override
	public AcademicOqMatrixResult findByServiceIdAndTermIdAndTermType(String serviceId, int termId, String termType) {
		Optional<AcademicOqMatrixResult> getCampMarksResult = academicOqMatrixResultRepo
				.findByServiceIdAndTermIdAndTermType(serviceId, termId, termType);
		if (getCampMarksResult.isPresent()) {
			AcademicOqMatrixResult OqMatResult = getCampMarksResult.get();
			List<AcademicOqMatrixSubjectResult> list = OqMatResult.getAcademicOqMatrixSubjectResult();
			for (AcademicOqMatrixSubjectResult oqMatSubResult : list) {

				AcademicOqSubject oqSub = academicOqSubjectService.getSubjectById(oqMatSubResult.getSubjectId());
				oqMatSubResult.setSubjectName(oqSub.getSubjectName());
				oqMatSubResult.setSubjectCategory(oqSub.getSubjectCategory());
			}
			return OqMatResult;

		}
		return null;
	}

//	@Override
//	public JSONObject findByServiceIdAndTermIdAndExerciseTypeId(String serviceId, int termId, long exerciseTypeId)
//			throws Exception {
//		// TODO Auto-generated method stub
//
//		JSONObject campMarksResult = null;
//		CampMarksResult cmr = null;
//		Optional<CampMarksResult> getCampMarksResult = academicOqMatrixResultRepo
//				.findByServiceIdAndTermIdAndExerciseTypeId(serviceId, termId, exerciseTypeId);
//		if (getCampMarksResult.isPresent()) {
//			cmr = getCampMarksResult.get();
//			campMarksResult = new JSONObject();
//			campMarksResult.put("createdAt", cmr.getCreatedAt());
//			campMarksResult.put("exerciseType", cmr.getExerciseType());
//			campMarksResult.put("gcAppt", cmr.getGcAppt());
//			campMarksResult.put("campMarksResultId", cmr.getId());
//			campMarksResult.put("totalObtainedMarks", cmr.getObtainedMarks());
//			campMarksResult.put("remarks", cmr.getRemarks());
//			campMarksResult.put("serviceId", cmr.getServiceId());
//			campMarksResult.put("status", cmr.getStatus());
//			campMarksResult.put("termId", cmr.getTermId());
//			campMarksResult.put("totalMarks", cmr.getTotalMarks());
//			campMarksResult.put("updatedAt", cmr.getUpdatedAt());
//
//			List<CampSubjectResult> sublist = cmr.getCampSubjectResult();
//			JSONArray jarrCampSubResult = new JSONArray();
//			for (CampSubjectResult campSub : sublist) {
//				JSONObject CampSubResult = new JSONObject();
//				CampSubResult.put("createAt", campSub.getCreatedAt());
//				CampSubResult.put("campMarksSubId", campSub.getId());
//				CampSubResult.put("obtainedMarks", campSub.getObtainedMarks());
//				CampSubResult.put("serviceId", campSub.getServiceId());
//				CampSubResult.put("status", campSub.getStatus());
//				CampSubResult.put("subjectId", campSub.getSubjectId());
//				CampSubResult.put("termId", campSub.getTermId());
//				CampSubResult.put("updatedAt", campSub.getUpdatedAt());
//				AcademicOqSubject campSubjectDetails = academicOqSubjectService.getSubjectById(campSub.getSubjectId());
//				CampSubResult.put("subjectName", campSubjectDetails.getSubjectName());
//				CampSubResult.put("totalmarks", campSub.getTotalMarks());
//				jarrCampSubResult.put(CampSubResult);
//			}
//			campMarksResult.put("CampSubjectResult", jarrCampSubResult);
//
//		}
//		return campMarksResult;
//	}

	@Override
	public OqMatrixPayload getCadetsByTermIdAndTermTypeAndBattaionAndCompany(Long termId, String termType,
			String battalion, String company, String serviceId, Pageable pageable) {
		Integer totalRecords = 0;
		OqMatrixPayload oqMatrixPayload = new OqMatrixPayload();
		List<OqMatrixFilterPayload> oqMatrixFilterList = new ArrayList<OqMatrixFilterPayload>();
		List<Cadet> cadetList = null;
		if (termId != null) {
			if (battalion != null) {
				if (company != null) {
					if (serviceId != null) {
						cadetList = cadetRepo.findAllByTermAndBattalianAndCompanyAndStatusAndServiceIdLike(termId,
								battalion, company, 1, "%" + serviceId + "%", pageable);
						totalRecords = cadetRepo.findAllByTermAndBattalianAndCompanyAndStatusAndServiceIdLike(termId,
								battalion, company, 1, "%" + serviceId + "%").size();
					} else {
						cadetList = cadetRepo.findAllByTermAndBattalianAndCompanyAndStatus(termId, battalion, company,
								1, pageable);
						totalRecords = cadetRepo
								.findAllByTermAndBattalianAndCompanyAndStatus(termId, battalion, company, 1).size();
					}
				} else {
					if (serviceId != null) {
						cadetList = cadetRepo.findAllByTermAndBattalianAndStatusAndServiceIdLike(termId, battalion, 1,
								"%" + serviceId + "%", pageable);
						totalRecords = cadetRepo.findAllByTermAndBattalianAndStatusAndServiceIdLike(termId, battalion,
								1, "%" + serviceId + "%").size();
					} else {
						cadetList = cadetRepo.findAllByTermAndBattalianAndStatus(termId, battalion, 1, pageable);
						totalRecords = cadetRepo.findAllByTermAndBattalianAndStatus(termId, battalion, 1).size();
					}
				}
			} else {
				if (serviceId != null) {
					cadetList = cadetRepo.findAllByTermAndStatusAndServiceIdLike(termId, 1, "%" + serviceId + "%",
							pageable);
					totalRecords = cadetRepo.findAllByTermAndStatusAndServiceIdLike(termId, 1, "%" + serviceId + "%")
							.size();
				} else {
					cadetList = cadetRepo.findAllByTermAndStatus(termId, 1, pageable);
					totalRecords = cadetRepo.findAllByTermAndStatus(termId, 1).size();
				}
			}
		} else {
			if (serviceId != null) {
				cadetList = cadetRepo.findByStatusAndServiceIdLike(1, "%" + serviceId + "%", pageable);// --%D/5016%
				totalRecords = cadetRepo.findByStatusAndServiceIdLike(1, "%" + serviceId + "%").size();
			} else {
				cadetList = cadetRepo.findAllByStatus(1, pageable);
				// cadetList = pageCadet.toList();
				totalRecords = cadetRepo.findAllByStatus(1).size();
			}
		}
		if (cadetList != null && cadetList.size() != 0) {
			for (Cadet cad : cadetList) {
				OqMatrixFilterPayload oqFilterPayload = new OqMatrixFilterPayload();
				oqFilterPayload.setId(cad.getId());
				oqFilterPayload.setTermId(cad.getTerm());
				oqFilterPayload.setName(cad.getName());
				oqFilterPayload.setBattalian(cad.getBattalian());
				oqFilterPayload.setCompany(cad.getCompany());
				oqFilterPayload.setRank(cad.getCadetRank());
				oqFilterPayload.setServiceId(cad.getServiceId());
				oqFilterPayload.setCourse(cad.getCourse());
				oqFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				oqFilterPayload.setNationality(cad.getNationality());
				AcademicOqMatrixResult oqResult = findByServiceIdAndTermIdAndTermType(cad.getServiceId(),
						Integer.parseInt(cad.getTerm().toString()), termType);
				if (oqResult != null) {
					///// sort logic
					List<AcademicOqMatrixSubjectResult> subjectResultList = oqResult.getAcademicOqMatrixSubjectResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					oqResult.setAcademicOqMatrixSubjectResult(subjectResultList);
					///// till here
					oqFilterPayload.setAcademicOqMatrixResult(oqResult);
				} else {
					AcademicOqMatrixResult oqMatResult = new AcademicOqMatrixResult();
					List<AcademicOqMatrixSubjectResult> oqSubjectResult = new ArrayList<AcademicOqMatrixSubjectResult>();
					List<AcademicOqSubject> result = academicOqSubjectService.getAcademicOqSubjectList(1);
					Integer totalMarks = 0;
					for (AcademicOqSubject subject : result) {
						AcademicOqMatrixSubjectResult subjectResult = new AcademicOqMatrixSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarks(subject.getTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						oqSubjectResult.add(subjectResult);
					}
					oqMatResult.setAcademicOqMatrixSubjectResult(oqSubjectResult);
					oqMatResult.setTotalMarks(totalMarks);
					oqMatResult.setServiceId(cad.getServiceId());
					oqMatResult.setTermId(Integer.parseInt(cad.getTerm().toString()));
					oqMatResult.setStatus(1);
					oqFilterPayload.setAcademicOqMatrixResult(oqMatResult);
				}
				oqMatrixFilterList.add(oqFilterPayload);
			}
			oqMatrixPayload.setTotalRecords(totalRecords);
			oqMatrixPayload.setOqMatrixFilterPayload(oqMatrixFilterList);
			return oqMatrixPayload;
		} else {
			return null;
		}
	}

	@Override
	public String updateBulkAcademicOqMarkResult(List<OqMatrixFilterPayload> oqMatrixPayloadList) {
		String result = "failed";
		int size = oqMatrixPayloadList.size();
		if (size > 0) {
			for (OqMatrixFilterPayload oqMatrixPayload : oqMatrixPayloadList) {
				AcademicOqMatrixResult academicOqMatrixResult = oqMatrixPayload.getAcademicOqMatrixResult();
				if (academicOqMatrixResult != null && academicOqMatrixResult.getId() != null
						&& academicOqMatrixResult.getId() != 0) {
					updateAcademicOqMarkResult(academicOqMatrixResult);
				} else if (academicOqMatrixResult != null) {
					List<AcademicOqMatrixSubjectResult> oqSubResult = academicOqMatrixResult
							.getAcademicOqMatrixSubjectResult();
					if (oqSubResult != null) {

						for (AcademicOqMatrixSubjectResult oqSubRslt : oqSubResult) {
							academicOqMatrixSubjectResultService.createSubResult(oqSubRslt);
						}
					}
					if (academicOqMatrixResult.getObtainedMarks() != null) {
						academicOqMatrixResult.setCreatedAt(new Date());
						createAcademicOqMarkResult(academicOqMatrixResult);
					}

				}
			}
			result = "success";
		}
		return result;
	}

	@Override
	public OqMatrixPayload getCadetsBySearch(Long termId, String termType, String serviceId, Pageable pageable) {
		OqMatrixPayload oqMatrixPayload = null;
		List<OqMatrixFilterPayload> oqMatrixFilterList = new ArrayList<OqMatrixFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		if (termId == null || termId == 0) {
			return oqMatrixPayload;
		}

		if (serviceId != null && !serviceId.trim().equals("")) {
			cadetList = cadetRepo.findAllByTermAndStatusAndServiceIdLike(termId, 1, "%" + serviceId + "%", pageable);
			totalRecords = cadetRepo.findAllByTermAndStatusAndServiceIdLike(termId, 1, "%" + serviceId + "%").size();
		} else {
			cadetList = cadetRepo.findAllByTermAndStatus(termId, 1, pageable);
			// cadetList = pageCadet.toList();
			totalRecords = cadetRepo.findAllByTermAndStatus(termId, 1).size();
		}
		if (cadetList != null && cadetList.size() != 0) {
			oqMatrixPayload = new OqMatrixPayload();
			for (Cadet cad : cadetList) {
				OqMatrixFilterPayload oqFilterPayload = new OqMatrixFilterPayload();
				oqFilterPayload.setId(cad.getId());
				oqFilterPayload.setTermId(cad.getTerm());
				oqFilterPayload.setName(cad.getName());
				oqFilterPayload.setBattalian(cad.getBattalian());
				oqFilterPayload.setCompany(cad.getCompany());
				oqFilterPayload.setRank(cad.getCadetRank());
				oqFilterPayload.setServiceId(cad.getServiceId());
				oqFilterPayload.setCourse(cad.getCourse());
				oqFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				oqFilterPayload.setNationality(cad.getNationality());
				AcademicOqMatrixResult oqResult = findByServiceIdAndTermIdAndTermType(cad.getServiceId(),
						Integer.parseInt(cad.getTerm().toString()), termType);
				if (oqResult != null) {
					///// sort logic
					List<AcademicOqMatrixSubjectResult> subjectResultList = oqResult.getAcademicOqMatrixSubjectResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					oqResult.setAcademicOqMatrixSubjectResult(subjectResultList);
					///// till here
					oqFilterPayload.setAcademicOqMatrixResult(oqResult);
				} else {
					AcademicOqMatrixResult oqMatResult = new AcademicOqMatrixResult();
					List<AcademicOqMatrixSubjectResult> oqSubjectResult = new ArrayList<AcademicOqMatrixSubjectResult>();
					List<AcademicOqSubject> result = academicOqSubjectService.getAcademicOqSubjectList(1);
					Integer totalMarks = 0;
					for (AcademicOqSubject subject : result) {
						AcademicOqMatrixSubjectResult subjectResult = new AcademicOqMatrixSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarks(subject.getTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						oqSubjectResult.add(subjectResult);
					}
					oqMatResult.setAcademicOqMatrixSubjectResult(oqSubjectResult);
					oqMatResult.setTotalMarks(totalMarks);
					oqMatResult.setServiceId(cad.getServiceId());
					oqMatResult.setTermId(Integer.parseInt(cad.getTerm().toString()));
					oqMatResult.setStatus(1);
					oqFilterPayload.setAcademicOqMatrixResult(oqMatResult);
				}
				oqMatrixFilterList.add(oqFilterPayload);
			}
			oqMatrixPayload.setTotalRecords(totalRecords);
			oqMatrixPayload.setOqMatrixFilterPayload(oqMatrixFilterList);
			return oqMatrixPayload;
		} else {
			return null;
		}
	}

	@Override
	public OqMatrixTermPayload findOqMatrixDrillEqtn(String serviceId) {
		// TODO Auto-generated method stub
		OqMatrixTermPayload result = null;
		System.out.println("11111");
		List<AcademicOqMatrixResult> oqMatrixList = academicOqMatrixResultRepo.findByServiceIdOrderByTermId(serviceId);
		System.out.println("AcademicOqMatrixResult");
		List<OqDrillResult> oqDrillList = drillRepo.findByServiceIdOrderByTermId(serviceId);
		System.out.println("AcademicOqMatrixResult");

		List<OqEqtnResult> oqEqtnList = eqtnRepo.findByServiceIdOrderByTermId(serviceId);
		System.out.println("AcademicOqMatrixResult");

		if (oqMatrixList.size() > 0 || oqDrillList.size() > 0 || oqEqtnList.size() > 0) {
			result = new OqMatrixTermPayload();
			if (result != null) {

				System.out.println("OqMatrixTermPayload");

				result.setTerm1(new EdOqMatrixPayload());
				result.setTerm2(new EdOqMatrixPayload());
				result.setTerm3(new EdOqMatrixPayload());
				result.setTech2(new EdOqMatrixPayload());
				result.setTech3(new EdOqMatrixPayload());
				// for BMT-1
				System.out.println("OqMatrixTermPayload11111");

				List<AcademicOqMatrixResult> OqDrillResultList = new ArrayList<AcademicOqMatrixResult>();
				for (AcademicOqMatrixResult oqMatrix : oqMatrixList) {
					if (oqMatrix.getTermId() == 1) {
						// EdserviceSubPayload term1Bmt = new EdserviceSubPayload();
						OqDrillResultList.add(oqMatrix);
						result.getTerm1().setOqMatrix(OqDrillResultList);
						System.out.println("OqMatrixTermPayloadTerm1");

					}
					if (oqMatrix.getTermId() == 2) {
						OqDrillResultList.add(oqMatrix);
						result.getTerm2().setOqMatrix(OqDrillResultList);
					}
					if (oqMatrix.getTermId() == 3) {
						OqDrillResultList.add(oqMatrix);
						result.getTerm3().setOqMatrix(OqDrillResultList);
					}
					System.out.println("OqMatrixTermPayload");

					if (oqMatrix.getTermId() == 7) {
						OqDrillResultList.add(oqMatrix);
						result.getTech2().setOqMatrix(OqDrillResultList);
					}
					if (oqMatrix.getTermId() == 8) {
						OqDrillResultList.add(oqMatrix);
						result.getTech3().setOqMatrix(OqDrillResultList);
					}
				}
				List<OqDrillResult> oqDrillResultList = new ArrayList<OqDrillResult>();
				for (OqDrillResult oqDrill : oqDrillList) {
					if (oqDrill.getTermId() == 1) {
						oqDrillResultList.add(oqDrill);
						result.getTerm1().setOqDrill(oqDrillResultList);
						System.out.println("DRILLResult");

					}
					if (oqDrill.getTermId() == 2) {
						oqDrillResultList.add(oqDrill);
						result.getTerm2().setOqDrill(oqDrillResultList);
					}
					if (oqDrill.getTermId() == 3) {
						oqDrillResultList.add(oqDrill);
						result.getTerm3().setOqDrill(oqDrillResultList);
					}
					if (oqDrill.getTermId() == 7) {
						oqDrillResultList.add(oqDrill);
						result.getTech2().setOqDrill(oqDrillResultList);
					}
					if (oqDrill.getTermId() == 8) {
						oqDrillResultList.add(oqDrill);
						result.getTech3().setOqDrill(oqDrillResultList);
					}
				}
				List<OqEqtnResult> OqEqtnResultList = new ArrayList<OqEqtnResult>();
				for (OqEqtnResult oqEqtn : oqEqtnList) {

					if (oqEqtn.getTermId() == 1) {
						OqEqtnResultList.add(oqEqtn);
						result.getTerm1().setOqEqtn(OqEqtnResultList);
					}
					if (oqEqtn.getTermId() == 2) {
						OqEqtnResultList.add(oqEqtn);
						result.getTerm2().setOqEqtn(OqEqtnResultList);
					}
					if (oqEqtn.getTermId() == 3) {
						OqEqtnResultList.add(oqEqtn);
						result.getTerm3().setOqEqtn(OqEqtnResultList);
					}
					if (oqEqtn.getTermId() == 7) {
						OqEqtnResultList.add(oqEqtn);
						result.getTech2().setOqEqtn(OqEqtnResultList);
					}
					if (oqEqtn.getTermId() == 8) {
						OqEqtnResultList.add(oqEqtn);
						result.getTech3().setOqEqtn(OqEqtnResultList);
					}
					System.out.println("eqtn");

				}
				if (result.getTerm1().getOqMatrix() == null && result.getTerm1().getOqDrill() == null
						&& result.getTerm1().getOqEqtn() == null) {
					result.setTerm1(null);
				}
				if (result.getTerm2().getOqMatrix() == null && result.getTerm2().getOqDrill() == null
						&& result.getTerm2().getOqEqtn() == null) {
					result.setTerm2(null);
				}
				if (result.getTerm3().getOqMatrix() == null && result.getTerm3().getOqDrill() == null
						&& result.getTerm3().getOqEqtn() == null) {
					result.setTerm3(null);
				}
				if (result.getTech2().getOqMatrix() == null && result.getTech2().getOqDrill() == null
						&& result.getTech2().getOqEqtn() == null) {
					result.setTech2(null);
				}
				if (result.getTech3().getOqMatrix() == null && result.getTech3().getOqDrill() == null
						&& result.getTech3().getOqEqtn() == null) {
					result.setTech3(null);
				}
				System.out.println("eqtn");

			}
			System.out.println("eqtnnnnn");

			return result;
		} else {
			return null;
		}
	}

	@Override
	public OqMatrixTermPayload findOqMatrixDrillEqtnAndTermType(String serviceId, String termType) {
		// TODO Auto-generated method stub
		OqMatrixTermPayload result = null;
		System.out.println("11111");
		List<AcademicOqMatrixResult> oqMatrixList = academicOqMatrixResultRepo
				.findByServiceIdAndTermTypeOrderByTermId(serviceId, "FINAL TERM");
		System.out.println("AcademicOqMatrixResult");
		List<OqDrillResult> oqDrillList = drillRepo.findByServiceIdAndTermTypeOrderByTermId(serviceId, "FINAL TERM");
		System.out.println("AcademicOqMatrixResult");

		List<OqEqtnResult> oqEqtnList = eqtnRepo.findByServiceIdAndTermTypeOrderByTermId(serviceId, "FINAL TERM");
		System.out.println("AcademicOqMatrixResult");

		if (oqMatrixList.size() > 0 || oqDrillList.size() > 0 || oqEqtnList.size() > 0) {
			result = new OqMatrixTermPayload();
			System.out.println("OqMatrixTermPayload");

			result.setTerm1(new EdOqMatrixPayload());
			result.setTerm2(new EdOqMatrixPayload());
			result.setTerm3(new EdOqMatrixPayload());
			result.setTech2(new EdOqMatrixPayload());
			result.setTech3(new EdOqMatrixPayload());
			// for BMT-1
			System.out.println("OqMatrixTermPayload11111");

			List<AcademicOqMatrixResult> OqDrillResultList = new ArrayList<AcademicOqMatrixResult>();
			for (AcademicOqMatrixResult oqMatrix : oqMatrixList) {
				if (oqMatrix.getTermId() == 1) {
					// EdserviceSubPayload term1Bmt = new EdserviceSubPayload();
					OqDrillResultList.add(oqMatrix);
					result.getTerm1().setOqMatrix(OqDrillResultList);
					System.out.println("OqMatrixTermPayloadTerm1");

				}
				if (oqMatrix.getTermId() == 2) {
					OqDrillResultList.add(oqMatrix);
					result.getTerm2().setOqMatrix(OqDrillResultList);
				}
				if (oqMatrix.getTermId() == 3) {
					OqDrillResultList.add(oqMatrix);
					result.getTerm3().setOqMatrix(OqDrillResultList);
				}
				System.out.println("OqMatrixTermPayload");

				if (oqMatrix.getTermId() == 7) {
					OqDrillResultList.add(oqMatrix);
					result.getTech2().setOqMatrix(OqDrillResultList);
				}
				if (oqMatrix.getTermId() == 8) {
					OqDrillResultList.add(oqMatrix);
					result.getTech3().setOqMatrix(OqDrillResultList);
				}
			}
			List<OqDrillResult> oqDrillResultList = new ArrayList<OqDrillResult>();
			for (OqDrillResult oqDrill : oqDrillList) {
				if (oqDrill.getTermId() == 1) {
					oqDrillResultList.add(oqDrill);
					result.getTerm1().setOqDrill(oqDrillResultList);
					System.out.println("DRILLResult");

				}
				if (oqDrill.getTermId() == 2) {
					oqDrillResultList.add(oqDrill);
					result.getTerm2().setOqDrill(oqDrillResultList);
				}
				if (oqDrill.getTermId() == 3) {
					oqDrillResultList.add(oqDrill);
					result.getTerm3().setOqDrill(oqDrillResultList);
				}
				if (oqDrill.getTermId() == 7) {
					oqDrillResultList.add(oqDrill);
					result.getTech2().setOqDrill(oqDrillResultList);
				}
				if (oqDrill.getTermId() == 8) {
					oqDrillResultList.add(oqDrill);
					result.getTech3().setOqDrill(oqDrillResultList);
				}
			}
			List<OqEqtnResult> OqEqtnResultList = new ArrayList<OqEqtnResult>();
			for (OqEqtnResult oqEqtn : oqEqtnList) {

				if (oqEqtn.getTermId() == 1) {
					OqEqtnResultList.add(oqEqtn);
					result.getTerm1().setOqEqtn(OqEqtnResultList);
				}
				if (oqEqtn.getTermId() == 2) {
					OqEqtnResultList.add(oqEqtn);
					result.getTerm2().setOqEqtn(OqEqtnResultList);
				}
				if (oqEqtn.getTermId() == 3) {
					OqEqtnResultList.add(oqEqtn);
					result.getTerm3().setOqEqtn(OqEqtnResultList);
				}
				if (oqEqtn.getTermId() == 7) {
					OqEqtnResultList.add(oqEqtn);
					result.getTech2().setOqEqtn(OqEqtnResultList);
				}
				if (oqEqtn.getTermId() == 8) {
					OqEqtnResultList.add(oqEqtn);
					result.getTech3().setOqEqtn(OqEqtnResultList);
				}
				System.out.println("eqtn");

			}
			if (result.getTerm1().getOqMatrix() == null && result.getTerm1().getOqDrill() == null
					&& result.getTerm1().getOqEqtn() == null) {
				result.setTerm1(null);
			}
			if (result.getTerm2().getOqMatrix() == null && result.getTerm2().getOqDrill() == null
					&& result.getTerm2().getOqEqtn() == null) {
				result.setTerm2(null);
			}
			if (result.getTerm3().getOqMatrix() == null && result.getTerm3().getOqDrill() == null
					&& result.getTerm3().getOqEqtn() == null) {
				result.setTerm3(null);
			}
			if (result.getTech2().getOqMatrix() == null && result.getTech2().getOqDrill() == null
					&& result.getTech2().getOqEqtn() == null) {
				result.setTech2(null);
			}
			if (result.getTech3().getOqMatrix() == null && result.getTech3().getOqDrill() == null
					&& result.getTech3().getOqEqtn() == null) {
				result.setTech3(null);
			}
			System.out.println("eqtn");

		}
		System.out.println("eqtnnnnn");

		return result;

	}

}
