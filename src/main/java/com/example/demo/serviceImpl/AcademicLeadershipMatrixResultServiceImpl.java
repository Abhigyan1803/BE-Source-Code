package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.AcademicLeadershipMatrixResult;
import com.example.demo.model.AcademicLeadershipMatrixSubjectResult;
import com.example.demo.model.AcademicLeadershipSubject;
import com.example.demo.model.Cadet;
import com.example.demo.payload.LeadershipFilterPayload;
import com.example.demo.payload.LeadershipPayload;
import com.example.demo.repository.AcademicLeadershipMatrixResultRepo;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.service.AcademicLeadershipMatrixResultService;
import com.example.demo.service.AcademicLeadershipMatrixSubjectResultService;
import com.example.demo.service.AcademicLeadershipSubjectService;

@Service
public class AcademicLeadershipMatrixResultServiceImpl implements AcademicLeadershipMatrixResultService {

	@Autowired
	private AcademicLeadershipMatrixResultRepo academicLeadershipMatrixResultRepo;

	@Autowired
	private AcademicLeadershipMatrixSubjectResultService academicLeadershipMatrixSubjectResultService;

	@Autowired
	private AcademicLeadershipSubjectService academicLeadershipSubjectService;

	@Autowired
	AdminCadetRepo cadetRepo;

	@Override
	public AcademicLeadershipMatrixResult createAcademicLeadershipMatrixResult(
			AcademicLeadershipMatrixResult academicLeadershipMatrixResult) {
		AcademicLeadershipMatrixResult saveAcademicLeadershipMatrixResult = academicLeadershipMatrixResultRepo
				.save(academicLeadershipMatrixResult);
		return saveAcademicLeadershipMatrixResult;
	}

	@Override
	public AcademicLeadershipMatrixResult updateAcademicLeadershipMatrixResult(
			AcademicLeadershipMatrixResult academicLeadershipMatrixResult) {
		// TODO Auto-generated method stub
		AcademicLeadershipMatrixResult academicLeadershipMatrixRslt = null;
		Optional<AcademicLeadershipMatrixResult> academicLeadershipMatrixRsltData = academicLeadershipMatrixResultRepo
				.findById(academicLeadershipMatrixResult.getId());
		if (academicLeadershipMatrixRsltData.isPresent()) {

			academicLeadershipMatrixRslt = academicLeadershipMatrixRsltData.get();
			if (academicLeadershipMatrixRslt != null) {

				academicLeadershipMatrixRslt.setObtainedMarks(academicLeadershipMatrixResult.getObtainedMarks());
				academicLeadershipMatrixRslt.setRemarks(academicLeadershipMatrixResult.getRemarks());
				academicLeadershipMatrixRslt.setTotalMarks(academicLeadershipMatrixResult.getTotalMarks());
				academicLeadershipMatrixRslt.setUpdatedAt(academicLeadershipMatrixResult.getUpdatedAt());

				List<AcademicLeadershipMatrixSubjectResult> SubListTemp = new ArrayList<AcademicLeadershipMatrixSubjectResult>();
				List<AcademicLeadershipMatrixSubjectResult> SubList = academicLeadershipMatrixResult
						.getLeadershipSubjectResult();
				for (AcademicLeadershipMatrixSubjectResult subject : SubList) {

					AcademicLeadershipMatrixSubjectResult academicLeadershipMatrixSubjectResult = academicLeadershipMatrixSubjectResultService
							.getSubResultById(subject.getId());
					academicLeadershipMatrixSubjectResult.setObtainedMarks(subject.getObtainedMarks());
					// campSubjectResult.setSubjectId(subject.getSubjectId());
					// campSubjectResult.setTermId(subject.getTermId());
					// campSubjectResult.setTotalMarks(subject.getTotalMarks());
					academicLeadershipMatrixSubjectResult.setUpdatedAt(new Date());
					SubListTemp.add(academicLeadershipMatrixSubjectResult);
					academicLeadershipMatrixSubjectResultService.updateSubResult(academicLeadershipMatrixSubjectResult);
					// campMarksRslt.setCampSubjectResult((List<CampSubjectResult>)
					// campSubjectResult);
				}

				academicLeadershipMatrixRslt.setLeadershipSubjectResult(SubListTemp);
			}

			academicLeadershipMatrixRslt = academicLeadershipMatrixResultRepo.save(academicLeadershipMatrixRslt);

		}

		return academicLeadershipMatrixRslt;
	}

	@Override
	public AcademicLeadershipMatrixResult findByServiceIdAndTermId(String serviceId, int termId) {
		Optional<AcademicLeadershipMatrixResult> leadershipMatResult = academicLeadershipMatrixResultRepo
				.findByServiceIdAndTermId(serviceId, termId);
		if (leadershipMatResult.isPresent()) {
			AcademicLeadershipMatrixResult leaderMatResult = leadershipMatResult.get();
			List<AcademicLeadershipMatrixSubjectResult> list = leaderMatResult.getLeadershipSubjectResult();
			for (AcademicLeadershipMatrixSubjectResult leadetMatSubReslt : list) {
				AcademicLeadershipSubject leaderSub = academicLeadershipSubjectService
						.getSubjectById(leadetMatSubReslt.getSubjectId());
				leadetMatSubReslt.setSubjectName(leaderSub.getSubjectName());
			}
			return leaderMatResult;
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
//		Optional<CampMarksResult> getCampMarksResult = campMarksResultRepo
//				.findByServiceIdAndTermIdAndExerciseTypeId(serviceId, termId, exerciseTypeId);
//		if (getCampMarksResult.isPresent()) {
//			cmr = getCampMarksResult.get();
//			campMarksResult = new JSONObject();
//			campMarksResult.put("createdAt", cmr.getCreatedAt());
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
//				CampSubjectDetails campSubjectDetails = campSubjectDetailsService
//						.getSubjectById(campSub.getSubjectId());
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
	public List<AcademicLeadershipMatrixResult> findByServiceId(String serviceId) {
		List<AcademicLeadershipMatrixResult> leadershipMatResultList = academicLeadershipMatrixResultRepo
				.findByServiceIdOrderByTermId(serviceId);
		if (leadershipMatResultList != null && leadershipMatResultList.size() != 0) {
			for (AcademicLeadershipMatrixResult leaderMatResult : leadershipMatResultList) {
				List<AcademicLeadershipMatrixSubjectResult> list = leaderMatResult.getLeadershipSubjectResult();
				for (AcademicLeadershipMatrixSubjectResult leadetMatSubReslt : list) {
					AcademicLeadershipSubject leaderSub = academicLeadershipSubjectService
							.getSubjectById(leadetMatSubReslt.getSubjectId());
					leadetMatSubReslt.setSubjectName(leaderSub.getSubjectName());
				}
			}

			return leadershipMatResultList;
		}
		return null;
	}

	@Override
	public LeadershipPayload getCadetsByTermIdAndBattaionAndCompany(Long termId, String battalion, String company,
			String serviceId, Pageable pageable) {
		Integer totalRecords = 0;
		LeadershipPayload leadershipPayload = new LeadershipPayload();
		List<LeadershipFilterPayload> leadershipFilterList = new ArrayList<LeadershipFilterPayload>();
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
				cadetList = cadetRepo.findByStatusAndServiceIdLike(1, "%" + serviceId + "%", pageable);
				totalRecords = cadetRepo.findByStatusAndServiceIdLike(1, "%" + serviceId + "%").size();
			} else {
				cadetList = cadetRepo.findAllByStatus(1, pageable);
				// cadetList = pageCadet.toList();
				totalRecords = cadetRepo.findAllByStatus(1).size();
			}

		}

		if (cadetList != null && cadetList.size() != 0) {
			for (Cadet cad : cadetList) {
				LeadershipFilterPayload leadFilterPayload = new LeadershipFilterPayload();
				leadFilterPayload.setId(cad.getId());
				leadFilterPayload.setTermId(cad.getTerm());
				leadFilterPayload.setName(cad.getName());
				leadFilterPayload.setBattalian(cad.getBattalian());
				leadFilterPayload.setCompany(cad.getCompany());
				leadFilterPayload.setRank(cad.getCadetRank());
				leadFilterPayload.setServiceId(cad.getServiceId());
				leadFilterPayload.setCourse(cad.getCourse());
				leadFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				leadFilterPayload.setNationality(cad.getNationality());
				AcademicLeadershipMatrixResult leaderResult = findByServiceIdAndTermId(cad.getServiceId(),
						Integer.parseInt(cad.getTerm().toString()));
				if (leaderResult != null) {
					///// sort logic
					List<AcademicLeadershipMatrixSubjectResult> subjectResultList = leaderResult
							.getLeadershipSubjectResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					leaderResult.setLeadershipSubjectResult(subjectResultList);
					///// till here
					leadFilterPayload.setAcademicLeadershipMatrixResult(leaderResult);
				} else {
					AcademicLeadershipMatrixResult ledMatResult = new AcademicLeadershipMatrixResult();
					List<AcademicLeadershipMatrixSubjectResult> leadershipSubjectResult = new ArrayList<AcademicLeadershipMatrixSubjectResult>();
//					List<AcademicLeadershipSubject> result = academicLeadershipSubjectService
//							.getBystatusOrderByIdDesc(1);
					List<AcademicLeadershipSubject> result = academicLeadershipSubjectService.getBystatus(1);
					Integer totalMarks = 0;
					for (AcademicLeadershipSubject subject : result) {
						AcademicLeadershipMatrixSubjectResult subjectResult = new AcademicLeadershipMatrixSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarks(subject.getTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						leadershipSubjectResult.add(subjectResult);
					}

					ledMatResult.setLeadershipSubjectResult(leadershipSubjectResult);
					ledMatResult.setTotalMarks(totalMarks);
					ledMatResult.setServiceId(cad.getServiceId());
					ledMatResult.setTermId(Integer.parseInt(cad.getTerm().toString()));
					ledMatResult.setStatus(1);
					leadFilterPayload.setAcademicLeadershipMatrixResult(ledMatResult);
				}
				leadershipFilterList.add(leadFilterPayload);
			}

			leadershipPayload.setTotalRecords(totalRecords);
			leadershipPayload.setLeadershipFilterPayload(leadershipFilterList);
			return leadershipPayload;
		} else {
			return null;
		}

	}

	@Override
	public String updateBulkAcademicLeadershipMatrixResult(List<LeadershipFilterPayload> leadershipPayloadList) {
		String result = "failed";
		int size = leadershipPayloadList.size();
		if (size > 0) {
			for (LeadershipFilterPayload leadershipPayload : leadershipPayloadList) {
				AcademicLeadershipMatrixResult academicLeadershipMatrixResult = leadershipPayload
						.getAcademicLeadershipMatrixResult();
				if (academicLeadershipMatrixResult != null && academicLeadershipMatrixResult.getId() != null
						&& academicLeadershipMatrixResult.getId() != 0) {
					// update logic
					updateAcademicLeadershipMatrixResult(academicLeadershipMatrixResult);
				} else if (academicLeadershipMatrixResult != null) {
					// add logic
					List<AcademicLeadershipMatrixSubjectResult> academicLeadershipMatrixSubjectResult = academicLeadershipMatrixResult
							.getLeadershipSubjectResult();
					if (academicLeadershipMatrixSubjectResult != null) {
						for (AcademicLeadershipMatrixSubjectResult leadershipSubRslt : academicLeadershipMatrixSubjectResult) {
							academicLeadershipMatrixSubjectResultService.createSubResult(leadershipSubRslt);
						}
					}
					if (academicLeadershipMatrixResult.getObtainedMarks() != null) {
						academicLeadershipMatrixResult.setCreatedAt(new Date());
						createAcademicLeadershipMatrixResult(academicLeadershipMatrixResult);
					}
				}
			}
			result = "success";
		}
		return result;

	}

	@Override
	public LeadershipPayload getCadetsBySearch(Long termId, String serviceId, Pageable pageable) {
		LeadershipPayload leadershipPayload = null;
		List<LeadershipFilterPayload> leadershipFilterList = new ArrayList<LeadershipFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		if (termId == null || termId == 0) {
			return leadershipPayload;
		}
		if (serviceId != null && !serviceId.trim().equals("")) {
			cadetList = cadetRepo.findAllByTermAndStatusAndServiceIdLike(termId, 1, "%" + serviceId + "%", pageable);
			totalRecords = cadetRepo.findAllByTermAndStatusAndServiceIdLike(termId, 1, "%" + serviceId + "%").size();
		} else {
			cadetList = cadetRepo.findAllByTermAndStatus(termId, 1, pageable);
			totalRecords = cadetRepo.findAllByTermAndStatus(termId, 1).size();
		}
		if (cadetList != null && cadetList.size() != 0) {
			leadershipPayload = new LeadershipPayload();
			for (Cadet cad : cadetList) {
				LeadershipFilterPayload leadFilterPayload = new LeadershipFilterPayload();
				leadFilterPayload.setId(cad.getId());
				leadFilterPayload.setTermId(cad.getTerm());
				leadFilterPayload.setName(cad.getName());
				leadFilterPayload.setBattalian(cad.getBattalian());
				leadFilterPayload.setCompany(cad.getCompany());
				leadFilterPayload.setRank(cad.getCadetRank());
				leadFilterPayload.setServiceId(cad.getServiceId());
				leadFilterPayload.setCourse(cad.getCourse());
				leadFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				leadFilterPayload.setNationality(cad.getNationality());
				AcademicLeadershipMatrixResult leaderResult = findByServiceIdAndTermId(cad.getServiceId(),
						Integer.parseInt(cad.getTerm().toString()));
				if (leaderResult != null) {
					///// sort logic
					List<AcademicLeadershipMatrixSubjectResult> subjectResultList = leaderResult
							.getLeadershipSubjectResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					leaderResult.setLeadershipSubjectResult(subjectResultList);
					///// till here
					leadFilterPayload.setAcademicLeadershipMatrixResult(leaderResult);
				} else {
					AcademicLeadershipMatrixResult ledMatResult = new AcademicLeadershipMatrixResult();
					List<AcademicLeadershipMatrixSubjectResult> leadershipSubjectResult = new ArrayList<AcademicLeadershipMatrixSubjectResult>();
//					List<AcademicLeadershipSubject> result = academicLeadershipSubjectService
//							.getBystatusOrderByIdDesc(1);
					List<AcademicLeadershipSubject> result = academicLeadershipSubjectService.getBystatus(1);
					Integer totalMarks = 0;
					for (AcademicLeadershipSubject subject : result) {
						AcademicLeadershipMatrixSubjectResult subjectResult = new AcademicLeadershipMatrixSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarks(subject.getTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						leadershipSubjectResult.add(subjectResult);
					}

					ledMatResult.setLeadershipSubjectResult(leadershipSubjectResult);
					ledMatResult.setTotalMarks(totalMarks);
					ledMatResult.setServiceId(cad.getServiceId());
					ledMatResult.setTermId(Integer.parseInt(cad.getTerm().toString()));
					ledMatResult.setStatus(1);
					leadFilterPayload.setAcademicLeadershipMatrixResult(ledMatResult);
				}
				leadershipFilterList.add(leadFilterPayload);
			}
			leadershipPayload.setTotalRecords(totalRecords);
			leadershipPayload.setLeadershipFilterPayload(leadershipFilterList);
			return leadershipPayload;
		} else {
			return null;
		}
	}

}
