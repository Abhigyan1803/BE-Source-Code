package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cadet;
import com.example.demo.model.OqMarksResult;
import com.example.demo.model.OqSubjectDetails1;
import com.example.demo.model.OqSubjectResult;
import com.example.demo.payload.OqMarksFilterPayload;
import com.example.demo.payload.OqMarksPayload;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.OqMarksResultRepo;
import com.example.demo.service.OqMarksResultService;
import com.example.demo.service.OqSubjectDetailsService1;
import com.example.demo.service.OqSubjectResultService;

@Service
public class OqMarksResultServiceImpl implements OqMarksResultService {

	@Autowired
	private OqMarksResultRepo oqMarksResultRepo;

	@Autowired
	private OqSubjectDetailsService1 oqSubjectDetailsService1;

	@Autowired
	private OqSubjectResultService oqSubjectResultService;

	@Autowired
	AdminCadetRepo cadetRepo;

	@Override
	public OqMarksResult createOqMarkResult(OqMarksResult oqMarksResult) {
		// TODO Auto-generated method stub
		return oqMarksResultRepo.save(oqMarksResult);
	}

	@Override
	public OqMarksResult findByServiceIdAndTermIdAndEntryTypeId(String serviceId, int termId, long entryTypeId) {
		// TODO Auto-generated method stub
//		OqMarkResultResponse oqMarkResultResponse = null;
//		OqSubjectResultResponse oqSubjectResultResponse = null;
		ArrayList<OqSubjectResult> listSubject = new ArrayList<OqSubjectResult>();
		OqMarksResult oqMarksResult = oqMarksResultRepo.findByServiceIdAndTermIdAndEntryTypeId(serviceId, termId,
				entryTypeId);
		if (oqMarksResult != null) {
			List<OqSubjectResult> sublist = oqMarksResult.getOqSubjectResult();
			for (OqSubjectResult oqSub : sublist) {
				OqSubjectDetails1 oqSubjectDetails1 = oqSubjectDetailsService1.getSubjectById(oqSub.getSubjectId());
				oqSub.setSubjectName(oqSubjectDetails1.getSubjectName());
				listSubject.add(oqSub);
			}
			oqMarksResult.setOqSubjectResult(listSubject);
		}

//			oqMarkResultResponse = new OqMarkResultResponse();
//			oqMarkResultResponse.setEntryTypeId(oqMarksResult.getEntryTypeId());
//			oqMarkResultResponse.setGcAppt(oqMarksResult.getGcAppt());
//			oqMarkResultResponse.setObtainedMarksBnCdr(oqMarksResult.getObtainedMarksBnCdr());
//			oqMarkResultResponse.setObtainedMarksCoyCdr(oqMarksResult.getObtainedMarksCoyCdr());
//			oqMarkResultResponse.setObtainedMarksPlCdr(oqMarksResult.getObtainedMarksPlCdr());
//			oqMarkResultResponse.setOqMarkResultId(oqMarksResult.getId());
//			oqMarkResultResponse.setServiceId(oqMarksResult.getServiceId());
//			oqMarkResultResponse.setStatus(oqMarksResult.getStatus());
//			oqMarkResultResponse.setTermId(oqMarksResult.getTermId());
//			oqMarkResultResponse.setTotalMarksBnCdr(oqMarksResult.getTotalMarksBnCdr());
//			oqMarkResultResponse.setTotalMarksCoyCdr(oqMarksResult.getTotalMarksCoyCdr());
//			oqMarkResultResponse.setTotalMarksPlCdr(oqMarksResult.getTotalMarksPlCdr());
//			oqMarkResultResponse.setCreatedAt(oqMarksResult.getCreatedAt());
//			oqMarkResultResponse.setUpdatedAt(oqMarksResult.getUpdatedAt());
//			List<OqSubjectResult> sublist = oqMarksResult.getOqSubjectResult();
//			for (OqSubjectResult oqSub : sublist) {
//				oqSubjectResultResponse = new OqSubjectResultResponse();
//				oqSubjectResultResponse.setObtainedMarksBnCdr(oqSub.getObtainedMarksBnCdr());
//				oqSubjectResultResponse.setObtainedMarksCoyCdr(oqSub.getObtainedMarksCoyCdr());
//				oqSubjectResultResponse.setObtainedMarksPlCdr(oqSub.getObtainedMarksPlCdr());
//				oqSubjectResultResponse.setServiceId(oqSub.getServiceId());
//				oqSubjectResultResponse.setStatus(oqSub.getStatus());
//				oqSubjectResultResponse.setSubjectId(oqSub.getSubjectId());
//				oqSubjectResultResponse.setSubResultId(oqSub.getId());
//				oqSubjectResultResponse.setTermId(oqSub.getTermId());
//				oqSubjectResultResponse.setCreatedAt(oqSub.getCreatedAt());
//				oqSubjectResultResponse.setUpdatedAt(oqSub.getUpdatedAt());
//				OqSubjectDetails1 oqSubjectDetails1 = oqSubjectDetailsService1.getSubjectById(oqSub.getSubjectId());
//				oqSubjectResultResponse.setTotalMarksBnCdr(oqSubjectDetails1.getBnTotalMarks());
//				oqSubjectResultResponse.setTotalMarksCoyCdr(oqSubjectDetails1.getCoyTotalMarks());
//				oqSubjectResultResponse.setTotalMarksPlCdr(oqSubjectDetails1.getPlTotalMarks());
//				oqSubjectResultResponse.setSubjectName(oqSubjectDetails1.getSubjectName());
//				listSubject.add(oqSubjectResultResponse);
//			}
//			oqMarkResultResponse.setOqSubjectResultResponse(listSubject);
//		}
		return oqMarksResult;
	}

	@Override
	public OqMarksResult updateOqMarkResult(OqMarksResult oqMarksResult) {
		// TODO Auto-generated method stub
		OqMarksResult oqMarksRslt = null;
		Optional<OqMarksResult> oqMarksResultdata = oqMarksResultRepo.findById(oqMarksResult.getId());
		if (oqMarksResultdata.isPresent()) {
			oqMarksRslt = oqMarksResultdata.get();
			if (oqMarksRslt != null) {
				if (oqMarksRslt.getEntryTypeId() != null) {
					oqMarksRslt.setEntryTypeId(oqMarksResult.getEntryTypeId());
				}
				if (oqMarksRslt.getGcAppt() != null) {
					oqMarksRslt.setGcAppt(oqMarksResult.getGcAppt());
				}
				oqMarksRslt.setObtainedMarksBnCdr(oqMarksResult.getObtainedMarksBnCdr());
				oqMarksRslt.setObtainedMarksCoyCdr(oqMarksResult.getObtainedMarksCoyCdr());
				oqMarksRslt.setObtainedMarksPlCdr(oqMarksResult.getObtainedMarksPlCdr());
				oqMarksRslt.setUpdatedAt(oqMarksResult.getUpdatedAt());
				oqMarksRslt.setTotalMarksBnCdr(oqMarksResult.getTotalMarksBnCdr());
				oqMarksRslt.setTotalMarksCoyCdr(oqMarksResult.getTotalMarksCoyCdr());
				oqMarksRslt.setTotalMarksPlCdr(oqMarksResult.getTotalMarksPlCdr());

				List<OqSubjectResult> SubListTemp = new ArrayList<OqSubjectResult>();
				List<OqSubjectResult> SubList = oqMarksResult.getOqSubjectResult();
				for (OqSubjectResult subject : SubList) {
					OqSubjectResult oqSubjectResult = oqSubjectResultService.getSubResultById(subject.getId());
					oqSubjectResult.setObtainedMarksBnCdr(subject.getObtainedMarksBnCdr());
					oqSubjectResult.setObtainedMarksCoyCdr(subject.getObtainedMarksCoyCdr());
					oqSubjectResult.setObtainedMarksPlCdr(subject.getObtainedMarksPlCdr());
					oqSubjectResult.setServiceId(subject.getServiceId());
					oqSubjectResult.setTotalMarksBnCdr(subject.getTotalMarksBnCdr());
					oqSubjectResult.setTotalMarksCoyCdr(subject.getTotalMarksCoyCdr());
					oqSubjectResult.setTotalMarksPlCdr(subject.getTotalMarksPlCdr());
					oqSubjectResult.setUpdatedAt(subject.getUpdatedAt());
					SubListTemp.add(oqSubjectResult);
					oqSubjectResultService.updateSubResult(oqSubjectResult);
				}

				oqMarksRslt.setOqSubjectResult(SubListTemp);
			}

			oqMarksRslt = oqMarksResultRepo.save(oqMarksRslt);

		}

		return oqMarksRslt;
	}

//	@Override
//	public List<OqMarksResult> findByServiceIdOrderBySubjectId(String serviceId) {
//		ArrayList<OqSubjectResult> listSubject = new ArrayList<OqSubjectResult>();
//		List<OqMarksResult> oqMarksResultList = oqMarksResultRepo.getByServiceIdOrderByTermIdANdEntryTypeId(serviceId);
//		if (oqMarksResultList != null && oqMarksResultList.size() != 0) {
//			for (OqMarksResult oqMarksResult : oqMarksResultList) {
//				List<OqSubjectResult> sublist = oqMarksResult.getOqSubjectResult();
//				for (OqSubjectResult oqSub : sublist) {
//					OqSubjectDetails1 oqSubjectDetails1 = oqSubjectDetailsService1.getSubjectById(oqSub.getSubjectId());
//					oqSub.setSubjectName(oqSubjectDetails1.getSubjectName());
//					listSubject.add(oqSub);
//				}
//				oqMarksResult.setOqSubjectResult(listSubject);
//			}
//
//			return oqMarksResultList;
//		} else {
//			return null;
//		}
//
//	}

	@Override
	public List<OqMarksResult> findByServiceIdOrderBySubjectId(String serviceId) {
		// ArrayList<OqSubjectResult> listSubject = new ArrayList<OqSubjectResult>();
		List<OqMarksResult> oqMarksResultList = oqMarksResultRepo.getByServiceIdOrderByTermIdAndEntryTypeId(serviceId);
		if (oqMarksResultList != null && oqMarksResultList.size() != 0) {
			for (OqMarksResult oqMarksResult : oqMarksResultList) {
				List<OqSubjectResult> sublist = oqMarksResult.getOqSubjectResult();
				for (OqSubjectResult oqSub : sublist) {
					OqSubjectDetails1 oqSubjectDetails1 = oqSubjectDetailsService1.getSubjectById(oqSub.getSubjectId());
					oqSub.setSubjectName(oqSubjectDetails1.getSubjectName());
					// listSubject.add(oqSub);
				}
				oqMarksResult.setOqSubjectResult(sublist);
			}

			return oqMarksResultList;
		}
		return null;
	}

	@Override
	public OqMarksPayload getCadetsByTermIdAndBattaionAndCompanyAndEntryTypeId(Long termId, String battalion,
			String company, Long entryTypeId, String serviceId, Pageable pageable) {
		Integer totalRecords = 0;
		OqMarksPayload oqMarksPayload = new OqMarksPayload();
		List<OqMarksFilterPayload> oqMarksFilterList = new ArrayList<OqMarksFilterPayload>();
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
				OqMarksFilterPayload oqFilterPayload = new OqMarksFilterPayload();
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
				OqMarksResult oqMarksResult = findByServiceIdAndTermIdAndEntryTypeId(cad.getServiceId(),
						Integer.parseInt(cad.getTerm().toString()), entryTypeId);
				if (oqMarksResult != null) {
					///// sort logic
					List<OqSubjectResult> subjectResultList = oqMarksResult.getOqSubjectResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					oqMarksResult.setOqSubjectResult(subjectResultList);
					///// till here
					oqFilterPayload.setOqMarksResult(oqMarksResult);
				} else {
					OqMarksResult oqMatResult = new OqMarksResult();
					List<OqSubjectResult> oqMarksSubjectResult = new ArrayList<OqSubjectResult>();
					List<OqSubjectDetails1> result = oqSubjectDetailsService1.getAllSubjectByStatus(1);
					// Integer totalMarks = 0;
					Integer totalMarksPlCdr = 0;
					Integer totalMarksCoyCdr = 0;
					Integer totalMarksBnCdr = 0;
					for (OqSubjectDetails1 subject : result) {
						OqSubjectResult subjectResult = new OqSubjectResult();
						totalMarksPlCdr = totalMarksPlCdr + subject.getTotalMarksPlCdr();
						totalMarksCoyCdr = totalMarksCoyCdr + subject.getTotalMarksCoyCdr();
						totalMarksPlCdr = totalMarksPlCdr + subject.getTotalMarksBnCdr();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarksPlCdr(subject.getTotalMarksPlCdr());
						subjectResult.setTotalMarksCoyCdr(subject.getTotalMarksCoyCdr());
						subjectResult.setTotalMarksBnCdr(subject.getTotalMarksBnCdr());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						oqMarksSubjectResult.add(subjectResult);
					}

					oqMatResult.setOqSubjectResult(oqMarksSubjectResult);
					oqMatResult.setTotalMarksPlCdr(totalMarksPlCdr);
					oqMatResult.setTotalMarksCoyCdr(totalMarksCoyCdr);
					oqMatResult.setTotalMarksBnCdr(totalMarksBnCdr);
					oqMatResult.setEntryTypeId(entryTypeId);
					oqMatResult.setServiceId(cad.getServiceId());
					oqMatResult.setTermId(Integer.parseInt(cad.getTerm().toString()));
					oqMatResult.setStatus(1);
					oqFilterPayload.setOqMarksResult(oqMatResult);
				}
				oqMarksFilterList.add(oqFilterPayload);
			}

			oqMarksPayload.setTotalRecords(totalRecords);
			oqMarksPayload.setOqMarksFilterPayload(oqMarksFilterList);
			return oqMarksPayload;
		} else {
			return null;
		}

	}

	@Override
	public String updateBulkOqMarksResult(List<OqMarksFilterPayload> orMarksPayloadList) {
		// TODO Auto-generated method stub
		String result = "failed";
		int size = orMarksPayloadList.size();
		if (size > 0) {
			for (OqMarksFilterPayload oqMarksPayload : orMarksPayloadList) {
				OqMarksResult oqMarksResult = oqMarksPayload.getOqMarksResult();
				if (oqMarksResult != null && oqMarksResult.getId() != null && oqMarksResult.getId() != 0) {
					// update logic
					updateOqMarkResult(oqMarksResult);
				} else if (oqMarksResult != null) {
					// add logic
					List<OqSubjectResult> oqSubjectResult = oqMarksResult.getOqSubjectResult();
					if (oqSubjectResult != null) {
						for (OqSubjectResult oqSubjectRslt : oqSubjectResult) {
							oqSubjectResultService.createSubResult(oqSubjectRslt);
						}
					}
					if (oqMarksResult.getObtainedMarksBnCdr() != null || oqMarksResult.getObtainedMarksCoyCdr() != null
							|| oqMarksResult.getObtainedMarksPlCdr() != null) {
						oqMarksResult.setCreatedAt(new Date());
						createOqMarkResult(oqMarksResult);
					}

				}
			}
			result = "success";
		}
		return result;

	}

	@Override
	public OqMarksPayload getCadetsBySearch(String serviceId, Long termId, Long entryTypeId, Pageable pageable) {
		OqMarksPayload oqMarkspPayload = null;
		List<OqMarksFilterPayload> oqMarksFilterList = new ArrayList<OqMarksFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		if (termId == null || termId == 0) {
			return oqMarkspPayload;
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
			oqMarkspPayload = new OqMarksPayload();
			for (Cadet cad : cadetList) {
				OqMarksFilterPayload oqFilterPayload = new OqMarksFilterPayload();
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
				OqMarksResult oqMarksResult = findByServiceIdAndTermIdAndEntryTypeId(cad.getServiceId(),
						Integer.parseInt(termId.toString()), entryTypeId);
				if (oqMarksResult != null) {
					///// sort logic
					List<OqSubjectResult> subjectResultList = oqMarksResult.getOqSubjectResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					oqMarksResult.setOqSubjectResult(subjectResultList);
					///// till here
					oqFilterPayload.setOqMarksResult(oqMarksResult);
				} else {
					OqMarksResult oqMatResult = new OqMarksResult();
					List<OqSubjectResult> oqMarksSubjectResult = new ArrayList<OqSubjectResult>();
					List<OqSubjectDetails1> result = oqSubjectDetailsService1.getAllSubjectByStatus(1);
					Integer totalMarksPlCdr = 0;
					Integer totalMarksCoyCdr = 0;
					Integer totalMarksBnCdr = 0;
					for (OqSubjectDetails1 subject : result) {
						OqSubjectResult subjectResult = new OqSubjectResult();
						totalMarksPlCdr = totalMarksPlCdr + subject.getTotalMarksPlCdr();
						totalMarksCoyCdr = totalMarksCoyCdr + subject.getTotalMarksCoyCdr();
						totalMarksPlCdr = totalMarksPlCdr + subject.getTotalMarksBnCdr();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarksPlCdr(subject.getTotalMarksPlCdr());
						subjectResult.setTotalMarksCoyCdr(subject.getTotalMarksCoyCdr());
						subjectResult.setTotalMarksBnCdr(subject.getTotalMarksBnCdr());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						oqMarksSubjectResult.add(subjectResult);
					}

					oqMatResult.setOqSubjectResult(oqMarksSubjectResult);
					oqMatResult.setTotalMarksPlCdr(totalMarksPlCdr);
					oqMatResult.setTotalMarksCoyCdr(totalMarksCoyCdr);
					oqMatResult.setTotalMarksBnCdr(totalMarksBnCdr);
					oqMatResult.setEntryTypeId(entryTypeId);
					oqMatResult.setServiceId(cad.getServiceId());
					oqMatResult.setTermId(Integer.parseInt(cad.getTerm().toString()));
					oqMatResult.setStatus(1);
					oqFilterPayload.setOqMarksResult(oqMatResult);
				}
				oqMarksFilterList.add(oqFilterPayload);
			}
			oqMarkspPayload.setTotalRecords(totalRecords);
			oqMarkspPayload.setOqMarksFilterPayload(oqMarksFilterList);
			return oqMarkspPayload;
		} else {
			return null;
		}
	}

	@Override
	public List<OqMarksResult> findByServiceIdAndEntryTypeId(String serviceId, Long entryTypeId) {
		new ArrayList<OqSubjectResult>();
		List<OqMarksResult> oqMarksResult = oqMarksResultRepo.findByServiceIdAndEntryTypeId(serviceId, entryTypeId);
		return oqMarksResult;
	}
}
