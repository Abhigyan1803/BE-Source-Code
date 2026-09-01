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
import com.example.demo.model.DRILLResult;
import com.example.demo.model.DRILLSubject;
import com.example.demo.model.DRILLSubjectResult;
import com.example.demo.payload.DrillFilterPayload;
import com.example.demo.payload.DrillPayload;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.DRILLResultRepo;
import com.example.demo.repository.DRILLSubjectRepo;
import com.example.demo.repository.DRILLSubjectResultRepo;
import com.example.demo.service.DRILLResultService;
import com.example.demo.service.DRILLSubjectService;

@Service
public class DRILLResultServiceImpl implements DRILLResultService {

	@Autowired
	public DRILLResultRepo dRILLResultRepo;

	@Autowired
	public DRILLSubjectRepo dRILLSubjectRepo;

	@Autowired
	public DRILLSubjectResultRepo dRILLSubjectResultRepo;
	@Autowired
	public DRILLSubjectService drillSubjectService;
	@Autowired
	AdminCadetRepo cadetRepo;

	@Override
	public DRILLResult createDrillResult(DRILLResult dRILLResult) {
		// TODO Auto-generated method stub
		Long term = dRILLResult.getTermId();
		if (term == 3) {
			dRILLResult.setDrillType("DAT");
		} else {
			dRILLResult.setDrillType("DST");
		}
		DRILLResult drillResult = dRILLResultRepo.save(dRILLResult);
		return drillResult;
	}

	@Override
	public DRILLResult getDrillResult(String serviceId, Long termId) {
		// TODO Auto-generated method stub
		Optional<DRILLResult> drillResult = dRILLResultRepo.findByServiceIdAndTermId(serviceId, termId);
		DRILLResult drillRslt = null;
		if (drillResult.isPresent()) {
			drillRslt = drillResult.get();
			List<DRILLSubjectResult> Sublist = new ArrayList<DRILLSubjectResult>();
			List<DRILLSubjectResult> drillSubRsltList = drillResult.get().getdRILLSubjectResult();
			for (DRILLSubjectResult drillSubRslt : drillSubRsltList) {
				Optional<DRILLSubject> dRILLSub = dRILLSubjectRepo.findById(drillSubRslt.getSubjectId());
				if (dRILLSub.isPresent()) {
					drillSubRslt.setSubjectName(dRILLSub.get().getSubjectName());
				}
				Sublist.add(drillSubRslt);
			}
			drillRslt.setdRILLSubjectResult(Sublist);
		}
		return drillRslt;
	}

	@Override
	public DRILLResult updateDrillResult(DRILLResult drillResult) {
// TODO Auto-generated method stub
		DRILLResult drillResultsave = null;
		Optional<DRILLResult> drillResultDate = dRILLResultRepo.findById(drillResult.getId());
		if (drillResultDate.isPresent()) {
			drillResultsave = drillResultDate.get();
			if (drillResultsave != null) {
				// if (drillResultsave.getObtainedMarks() == null
				// || (drillResultsave.getObtainedMarks() < drillResult.getObtainedMarks())) {
				// drillResultsave.setObtainedMarks(drillResult.getObtainedMarks());
				// drillResultsave.setClearedIn(drillResult.getClearedIn());
				// }
				drillResultsave.setObtainedMarks(drillResult.getObtainedMarks());
				drillResultsave.setLastAttemptType(drillResult.getLastAttemptType());
				if (drillResult.getClearedIn() != null) {
					drillResultsave.setClearedIn(drillResult.getClearedIn());
				}
				if (drillResult.getTotalMarks() != null) {
					drillResultsave.setTotalMarks(drillResult.getTotalMarks());
				}
				List<DRILLSubjectResult> drillSubjectRslt = new ArrayList<DRILLSubjectResult>();
				List<DRILLSubjectResult> drillSubRsltList = drillResult.getdRILLSubjectResult();
				for (DRILLSubjectResult drillSubRslt : drillSubRsltList) {
					Optional<DRILLSubjectResult> dRILLSub = dRILLSubjectResultRepo.findById(drillSubRslt.getId());
					if (dRILLSub.isPresent()) {
						dRILLSub.get().setLastAttemptType(drillSubRslt.getLastAttemptType());
						if (drillSubRslt.getClearedIn() != null) {
							dRILLSub.get().setClearedIn(drillSubRslt.getClearedIn());
						}
						if (drillSubRslt.getC1ObtainedMarks() != null) {
							dRILLSub.get().setC1ObtainedMarks(drillSubRslt.getC1ObtainedMarks());
						}
						if (drillSubRslt.getC2ObtainedMarks() != null) {
							dRILLSub.get().setC2ObtainedMarks(drillSubRslt.getC2ObtainedMarks());
						}
						if (drillSubRslt.getM1ObtainedMarks() != null) {
							dRILLSub.get().setM1ObtainedMarks(drillSubRslt.getM1ObtainedMarks());
						}
						if (drillSubRslt.getM2ObtainedMarks() != null) {
							dRILLSub.get().setM2ObtainedMarks(drillSubRslt.getM2ObtainedMarks());
						}
						if (drillSubRslt.getTotalMarks() != null) {
							dRILLSub.get().setTotalMarks(drillSubRslt.getTotalMarks());
						}
						dRILLSub.get().setUpdatedAt(new Date());
						drillSubjectRslt.add(dRILLSub.get());
					}

					dRILLSubjectResultRepo.save(dRILLSub.get());
				}

				drillResultsave.setdRILLSubjectResult(drillSubjectRslt);
				drillResultsave.setUpdatedAt(new Date());
				drillResultsave = dRILLResultRepo.save(drillResultsave);
			}
		}
		return drillResultsave;
	}

	@Override
	public List<DRILLResult> getAllDrillResult(String serviceId) {
		// TODO Auto-generated method stub
		List<DRILLResult> getDrillResult = dRILLResultRepo.findByServiceIdOrderByTermId(serviceId);
		if (getDrillResult != null && getDrillResult.size() != 0) {
			for (DRILLResult drillMark : getDrillResult) {
				Double m1ObtainedMarks = 0.0;
				Double m2ObtainedMarks = 0.0;
				Double c1ObtainedMarks = 0.0;
				Double c2ObtainedMarks = 0.0;
				List<DRILLSubjectResult> drillSub = drillMark.getdRILLSubjectResult();
				for (DRILLSubjectResult subjectResult : drillSub) {
					DRILLSubject drillSubjectDetails = drillSubjectService.getSubjectById(subjectResult.getSubjectId())
							.get();
					subjectResult.setSubjectName(drillSubjectDetails.getSubjectName());
					m1ObtainedMarks += subjectResult.getM1ObtainedMarks() == null ? 0
							: subjectResult.getM1ObtainedMarks();
					m2ObtainedMarks += subjectResult.getM2ObtainedMarks() == null ? 0
							: subjectResult.getM2ObtainedMarks();
					c1ObtainedMarks += subjectResult.getC1ObtainedMarks() == null ? 0
							: subjectResult.getC1ObtainedMarks();
					c2ObtainedMarks += subjectResult.getC2ObtainedMarks() == null ? 0
							: subjectResult.getC2ObtainedMarks();
				}
				drillMark.setM1ObtainedMarks(m1ObtainedMarks);
				drillMark.setM2ObtainedMarks(m2ObtainedMarks);
				drillMark.setC1ObtainedMarks(c1ObtainedMarks);
				drillMark.setC2ObtainedMarks(c2ObtainedMarks);

			}
			return getDrillResult;
		} else {
			return null;
		}

	}

	@Override
	public DrillPayload getCadetsByTermIdAndBattaionAndCompanyAndEntryTypeId(Long termId, String battalion,
			String company, String serviceId, Pageable pageable) {
		// TODO Auto-generated method stub
		Integer totalRecords = 0;
		DrillPayload drillPayload = new DrillPayload();
		List<DrillFilterPayload> drillFilterList = new ArrayList<DrillFilterPayload>();
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
				DrillFilterPayload drillFilterPayload = new DrillFilterPayload();
				drillFilterPayload.setId(cad.getId());
				drillFilterPayload.setTermId(cad.getTerm());
				drillFilterPayload.setName(cad.getName());
				drillFilterPayload.setBattalian(cad.getBattalian());
				drillFilterPayload.setCompany(cad.getCompany());
				drillFilterPayload.setRank(cad.getCadetRank());
				drillFilterPayload.setServiceId(cad.getServiceId());
				drillFilterPayload.setCourse(cad.getCourse());
				drillFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				drillFilterPayload.setNationality(cad.getNationality());
				DRILLResult dRILLResult = getDrillResult(cad.getServiceId(), cad.getTerm());
				if (dRILLResult != null) {
					Double m1ObtainedMarks = 0.0;
					Double m2ObtainedMarks = 0.0;
					Double c1ObtainedMarks = 0.0;
					Double c2ObtainedMarks = 0.0;
					for (DRILLSubjectResult subjectResult : dRILLResult.getdRILLSubjectResult()) {
						m1ObtainedMarks += subjectResult.getM1ObtainedMarks() == null ? 0
								: subjectResult.getM1ObtainedMarks();
						m2ObtainedMarks += subjectResult.getM2ObtainedMarks() == null ? 0
								: subjectResult.getM2ObtainedMarks();
						c1ObtainedMarks += subjectResult.getC1ObtainedMarks() == null ? 0
								: subjectResult.getC1ObtainedMarks();
						c2ObtainedMarks += subjectResult.getC2ObtainedMarks() == null ? 0
								: subjectResult.getC2ObtainedMarks();
					}
					dRILLResult.setM1ObtainedMarks(m1ObtainedMarks);
					dRILLResult.setM2ObtainedMarks(m2ObtainedMarks);
					dRILLResult.setC1ObtainedMarks(c1ObtainedMarks);
					dRILLResult.setC2ObtainedMarks(c2ObtainedMarks);
					///// sort logic
					List<DRILLSubjectResult> subjectResultList = dRILLResult.getdRILLSubjectResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					dRILLResult.setdRILLSubjectResult(subjectResultList);
					///// till here
					drillFilterPayload.setdRILLResult(dRILLResult);
				} else {
					DRILLResult drillMatResult = new DRILLResult();
					List<DRILLSubjectResult> drillSubjectResult = new ArrayList<DRILLSubjectResult>();
					List<DRILLSubject> result = drillSubjectService.getAllSubjectByTermId(termId);
					Integer totalMarks = 0;
					// Double m1ObtainedMarks=0.0;
					// Double m2ObtainedMarks=0.0;
					// Double c1ObtainedMarks=0.0;
					// Double c2ObtainedMarks=0.0;
					// Integer totalMarksPlCdr=0;
					// Integer totalMarksCoyCdr=0;
					// Integer totalMarksBnCdr=0;
					for (DRILLSubject subject : result) {
						DRILLSubjectResult subjectResult = new DRILLSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						// m1ObtainedMarks = m1ObtainedMarks + subject.getM1ObtainedMarks();
						// m2ObtainedMarks = m2ObtainedMarks + subject.getM2ObtainedMarks();
						// c1ObtainedMarks = c1ObtainedMarks + subject.getC1ObtainedMarks();
						// c2ObtainedMarks = c2ObtainedMarks + subject.getC2ObtainedMarks();
						// totalMarksCoyCdr = totalMarksCoyCdr + subject.getTotalMarksCoyCdr();
						// totalMarksPlCdr = totalMarksPlCdr + subject.getTotalMarksBnCdr();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarks(subject.getTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						drillSubjectResult.add(subjectResult);
					}

					drillMatResult.setdRILLSubjectResult(drillSubjectResult);
					drillMatResult.setTotalMarks(totalMarks);
					drillMatResult.setServiceId(cad.getServiceId());
					drillMatResult.setTermId(cad.getTerm());
					drillMatResult.setStatus(1);

					drillFilterPayload.setdRILLResult(drillMatResult);
				}
				drillFilterList.add(drillFilterPayload);
			}

			drillPayload.setTotalRecords(totalRecords);
			drillPayload.setDrillFilterPayload(drillFilterList);
			return drillPayload;
		} else {
			return null;
		}
	}

	@Override
	public String updateDrillResult(List<DrillFilterPayload> drillPayloadList) {
		// TODO Auto-generated method stub
		String result = "failed";
		int size = drillPayloadList.size();
		if (size > 0) {
			for (DrillFilterPayload drillPayload : drillPayloadList) {
				DRILLResult dRILLResult = drillPayload.getdRILLResult();
				if (dRILLResult != null && dRILLResult.getId() != null && dRILLResult.getId() != 0) {
					// update logic
					updateDrillResult(dRILLResult);
				} else if (dRILLResult != null) {
					// add logic
					if (dRILLResult.getObtainedMarks() != null) {
						dRILLResult.setCreatedAt(new Date());
						createDrillResult(dRILLResult);
					}
				}
			}
			result = "success";
		}
		return result;
	}

	@Override
	public DrillPayload getCadetsBySearch(String serviceId, Long termId, Pageable pageable) {
		DrillPayload drillPayload = null;
		List<DrillFilterPayload> drillFilterList = new ArrayList<DrillFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		if (termId == null || termId == 0) {
			return drillPayload;
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
			drillPayload = new DrillPayload();
			for (Cadet cad : cadetList) {
				DrillFilterPayload drillFilterPayload = new DrillFilterPayload();
				drillFilterPayload.setId(cad.getId());
				drillFilterPayload.setTermId(cad.getTerm());
				drillFilterPayload.setName(cad.getName());
				drillFilterPayload.setBattalian(cad.getBattalian());
				drillFilterPayload.setCompany(cad.getCompany());
				drillFilterPayload.setRank(cad.getCadetRank());
				drillFilterPayload.setServiceId(cad.getServiceId());
				drillFilterPayload.setCourse(cad.getCourse());
				drillFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				drillFilterPayload.setNationality(cad.getNationality());
				DRILLResult dRILLResult = getDrillResult(cad.getServiceId(), cad.getTerm());
				if (dRILLResult != null) {
					Double m1ObtainedMarks = 0.0;
					Double m2ObtainedMarks = 0.0;
					Double c1ObtainedMarks = 0.0;
					Double c2ObtainedMarks = 0.0;
					for (DRILLSubjectResult subjectResult : dRILLResult.getdRILLSubjectResult()) {
						m1ObtainedMarks += subjectResult.getM1ObtainedMarks() == null ? 0
								: subjectResult.getM1ObtainedMarks();
						m2ObtainedMarks += subjectResult.getM2ObtainedMarks() == null ? 0
								: subjectResult.getM2ObtainedMarks();
						c1ObtainedMarks += subjectResult.getC1ObtainedMarks() == null ? 0
								: subjectResult.getC1ObtainedMarks();
						c2ObtainedMarks += subjectResult.getC2ObtainedMarks() == null ? 0
								: subjectResult.getC2ObtainedMarks();
					}
					dRILLResult.setM1ObtainedMarks(m1ObtainedMarks);
					dRILLResult.setM2ObtainedMarks(m2ObtainedMarks);
					dRILLResult.setC1ObtainedMarks(c1ObtainedMarks);
					dRILLResult.setC2ObtainedMarks(c2ObtainedMarks);
					///// sort logic
					List<DRILLSubjectResult> subjectResultList = dRILLResult.getdRILLSubjectResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					dRILLResult.setdRILLSubjectResult(subjectResultList);
					///// till here
					drillFilterPayload.setdRILLResult(dRILLResult);
				} else {
					DRILLResult drillMatResult = new DRILLResult();
					List<DRILLSubjectResult> drillSubjectResult = new ArrayList<DRILLSubjectResult>();
					List<DRILLSubject> result = drillSubjectService.getAllSubjectByStatus(1);
					Double totalMarks = 0.0;
					for (DRILLSubject subject : result) {
						DRILLSubjectResult subjectResult = new DRILLSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarks(subject.getTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						drillSubjectResult.add(subjectResult);
					}

					drillMatResult.setdRILLSubjectResult(drillSubjectResult);
					drillMatResult.setObtainedMarks(totalMarks);

					drillFilterPayload.setdRILLResult(drillMatResult);
				}
				drillFilterList.add(drillFilterPayload);
			}
			drillPayload.setTotalRecords(totalRecords);
			drillPayload.setDrillFilterPayload(drillFilterList);
			return drillPayload;
		} else {
			return null;
		}
	}

}
