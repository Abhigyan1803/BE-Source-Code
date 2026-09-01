package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cadet;
import com.example.demo.model.CampMarksResult;
import com.example.demo.model.CampSubjectDetails;
import com.example.demo.model.CampSubjectResult;
import com.example.demo.model.RunbackRouteMr;
import com.example.demo.payload.CampMarksFilterPayload;
import com.example.demo.payload.CampMarksPayload;
import com.example.demo.payload.CampMarksRouteRunBack;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.CampMarksResultRepo;
import com.example.demo.service.AdminBattalionService;
import com.example.demo.service.CampMarksResultsService;
import com.example.demo.service.CampSubjectDetailsService;
import com.example.demo.service.CampSubjectResultService;

@Service
public class CampMarksResultsServiceImpl implements CampMarksResultsService {

	@Autowired
	private CampMarksResultRepo campMarksResultRepo;

	@Autowired
	private CampSubjectDetailsService campSubjectDetailsService;

	@Autowired
	private CampSubjectResultService campSubjectResultService;

	@Autowired
	AdminBattalionService adminBattalionService;

	@Autowired
	AdminCadetRepo cadetRepo;

	@Override
	public CampMarksResult createCampMarkResult(CampMarksResult CampMarksResult) {
		// TODO Auto-generated method stub
		CampMarksResult saveCampMarksResult = campMarksResultRepo.save(CampMarksResult);
		return saveCampMarksResult;
	}

	@Override
	public CampMarksResult updateCampMarkResult(CampMarksResult campMarksResult) {
		// TODO Auto-generated method stub
		CampMarksResult campMarksRslt = null;
		Optional<CampMarksResult> campMarksRsltData = campMarksResultRepo.findById(campMarksResult.getId());
		if (campMarksRsltData.isPresent()) {
			campMarksRslt = campMarksRsltData.get();
			if (campMarksRslt != null) {
				if (campMarksResult.getExerciseTypeId() != null) {
					campMarksRslt.setExerciseTypeId(campMarksResult.getExerciseTypeId());
				}
				if (campMarksResult.getGcAppt() != null) {
					campMarksRslt.setGcAppt(campMarksResult.getGcAppt());
				}
				campMarksRslt.setObtainedMarks(campMarksResult.getObtainedMarks());
				campMarksRslt.setRemarks(campMarksResult.getRemarks());
				campMarksRslt.setTotalMarks(campMarksResult.getTotalMarks());
				campMarksRslt.setUpdatedAt(campMarksResult.getUpdatedAt());

				List<CampSubjectResult> SubListTemp = new ArrayList<CampSubjectResult>();
				List<CampSubjectResult> SubList = campMarksResult.getCampSubjectResult();
				for (CampSubjectResult subject : SubList) {

					CampSubjectResult campSubjectResult = campSubjectResultService.getSubResultById(subject.getId());
					campSubjectResult.setObtainedMarks(subject.getObtainedMarks());
					// campSubjectResult.setSubjectId(subject.getSubjectId());
					// campSubjectResult.setTermId(subject.getTermId());
					// campSubjectResult.setTotalMarks(subject.getTotalMarks());
					campSubjectResult.setUpdatedAt(new Date());
					SubListTemp.add(campSubjectResult);
					campSubjectResultService.updateSubResult(campSubjectResult);
					// campMarksRslt.setCampSubjectResult((List<CampSubjectResult>)
					// campSubjectResult);
				}

				campMarksRslt.setCampSubjectResult(SubListTemp);
			}

			campMarksRslt = campMarksResultRepo.save(campMarksRslt);

		}

		return campMarksRslt;
	}

	@Override
	public Optional<CampMarksResult> findByServiceIdAndTermId(String serviceId, int termId) {
		Optional<CampMarksResult> getCampMarksResult = campMarksResultRepo.findByServiceIdAndTermId(serviceId, termId);
		return getCampMarksResult;
	}

	@Override
	public JSONObject findByServiceIdAndTermIdAndExerciseTypeId(String serviceId, int termId, long exerciseTypeId)
			throws Exception {
		// TODO Auto-generated method stub

		JSONObject campMarksResult = null;
		CampMarksResult cmr = null;
		Optional<CampMarksResult> getCampMarksResult = campMarksResultRepo
				.findByServiceIdAndTermIdAndExerciseTypeId(serviceId, termId, exerciseTypeId);
		if (getCampMarksResult.isPresent()) {
			cmr = getCampMarksResult.get();
			campMarksResult = new JSONObject();
			campMarksResult.put("createdAt", cmr.getCreatedAt());
			campMarksResult.put("exerciseType", cmr.getExerciseTypeId());
			campMarksResult.put("gcAppt", cmr.getGcAppt());
			campMarksResult.put("campMarksResultId", cmr.getId());
			campMarksResult.put("totalObtainedMarks", cmr.getObtainedMarks());
			campMarksResult.put("remarks", cmr.getRemarks());
			campMarksResult.put("serviceId", cmr.getServiceId());
			campMarksResult.put("status", cmr.getStatus());
			campMarksResult.put("termId", cmr.getTermId());
			campMarksResult.put("totalMarks", cmr.getTotalMarks());
			campMarksResult.put("updatedAt", cmr.getUpdatedAt());

			List<CampSubjectResult> sublist = cmr.getCampSubjectResult();
			JSONArray jarrCampSubResult = new JSONArray();
			for (CampSubjectResult campSub : sublist) {
				JSONObject CampSubResult = new JSONObject();
				CampSubResult.put("createAt", campSub.getCreatedAt());
				CampSubResult.put("campMarksSubId", campSub.getId());
				CampSubResult.put("obtainedMarks", campSub.getObtainedMarks());
				CampSubResult.put("serviceId", campSub.getServiceId());
				CampSubResult.put("status", campSub.getStatus());
				CampSubResult.put("subjectId", campSub.getSubjectId());
				CampSubResult.put("termId", campSub.getTermId());
				CampSubResult.put("updatedAt", campSub.getUpdatedAt());
				CampSubjectDetails campSubjectDetails = campSubjectDetailsService
						.getSubjectById(campSub.getSubjectId());
				CampSubResult.put("subjectName", campSubjectDetails.getSubjectName());
				CampSubResult.put("totalmarks", campSub.getTotalMarks());
				jarrCampSubResult.put(CampSubResult);
			}
			campMarksResult.put("CampSubjectResult", jarrCampSubResult);

		}
		return campMarksResult;
	}

	@Override
	public List<CampMarksResult> findByServiceId(String serviceId) {
		List<CampMarksResult> getCampMarksResult = campMarksResultRepo.findByServiceIdOrderByTermId(serviceId);
		if (getCampMarksResult != null && getCampMarksResult.size() != 0) {
			for (CampMarksResult cadMark : getCampMarksResult) {
				List<CampSubjectResult> campSub = cadMark.getCampSubjectResult();
				for (CampSubjectResult subjectResult : campSub) {
					CampSubjectDetails campSubjectDetails = campSubjectDetailsService
							.getSubjectById(subjectResult.getSubjectId());
					subjectResult.setSubjectName(campSubjectDetails.getSubjectName());
				}

			}
			return getCampMarksResult;
		} else {
			return null;
		}
	}

	@Override
	public CampMarksRouteRunBack updateCampMarksRouteMarchRunback(CampMarksRouteRunBack campMarksRouteMarchRunback) {

		if (campMarksRouteMarchRunback != null) {
			Boolean isUpdated = false;
			CampMarksResult campMarks = campMarksRouteMarchRunback.getCampMarksResult();
			RunbackRouteMr routeMarch = campMarksRouteMarchRunback.getRouteMarch();
			RunbackRouteMr runback = campMarksRouteMarchRunback.getRunback();
			if (campMarks != null && campMarks.getId() != null && campMarks.getId() != 0) {
				CampMarksResult result1 = updateCampMarkResult(campMarks);
				if (result1 != null) {
					isUpdated = true;
				}
			}
			if (routeMarch != null && routeMarch.getId() != null && routeMarch.getId() != 0) {
				RunbackRouteMr result2 = adminBattalionService.updateRunbackRouteMr(routeMarch);
				if (result2 != null) {
					isUpdated = true;
				}
			}
			if (runback != null && runback.getId() != null && runback.getId() != 0) {
				RunbackRouteMr result3 = adminBattalionService.updateRunbackRouteMr(runback);
				if (result3 != null) {
					isUpdated = true;
				}
			}

			if (isUpdated) {
				return campMarksRouteMarchRunback;
			} else {
				return null;
			}
		}
		return null;
	}

	public CampMarksResult findByServiceIdAndTermIdAndExerciseTypeId1(String serviceId, int termId,
			long exerciseTypeId) {
		ArrayList<CampSubjectResult> listSubject = new ArrayList<CampSubjectResult>();
		// OqMarksResult oqMarksResult =
		// oqMarksResultRepo.findByServiceIdAndTermIdAndEntryTypeId(serviceId, termId,
		// entryTypeId);

		CampMarksResult campMarksResult = null;

		Optional<CampMarksResult> getCampMarksResult = campMarksResultRepo
				.findByServiceIdAndTermIdAndExerciseTypeId(serviceId, termId, exerciseTypeId);
		if (getCampMarksResult.isPresent()) {
			campMarksResult = getCampMarksResult.get();
		}
		if (campMarksResult != null) {
			System.out.println("serviceId===>" + serviceId);
			List<CampSubjectResult> sublist = campMarksResult.getCampSubjectResult();
			for (CampSubjectResult oqSub : sublist) {
				CampSubjectDetails campSubjectDetails = campSubjectDetailsService.getSubjectById(oqSub.getSubjectId());
				oqSub.setSubjectName(campSubjectDetails.getSubjectName());
				listSubject.add(oqSub);
			}
			campMarksResult.setCampSubjectResult(listSubject);
		}
		return campMarksResult;
	}

	@Override
	public CampMarksPayload getCadetsByTermIdAndBattaionAndCompanyAndExerciseTypeId(Long termId, String battalion,
			String company, String serviceId, Long exerciseTypeId, Pageable pageable) {
		Integer totalRecords = 0;
		CampMarksPayload campMarksPayload = new CampMarksPayload();
		List<CampMarksFilterPayload> campMarksFilterPayloadList = new ArrayList<CampMarksFilterPayload>();
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
				CampMarksFilterPayload campFilterPayload = new CampMarksFilterPayload();
				campFilterPayload.setId(cad.getId());
				campFilterPayload.setTermId(cad.getTerm());
				campFilterPayload.setName(cad.getName());
				campFilterPayload.setBattalian(cad.getBattalian());
				campFilterPayload.setCompany(cad.getCompany());
				campFilterPayload.setRank(cad.getCadetRank());
				campFilterPayload.setServiceId(cad.getServiceId());
				campFilterPayload.setCourse(cad.getCourse());
				campFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				campFilterPayload.setNationality(cad.getNationality());
				CampMarksResult leaderResult = findByServiceIdAndTermIdAndExerciseTypeId1(cad.getServiceId(),
						Integer.parseInt(cad.getTerm().toString()), exerciseTypeId);
				if (leaderResult != null) {
					///// sort logic
					List<CampSubjectResult> subjectResultList = leaderResult.getCampSubjectResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					leaderResult.setCampSubjectResult(subjectResultList);
					///// till here
					campFilterPayload.setCampMarksResult(leaderResult);
				} else {
					CampMarksResult ledMatResult = new CampMarksResult();
					List<CampSubjectResult> CampSubjectResult = new ArrayList<CampSubjectResult>();
					List<CampSubjectDetails> result = campSubjectDetailsService.getAllSubjectByStatus(1);
					Integer totalMarks = 0;
					for (CampSubjectDetails subject : result) {
						CampSubjectResult subjectResult = new CampSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarks(subject.getTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						CampSubjectResult.add(subjectResult);
					}

					ledMatResult.setCampSubjectResult(CampSubjectResult);
					ledMatResult.setTotalMarks(totalMarks);
					ledMatResult.setServiceId(cad.getServiceId());
					ledMatResult.setTermId(Integer.parseInt(cad.getTerm().toString()));
					ledMatResult.setExerciseTypeId(exerciseTypeId);
					campFilterPayload.setCampMarksResult(ledMatResult);
				}
				campMarksFilterPayloadList.add(campFilterPayload);
			}

			campMarksPayload.setTotalRecords(totalRecords);
			campMarksPayload.setCampMarksFilterPayload(campMarksFilterPayloadList);
			return campMarksPayload;
		} else {
			return null;
		}

	}

	@Override
	public String updateBulkCampMarksResult(List<CampMarksFilterPayload> campMarksFilterPayloadList) {
		String result = "failed";
		int size = campMarksFilterPayloadList.size();
		if (size > 0) {
			for (CampMarksFilterPayload campMarksPayload : campMarksFilterPayloadList) {
				CampMarksResult campMarksResult = campMarksPayload.getCampMarksResult();
				if (campMarksResult != null && campMarksResult.getId() != null && campMarksResult.getId() != 0) {
					updateCampMarkResult(campMarksResult);
				} else if (campMarksResult != null) {
					List<CampSubjectResult> CampSubResult = campMarksResult.getCampSubjectResult();
					if (CampSubResult.size() > 0) {
						for (CampSubjectResult campSubRslt : CampSubResult) {
							campSubjectResultService.createSubResult(campSubRslt);
						}
					}
					if (campMarksResult.getObtainedMarks() != null) {
						campMarksResult.setCreatedAt(new Date());
						createCampMarkResult(campMarksResult);
					}

				}
			}
			result = "success";
		}
		return result;

	}

	@Override
	public CampMarksPayload getCadetsBySearch(Long termId, String serviceId, Long exerciseTypeId, Pageable pageable) {
		CampMarksPayload campMarksPayload = null;
		List<CampMarksFilterPayload> campMarksFilterList = new ArrayList<CampMarksFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		if (termId == null || termId == 0) {
			return campMarksPayload;
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
			campMarksPayload = new CampMarksPayload();
			for (Cadet cad : cadetList) {
				CampMarksFilterPayload campFilterPayload = new CampMarksFilterPayload();
				campFilterPayload.setId(cad.getId());
				campFilterPayload.setTermId(cad.getTerm());
				campFilterPayload.setName(cad.getName());
				campFilterPayload.setBattalian(cad.getBattalian());
				campFilterPayload.setCompany(cad.getCompany());
				campFilterPayload.setRank(cad.getCadetRank());
				campFilterPayload.setServiceId(cad.getServiceId());
				campFilterPayload.setCourse(cad.getCourse());
				campFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				campFilterPayload.setNationality(cad.getNationality());
				CampMarksResult leaderResult = findByServiceIdAndTermIdAndExerciseTypeId1(cad.getServiceId(),
						Integer.parseInt(cad.getTerm().toString()), exerciseTypeId);
				if (leaderResult != null) {
					///// sort logic
					List<CampSubjectResult> subjectResultList = leaderResult.getCampSubjectResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					leaderResult.setCampSubjectResult(subjectResultList);
					///// till here
					campFilterPayload.setCampMarksResult(leaderResult);
				} else {
					CampMarksResult ledMatResult = new CampMarksResult();
					List<CampSubjectResult> leadershipSubjectResult = new ArrayList<CampSubjectResult>();
					List<CampSubjectDetails> result = campSubjectDetailsService.getAllSubjectByStatus(1);
					Integer totalMarks = 0;
					for (CampSubjectDetails subject : result) {
						CampSubjectResult subjectResult = new CampSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarks(subject.getTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						leadershipSubjectResult.add(subjectResult);
					}

					ledMatResult.setCampSubjectResult(leadershipSubjectResult);
					ledMatResult.setTotalMarks(totalMarks);
					ledMatResult.setServiceId(cad.getServiceId());
					ledMatResult.setTermId(Integer.parseInt(cad.getTerm().toString()));
					ledMatResult.setExerciseTypeId(exerciseTypeId);
					campFilterPayload.setCampMarksResult(ledMatResult);
				}
				campMarksFilterList.add(campFilterPayload);
			}
			campMarksPayload.setTotalRecords(totalRecords);
			campMarksPayload.setCampMarksFilterPayload(campMarksFilterList);
			return campMarksPayload;
		} else {
			return null;
		}
	}

}
