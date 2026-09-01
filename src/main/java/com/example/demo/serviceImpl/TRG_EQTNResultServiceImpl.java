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
import com.example.demo.model.TRG_EQTNResult;
import com.example.demo.model.TRG_EQTNSubject;
import com.example.demo.model.TRG_EQTNSubjectResult;
import com.example.demo.payload.EqtnFilterPayload;
import com.example.demo.payload.EqtnPayload;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.TRG_EQTNResultRepo;
import com.example.demo.service.TRG_EQTNResultService;
import com.example.demo.service.TRG_EQTNSubjectResultService;
import com.example.demo.service.TRG_EQTNSubjectService;

@Service
public class TRG_EQTNResultServiceImpl implements TRG_EQTNResultService {
	@Autowired
	private TRG_EQTNResultRepo tRG_EQTNResultRepo;

	@Autowired
	private TRG_EQTNSubjectService tRG_EQTNSubjectService;

	@Autowired
	private TRG_EQTNSubjectResultService tRG_EQTNSubjectResultService;

	@Autowired
	AdminCadetRepo cadetRepo;

	@Override
	public TRG_EQTNResult createTRG_EQTNResult(TRG_EQTNResult tRG_EQTNResult) {
		TRG_EQTNResult saveTRG_EQTNResult = tRG_EQTNResultRepo.save(tRG_EQTNResult);
		return saveTRG_EQTNResult;
	}

	@Override
	public TRG_EQTNResult findByServiceIdAndTermId(String serviceId, Long termId) {
		// TODO Auto-generated method stub
		Optional<TRG_EQTNResult> tRG_EQTNResult = tRG_EQTNResultRepo.findByServiceIdAndTermId(serviceId, termId);
		if (tRG_EQTNResult.isPresent()) {

			TRG_EQTNResult leaderMatResult = tRG_EQTNResult.get();

			List<TRG_EQTNSubjectResult> list = leaderMatResult.getTrgEQTNSubResult();

			for (TRG_EQTNSubjectResult leadetMatSubReslt : list) {
				TRG_EQTNSubject leaderSub = tRG_EQTNSubjectService.getSubjectById(leadetMatSubReslt.getSubjectId());
				leadetMatSubReslt.setSubjectName(leaderSub.getSubjectName());
			}
			return leaderMatResult;
		}
		return null;
	}

	@Override
	public TRG_EQTNResult updateTRG_EQTNResult(TRG_EQTNResult tRG_EQTNResult) {
		// TODO Auto-generated method stub
		TRG_EQTNResult tRG_EQTNRslt = null;
		Optional<TRG_EQTNResult> tRG_EQTNRsltData = tRG_EQTNResultRepo.findById(tRG_EQTNResult.getId());
		if (tRG_EQTNRsltData.isPresent()) {

			tRG_EQTNRslt = tRG_EQTNRsltData.get();
			if (tRG_EQTNRslt != null) {

				tRG_EQTNRslt.setObtainedMarks(tRG_EQTNResult.getObtainedMarks());
				tRG_EQTNRslt.setRemarks(tRG_EQTNResult.getRemarks());
				tRG_EQTNRslt.setTotalMarks(tRG_EQTNResult.getTotalMarks());
				tRG_EQTNRslt.setUpdatedAt(tRG_EQTNResult.getUpdatedAt());

				List<TRG_EQTNSubjectResult> SubListTemp = new ArrayList<TRG_EQTNSubjectResult>();
				List<TRG_EQTNSubjectResult> SubList = tRG_EQTNResult.getTrgEQTNSubResult();
				for (TRG_EQTNSubjectResult subject : SubList) {

					TRG_EQTNSubjectResult tRG_EQTNSubjectResult = tRG_EQTNSubjectResultService
							.getSubResultById(subject.getId());
					tRG_EQTNSubjectResult.setObtainedMarks(subject.getObtainedMarks());
					tRG_EQTNSubjectResult.setUpdatedAt(new Date());
					tRG_EQTNSubjectResult.setRemarks(subject.getRemarks());
					SubListTemp.add(tRG_EQTNSubjectResult);
					tRG_EQTNSubjectResultService.updateSubResult(tRG_EQTNSubjectResult);
				}
				tRG_EQTNRslt.setTrgEQTNSubResult(SubListTemp);
			}
			tRG_EQTNRslt = tRG_EQTNResultRepo.save(tRG_EQTNRslt);
		}
		return tRG_EQTNRslt;

	}

	@Override
	public List<TRG_EQTNResult> findByServiceId(String serviceId) {
		List<TRG_EQTNResult> tRG_EQTNResultList = tRG_EQTNResultRepo.findByServiceIdOrderByTermId(serviceId);
		if (tRG_EQTNResultList != null && tRG_EQTNResultList.size() != 0) {
			for (TRG_EQTNResult leaderMatResult : tRG_EQTNResultList) {
				List<TRG_EQTNSubjectResult> list = leaderMatResult.getTrgEQTNSubResult();
				for (TRG_EQTNSubjectResult leadetMatSubReslt : list) {
					TRG_EQTNSubject leaderSub = tRG_EQTNSubjectService.getSubjectById(leadetMatSubReslt.getSubjectId());
					leadetMatSubReslt.setSubjectName(leaderSub.getSubjectName());
				}
				// return tRG_EQTNResultList;
			}
			return tRG_EQTNResultList;
		}
		return null;
	}

	@Override
	public EqtnPayload getCadetsByTermIdAndBattaionAndCompany(Long termId, String battalion, String company,
			String serviceId, Pageable pageable) {
		Integer totalRecords = 0;
		EqtnPayload eqtnPayload = new EqtnPayload();
		List<EqtnFilterPayload> eqtnFilterList = new ArrayList<EqtnFilterPayload>();
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
				EqtnFilterPayload qtnFilterPayload = new EqtnFilterPayload();
				qtnFilterPayload.setId(cad.getId());
				qtnFilterPayload.setTermId(cad.getTerm());
				qtnFilterPayload.setName(cad.getName());
				qtnFilterPayload.setBattalian(cad.getBattalian());
				qtnFilterPayload.setCompany(cad.getCompany());
				qtnFilterPayload.setRank(cad.getCadetRank());
				qtnFilterPayload.setServiceId(cad.getServiceId());
				qtnFilterPayload.setCourse(cad.getCourse());
				qtnFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				qtnFilterPayload.setNationality(cad.getNationality());
				TRG_EQTNResult eQTNResult = findByServiceIdAndTermId(cad.getServiceId(),
						(long) Integer.parseInt(cad.getTerm().toString()));
				if (eQTNResult != null) {
					///// sort logic
					List<TRG_EQTNSubjectResult> subjectResultList = eQTNResult.getTrgEQTNSubResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					eQTNResult.setTrgEQTNSubResult(subjectResultList);
					///// till here
					qtnFilterPayload.setTrgEQTNResult(eQTNResult);
				} else {
					TRG_EQTNResult trgMatResult = new TRG_EQTNResult();
					List<TRG_EQTNSubjectResult> subjectResultList = new ArrayList<TRG_EQTNSubjectResult>();
					List<TRG_EQTNSubject> result = tRG_EQTNSubjectService.getByStatusAndTermId(1, cad.getTerm());
					Integer totalMarks = 0;
					for (TRG_EQTNSubject subject : result) {
						TRG_EQTNSubjectResult subjectResult = new TRG_EQTNSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarks(subject.getTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						subjectResultList.add(subjectResult);
					}

					// trgMatResult.setLeadershipSubjectResult(leadershipSubjectResult);
					trgMatResult.setTrgEQTNSubResult(subjectResultList);
					trgMatResult.setTotalMarks(totalMarks);
					trgMatResult.setServiceId(cad.getServiceId());
					trgMatResult.setTermId(cad.getTerm());
					trgMatResult.setStatus(1);
					qtnFilterPayload.setTrgEQTNResult(trgMatResult);
				}
				eqtnFilterList.add(qtnFilterPayload);
			}
			eqtnPayload.setTotalRecords(totalRecords);
			eqtnPayload.setEqtnFilterPayload(eqtnFilterList);
			return eqtnPayload;
		} else {
			return null;
		}
	}

	@Override
	public String updateBulkTRG_EQTNResult(List<EqtnFilterPayload> eqtnPayloadList) {
		String result = "failed";
		int size = eqtnPayloadList.size();
		if (size > 0) {
			for (EqtnFilterPayload qtnPayload : eqtnPayloadList) {
				TRG_EQTNResult tRG_EQTNResult = qtnPayload.getTrgEQTNResult();
				if (tRG_EQTNResult != null && tRG_EQTNResult.getId() != null && tRG_EQTNResult.getId() != 0) {
					updateTRG_EQTNResult(tRG_EQTNResult);
				} else if (tRG_EQTNResult != null) {
					List<TRG_EQTNSubjectResult> tRG_EQTNSubjectResult = tRG_EQTNResult.getTrgEQTNSubResult();
					if (tRG_EQTNSubjectResult != null) {
						for (TRG_EQTNSubjectResult EQTNSubRslt : tRG_EQTNSubjectResult) {
							tRG_EQTNSubjectResultService.createSubResult(EQTNSubRslt);
						}
					}
					if (tRG_EQTNResult.getObtainedMarks() != null) {
						tRG_EQTNResult.setCreatedAt(new Date());
						createTRG_EQTNResult(tRG_EQTNResult);
					}
				}
			}
			result = "success";
		}
		return result;
	}

	@Override
	public EqtnPayload getCadetsBySearch(Long termId, String serviceId, Pageable pageable) {
		EqtnPayload eqtnPayload = null;
		List<EqtnFilterPayload> eqtnFilterList = new ArrayList<EqtnFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		if (termId == null || termId == 0) {
			return eqtnPayload;
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
			eqtnPayload = new EqtnPayload();
			for (Cadet cad : cadetList) {
				EqtnFilterPayload qtnFilterPayload = new EqtnFilterPayload();
				qtnFilterPayload.setId(cad.getId());
				qtnFilterPayload.setTermId(cad.getTerm());
				qtnFilterPayload.setName(cad.getName());
				qtnFilterPayload.setBattalian(cad.getBattalian());
				qtnFilterPayload.setCompany(cad.getCompany());
				qtnFilterPayload.setRank(cad.getCadetRank());
				qtnFilterPayload.setServiceId(cad.getServiceId());
				qtnFilterPayload.setCourse(cad.getCourse());
				qtnFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				qtnFilterPayload.setNationality(cad.getNationality());
				// eqtnFilterList.add(qtnFilterPayload);
				TRG_EQTNResult eQTNResult = findByServiceIdAndTermId(cad.getServiceId(),
						(long) Integer.parseInt(cad.getTerm().toString()));
				if (eQTNResult != null) {
					///// sort logic
					List<TRG_EQTNSubjectResult> subjectResultList = eQTNResult.getTrgEQTNSubResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					eQTNResult.setTrgEQTNSubResult(subjectResultList);
					///// till here
					qtnFilterPayload.setTrgEQTNResult(eQTNResult);
				} else {
					TRG_EQTNResult trgMatResult = new TRG_EQTNResult();
					List<TRG_EQTNSubjectResult> subjectResultList = new ArrayList<TRG_EQTNSubjectResult>();
					List<TRG_EQTNSubject> result = tRG_EQTNSubjectService.getByStatusAndTermId(1, cad.getTerm());
					Integer totalMarks = 0;
					for (TRG_EQTNSubject subject : result) {
						TRG_EQTNSubjectResult subjectResult = new TRG_EQTNSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarks(subject.getTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						subjectResultList.add(subjectResult);
					}

					// trgMatResult.setLeadershipSubjectResult(leadershipSubjectResult);
					trgMatResult.setTrgEQTNSubResult(subjectResultList);
					trgMatResult.setTotalMarks(totalMarks);
					trgMatResult.setServiceId(cad.getServiceId());
					trgMatResult.setTermId(cad.getTerm());
					trgMatResult.setStatus(1);
					qtnFilterPayload.setTrgEQTNResult(trgMatResult);
				}
				eqtnFilterList.add(qtnFilterPayload);
			}
			eqtnPayload.setTotalRecords(totalRecords);
			eqtnPayload.setEqtnFilterPayload(eqtnFilterList);
			return eqtnPayload;
		} else {
			return null;
		}
	}
}
