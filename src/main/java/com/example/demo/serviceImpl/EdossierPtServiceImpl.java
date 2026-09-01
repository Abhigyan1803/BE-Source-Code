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
import com.example.demo.model.EdossierPtResult;
import com.example.demo.model.EdossierPtSubject;
import com.example.demo.model.EdossierPtSubjectResult;
import com.example.demo.payload.EdossierPtResultFilterPayload;
import com.example.demo.payload.EdossierPtResultPayload;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.EdossierPtResultRepository;
import com.example.demo.repository.EdossierPtSubjectResultRepository;
import com.example.demo.service.EdossierPtService;
import com.example.demo.service.EdossierPtSubjectService;

@Service
public class EdossierPtServiceImpl implements EdossierPtService {

	@Autowired
	private EdossierPtResultRepository ptResultRepo;

	@Autowired
	private EdossierPtSubjectResultRepository ptSubjectResultRepo;

	@Autowired
	private EdossierPtSubjectService subService;

	@Autowired
	private AdminCadetRepo cadetRepo;

	@Override
	public EdossierPtSubjectResult createSubResult(EdossierPtSubjectResult ptSubRslt) {
		// TODO Auto-generated method stub
		return ptSubjectResultRepo.save(ptSubRslt);
	}

	@Override
	public EdossierPtResult createEdossierPtResult(EdossierPtResult edossierPtResult) {
		// TODO Auto-generated method stub
		return ptResultRepo.save(edossierPtResult);
	}

	@Override
	public EdossierPtResult findByServiceIdAndTermIdAndSubjectType(String serviceId, Long termId, String subjectType) {
		// TODO Auto-generated method stub
		Optional<EdossierPtResult> getEdossierPtResult = ptResultRepo.findByServiceIdAndTermIdAndSubjectType(serviceId,
				termId, subjectType);
		if (getEdossierPtResult.isPresent()) {
			EdossierPtResult ptResult = getEdossierPtResult.get();
			List<EdossierPtSubjectResult> list = ptResult.getEdossierPtSubjectResult();
			for (EdossierPtSubjectResult ptSubResult : list) {

				EdossierPtSubject ptSub = subService.getSubjectById(ptSubResult.getSubjectId());
				ptSubResult.setSubjectName(ptSub.getSubject());
				ptSubResult.setSubjectType(ptSub.getSubjectType());
				ptSubResult.setSubjectCategory(ptSub.getSubjectCategory());
			}
			return ptResult;

		}
		return null;

	}

	@Override
	public EdossierPtResult updateEdossierPtResult(EdossierPtResult edossierPtResult) {
		// TODO Auto-generated method stub
		EdossierPtResult ptRslt = null;
		Optional<EdossierPtResult> ptRsltData = ptResultRepo.findById(edossierPtResult.getId());
		if (ptRsltData.isPresent()) {
			ptRslt = ptRsltData.get();
			if (ptRslt != null) {
				// if (ptRslt.getObtainedMarks() < edossierPtResult.getObtainedMarks()) {
				ptRslt.setObtainedMarks(edossierPtResult.getObtainedMarks());
				ptRslt.setLastAttemptType(edossierPtResult.getLastAttemptType());
				if (edossierPtResult.getClearedIn() != null) {
					ptRslt.setClearedIn(edossierPtResult.getClearedIn());
				}
				// }

				ptRslt.setRemarks(edossierPtResult.getRemarks());
				ptRslt.setTotalMarks(edossierPtResult.getTotalMarks());
				ptRslt.setUpdatedAt(new Date());

				List<EdossierPtSubjectResult> SubListTemp = new ArrayList<EdossierPtSubjectResult>();
				List<EdossierPtSubjectResult> SubList = edossierPtResult.getEdossierPtSubjectResult();
				for (EdossierPtSubjectResult subject : SubList) {

					EdossierPtSubjectResult edossierPtSubjectResult = ptSubjectResultRepo
							.getSubResultById(subject.getId());
					edossierPtSubjectResult.setC1ObtainedMarks(subject.getC1ObtainedMarks());
					edossierPtSubjectResult.setC2ObtainedMarks(subject.getC2ObtainedMarks());
					edossierPtSubjectResult.setM1ObtainedMarks(subject.getM1ObtainedMarks());
					edossierPtSubjectResult.setM2ObtainedMarks(subject.getM2ObtainedMarks());
					edossierPtSubjectResult.setUpdatedAt(new Date());
					edossierPtSubjectResult.setLastAttemptType(subject.getLastAttemptType());
					if (subject.getResultGrade() != null) {
						edossierPtSubjectResult.setResultGrade(subject.getResultGrade());
					}
					if (subject.getResultSubGrade() != null) {
						edossierPtSubjectResult.setResultSubGrade(subject.getResultSubGrade());
					}
					if (subject.getClearedIn() != null) {
						edossierPtSubjectResult.setClearedIn(subject.getClearedIn());
					}
					SubListTemp.add(edossierPtSubjectResult);
					updateSubResult(edossierPtSubjectResult);
				}

				ptRslt.setEdossierPtSubjectResult(SubListTemp);
			}

			ptRslt = ptResultRepo.save(ptRslt);

		}

		return ptRslt;

	}

	public EdossierPtSubjectResult updateSubResult(EdossierPtSubjectResult oqSubjectResult) {
		EdossierPtSubjectResult oqSubResult = null;
		// TODO Auto-generated method stub
		if (oqSubjectResult != null) {
			Optional<EdossierPtSubjectResult> getOqSubResult = ptSubjectResultRepo.findById(oqSubjectResult.getId());
			oqSubResult = getOqSubResult.get();
			oqSubResult.setC1ObtainedMarks(oqSubjectResult.getC1ObtainedMarks());
			oqSubResult.setC2ObtainedMarks(oqSubjectResult.getC2ObtainedMarks());
			oqSubResult.setM1ObtainedMarks(oqSubjectResult.getM1ObtainedMarks());
			oqSubResult.setM2ObtainedMarks(oqSubjectResult.getM2ObtainedMarks());
			oqSubResult.setSubjectId(oqSubjectResult.getSubjectId());
			oqSubResult.setStatus(oqSubjectResult.getStatus());
			oqSubResult.setLastAttemptType(oqSubjectResult.getLastAttemptType());
			// oqSubResult.setTermId(oqSubjectResult.getTermId());
			oqSubResult.setTotalMarks(oqSubjectResult.getTotalMarks());
			oqSubResult.setUpdatedAt(oqSubjectResult.getUpdatedAt());
			if (oqSubjectResult.getResultGrade() != null) {
				oqSubResult.setResultGrade(oqSubjectResult.getResultGrade());
			}
			if (oqSubjectResult.getResultSubGrade() != null) {
				oqSubResult.setResultSubGrade(oqSubjectResult.getResultSubGrade());
			}
			if (oqSubjectResult.getClearedIn() != null) {
				oqSubResult.setClearedIn(oqSubjectResult.getClearedIn());
			}
		}

		return ptSubjectResultRepo.save(oqSubResult);
	}

	@Override
	public EdossierPtResultPayload getCadetsByTermIdAndBattaionAndCompanyAndSubjectType(Long termId, String battalion,
			String company, String subjectType, String serviceId, Pageable pageable) {
		Integer totalRecords = 0;
		EdossierPtResultPayload edossierPtPayload = new EdossierPtResultPayload();
		List<EdossierPtResultFilterPayload> edossierPtFilterList = new ArrayList<EdossierPtResultFilterPayload>();
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
				EdossierPtResultFilterPayload ptFilterPayload = new EdossierPtResultFilterPayload();
				ptFilterPayload.setId(cad.getId());
				ptFilterPayload.setTermId(cad.getTerm());
				ptFilterPayload.setName(cad.getName());
				ptFilterPayload.setBattalian(cad.getBattalian());
				ptFilterPayload.setCompany(cad.getCompany());
				ptFilterPayload.setRank(cad.getCadetRank());
				ptFilterPayload.setServiceId(cad.getServiceId());
				ptFilterPayload.setCourse(cad.getCourse());
				ptFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				ptFilterPayload.setNationality(cad.getNationality());
				EdossierPtResult ptMarksResult = findByServiceIdAndTermIdAndSubjectType(cad.getServiceId(),
						cad.getTerm(), subjectType);
				if (ptMarksResult != null) {
					///// sort logic
					List<EdossierPtSubjectResult> subjectResultList = ptMarksResult.getEdossierPtSubjectResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					ptMarksResult.setEdossierPtSubjectResult(subjectResultList);
					///// till here
					ptFilterPayload.setEdossierPtResult(ptMarksResult);
				} else {
					EdossierPtResult ptMatResult = new EdossierPtResult();
					List<EdossierPtSubjectResult> edossierPtSubjectResult = new ArrayList<EdossierPtSubjectResult>();
					List<EdossierPtSubject> result = subService.getPtSubjectList(1, subjectType, cad.getTerm());
					Double totalMarks = 0.0;
					for (EdossierPtSubject subject : result) {
						EdossierPtSubjectResult subjectResult = new EdossierPtSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setSubjectName(subject.getSubject());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						edossierPtSubjectResult.add(subjectResult);
					}

					ptMatResult.setEdossierPtSubjectResult(edossierPtSubjectResult);
					ptMatResult.setObtainedMarks(totalMarks);
					ptMatResult.setServiceId(cad.getServiceId());
					ptMatResult.setTermId(cad.getTerm());
					ptMatResult.setStatus(1);
					ptFilterPayload.setEdossierPtResult(ptMatResult);
				}
				edossierPtFilterList.add(ptFilterPayload);
			}

			edossierPtPayload.setTotalRecords(totalRecords);
			edossierPtPayload.setEdossierPtResultFilterPayload(edossierPtFilterList);
			return edossierPtPayload;
		} else {
			return null;
		}
	}

	@Override
	public EdossierPtResultPayload getCadetsByTermIdAndBattaionAndCompanyAndSubjectTypeWithoutPagination(Long termId,
			String battalion, String company, String subjectType, String serviceId) {
		Integer totalRecords = 0;
		EdossierPtResultPayload edossierPtPayload = new EdossierPtResultPayload();
		List<EdossierPtResultFilterPayload> edossierPtFilterList = new ArrayList<EdossierPtResultFilterPayload>();
		List<Cadet> cadetList = null;
		if (termId != null) {
			if (battalion != null) {
				if (company != null) {
					if (serviceId != null) {
						cadetList = cadetRepo.findAllByTermAndBattalianAndCompanyAndStatusAndServiceIdLike(termId,
								battalion, company, 1, "%" + serviceId + "%");
						totalRecords = cadetList.size();
					} else {
						cadetList = cadetRepo.findAllByTermAndBattalianAndCompanyAndStatus(termId, battalion, company,
								1);
						totalRecords = cadetList.size();
					}

				} else {
					if (serviceId != null) {
						cadetList = cadetRepo.findAllByTermAndBattalianAndStatusAndServiceIdLike(termId, battalion, 1,
								"%" + serviceId + "%");
						totalRecords = cadetList.size();
					} else {
						cadetList = cadetRepo.findAllByTermAndBattalianAndStatus(termId, battalion, 1);
						totalRecords = cadetList.size();
					}

				}
			} else {
				if (serviceId != null) {
					cadetList = cadetRepo.findAllByTermAndStatusAndServiceIdLike(termId, 1, "%" + serviceId + "%");
					totalRecords = cadetList.size();
				} else {
					cadetList = cadetRepo.findAllByTermAndStatus(termId, 1);
					totalRecords = cadetList.size();
				}

			}
		} else {
			if (serviceId != null) {
				cadetList = cadetRepo.findByStatusAndServiceIdLike(1, "%" + serviceId + "%");
				totalRecords = cadetList.size();
			} else {
				cadetList = cadetRepo.findAllByStatus(1);
				totalRecords = cadetList.size();
			}

		}

		if (cadetList != null && cadetList.size() != 0) {
			for (Cadet cad : cadetList) {
				EdossierPtResultFilterPayload ptFilterPayload = new EdossierPtResultFilterPayload();
				ptFilterPayload.setId(cad.getId());
				ptFilterPayload.setTermId(cad.getTerm());
				ptFilterPayload.setName(cad.getName());
				ptFilterPayload.setBattalian(cad.getBattalian());
				ptFilterPayload.setCompany(cad.getCompany());
				ptFilterPayload.setRank(cad.getCadetRank());
				ptFilterPayload.setServiceId(cad.getServiceId());
				ptFilterPayload.setCourse(cad.getCourse());
				ptFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				ptFilterPayload.setNationality(cad.getNationality());
				EdossierPtResult ptMarksResult = findByServiceIdAndTermIdAndSubjectType(cad.getServiceId(),
						cad.getTerm(), subjectType);
				if (ptMarksResult != null) {
					///// sort logic
					List<EdossierPtSubjectResult> subjectResultList = ptMarksResult.getEdossierPtSubjectResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					ptMarksResult.setEdossierPtSubjectResult(subjectResultList);
					///// till here
					ptFilterPayload.setEdossierPtResult(ptMarksResult);
				} else {
					EdossierPtResult ptMatResult = new EdossierPtResult();
					List<EdossierPtSubjectResult> edossierPtSubjectResult = new ArrayList<EdossierPtSubjectResult>();
					List<EdossierPtSubject> result = subService.getPtSubjectList(1, subjectType, cad.getTerm());
					Double totalMarks = 0.0;
					for (EdossierPtSubject subject : result) {
						EdossierPtSubjectResult subjectResult = new EdossierPtSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setSubjectName(subject.getSubject());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						edossierPtSubjectResult.add(subjectResult);
					}

					ptMatResult.setEdossierPtSubjectResult(edossierPtSubjectResult);
					ptMatResult.setObtainedMarks(totalMarks);
					ptMatResult.setServiceId(cad.getServiceId());
					ptMatResult.setTermId(cad.getTerm());
					ptMatResult.setStatus(1);
					ptFilterPayload.setEdossierPtResult(ptMatResult);
				}
				edossierPtFilterList.add(ptFilterPayload);
			}

			edossierPtPayload.setTotalRecords(totalRecords);
			edossierPtPayload.setEdossierPtResultFilterPayload(edossierPtFilterList);
			return edossierPtPayload;
		} else {
			return null;
		}
	}

	@Override
	public EdossierPtResultPayload getCadetsBySearch(String serviceId, Long termId, String subjectType,
			Pageable pageable) {
		List<EdossierPtResultFilterPayload> edossierPtFilterList = new ArrayList<EdossierPtResultFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		EdossierPtResultPayload edossierPtPayload = new EdossierPtResultPayload();
		if (serviceId != null && !serviceId.trim().equals("")) {
			cadetList = cadetRepo.findAllByTermAndStatusAndServiceIdLike(termId, 1, "%" + serviceId + "%", pageable);
			totalRecords = cadetRepo.findAllByTermAndStatusAndServiceIdLike(termId, 1, "%" + serviceId + "%").size();
		} else {
			cadetList = cadetRepo.findAllByTermAndStatus(termId, 1, pageable);
			totalRecords = cadetRepo.findAllByTermAndStatus(termId, 1).size();
		}
		if (cadetList != null && cadetList.size() != 0) {
			for (Cadet cad : cadetList) {
				EdossierPtResultFilterPayload ptFilterPayload = new EdossierPtResultFilterPayload();
				ptFilterPayload.setId(cad.getId());
				ptFilterPayload.setTermId(cad.getTerm());
				ptFilterPayload.setName(cad.getName());
				ptFilterPayload.setBattalian(cad.getBattalian());
				ptFilterPayload.setCompany(cad.getCompany());
				ptFilterPayload.setRank(cad.getCadetRank());
				ptFilterPayload.setServiceId(cad.getServiceId());
				ptFilterPayload.setCourse(cad.getCourse());
				ptFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				ptFilterPayload.setNationality(cad.getNationality());
				EdossierPtResult ptMarksResult = findByServiceIdAndTermIdAndSubjectType(cad.getServiceId(),
						cad.getTerm(), subjectType);
				if (ptMarksResult != null) {
					///// sort logic
					List<EdossierPtSubjectResult> subjectResultList = ptMarksResult.getEdossierPtSubjectResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					ptMarksResult.setEdossierPtSubjectResult(subjectResultList);
					///// till here
					ptFilterPayload.setEdossierPtResult(ptMarksResult);
				} else {
					EdossierPtResult ptMatResult = new EdossierPtResult();
					List<EdossierPtSubjectResult> ptMarksSubjectResult = new ArrayList<EdossierPtSubjectResult>();
					List<EdossierPtSubject> result = subService.getPtSubjectList(1, subjectType, cad.getTerm());
					Double totalMarks = 0.0;
					for (EdossierPtSubject subject : result) {
						EdossierPtSubjectResult subjectResult = new EdossierPtSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setSubjectName(subject.getSubject());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						ptMarksSubjectResult.add(subjectResult);
					}

					ptMatResult.setEdossierPtSubjectResult(ptMarksSubjectResult);
					ptMatResult.setObtainedMarks(totalMarks);
					ptMatResult.setServiceId(cad.getServiceId());
					ptMatResult.setTermId(cad.getTerm());
					ptMatResult.setStatus(1);
					ptFilterPayload.setEdossierPtResult(ptMatResult);
				}
				edossierPtFilterList.add(ptFilterPayload);
			}
			edossierPtPayload.setTotalRecords(totalRecords);
			edossierPtPayload.setEdossierPtResultFilterPayload(edossierPtFilterList);
			return edossierPtPayload;
		} else {
			return null;
		}
	}

	@Override
	public EdossierPtResultPayload getCadetsBySearchWithoutPagination(String serviceId, Long termId,
			String subjectType) {
		List<EdossierPtResultFilterPayload> edossierPtFilterList = new ArrayList<EdossierPtResultFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		EdossierPtResultPayload edossierPtPayload = new EdossierPtResultPayload();
		if (serviceId != null && !serviceId.trim().equals("")) {
			cadetList = cadetRepo.findAllByTermAndStatusAndServiceIdLike(termId, 1, "%" + serviceId + "%");
			totalRecords = cadetList.size();
		} else {
			cadetList = cadetRepo.findAllByTermAndStatus(termId, 1);
			totalRecords = cadetList.size();
		}
		if (cadetList != null && cadetList.size() != 0) {
			for (Cadet cad : cadetList) {
				EdossierPtResultFilterPayload ptFilterPayload = new EdossierPtResultFilterPayload();
				ptFilterPayload.setId(cad.getId());
				ptFilterPayload.setTermId(cad.getTerm());
				ptFilterPayload.setName(cad.getName());
				ptFilterPayload.setBattalian(cad.getBattalian());
				ptFilterPayload.setCompany(cad.getCompany());
				ptFilterPayload.setRank(cad.getCadetRank());
				ptFilterPayload.setServiceId(cad.getServiceId());
				ptFilterPayload.setCourse(cad.getCourse());
				ptFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				ptFilterPayload.setNationality(cad.getNationality());
				EdossierPtResult ptMarksResult = findByServiceIdAndTermIdAndSubjectType(cad.getServiceId(),
						cad.getTerm(), subjectType);
				if (ptMarksResult != null) {
					///// sort logic
					List<EdossierPtSubjectResult> subjectResultList = ptMarksResult.getEdossierPtSubjectResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					ptMarksResult.setEdossierPtSubjectResult(subjectResultList);
					///// till here
					ptFilterPayload.setEdossierPtResult(ptMarksResult);
				} else {
					EdossierPtResult ptMatResult = new EdossierPtResult();
					List<EdossierPtSubjectResult> ptMarksSubjectResult = new ArrayList<EdossierPtSubjectResult>();
					List<EdossierPtSubject> result = subService.getPtSubjectList(1, subjectType, cad.getTerm());
					Double totalMarks = 0.0;
					for (EdossierPtSubject subject : result) {
						EdossierPtSubjectResult subjectResult = new EdossierPtSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setSubjectName(subject.getSubject());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						ptMarksSubjectResult.add(subjectResult);
					}

					ptMatResult.setEdossierPtSubjectResult(ptMarksSubjectResult);
					ptMatResult.setObtainedMarks(totalMarks);
					ptMatResult.setServiceId(cad.getServiceId());
					ptMatResult.setTermId(cad.getTerm());
					ptMatResult.setStatus(1);
					ptFilterPayload.setEdossierPtResult(ptMatResult);
				}
				edossierPtFilterList.add(ptFilterPayload);
			}
			edossierPtPayload.setTotalRecords(totalRecords);
			edossierPtPayload.setEdossierPtResultFilterPayload(edossierPtFilterList);
			return edossierPtPayload;
		} else {
			return null;
		}
	}

	@Override
	public String updateBulkEdossierPtResult(List<EdossierPtResultFilterPayload> edossierPtResultPayloadList) {
		// TODO Auto-generated method stub
		String result = "failed";
		int size = edossierPtResultPayloadList.size();
		if (size > 0) {
			for (EdossierPtResultFilterPayload edossierPtPayload : edossierPtResultPayloadList) {
				EdossierPtResult edossierPtResult = edossierPtPayload.getEdossierPtResult();
				if (edossierPtResult != null && edossierPtResult.getId() != null && edossierPtResult.getId() != 0) {
					// update logic
					updateEdossierPtResult(edossierPtResult);
				} else if (edossierPtResult != null) {
					// add logic
					List<EdossierPtSubjectResult> ptSubResult = edossierPtResult.getEdossierPtSubjectResult();
					if (ptSubResult != null) {
						for (EdossierPtSubjectResult ptSubRslt : ptSubResult) {
							createSubResult(ptSubRslt);
						}
					}
					createEdossierPtResult(edossierPtResult);
				}
			}
			result = "success";
		}
		return result;

	}

	@Override
	public List<EdossierPtResult> findByServiceIdAndSubjectType(String serviceId, String subjectType) {
		List<EdossierPtResult> ptResultList = null;
		if (subjectType == null) {
			ptResultList = ptResultRepo.findByServiceIdOrderByTermId(serviceId);
		} else {
			ptResultList = ptResultRepo.findByServiceIdAndSubjectTypeOrderByTermId(serviceId, subjectType);
		}

		if (ptResultList != null && ptResultList.size() != 0) {
			for (EdossierPtResult ptMatResult : ptResultList) {
				List<EdossierPtSubjectResult> list = ptMatResult.getEdossierPtSubjectResult();
				for (EdossierPtSubjectResult ptMatSubReslt : list) {
					EdossierPtSubject ptSub = subService.getSubjectById(ptMatSubReslt.getSubjectId());
					ptMatSubReslt.setSubjectName(ptSub.getSubject());
				}
				// return ptResultList;
			}
			return ptResultList;
		}
		return null;
	}
}
