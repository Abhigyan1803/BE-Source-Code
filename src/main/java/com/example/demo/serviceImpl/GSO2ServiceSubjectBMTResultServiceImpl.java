package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cadet;
import com.example.demo.model.GSO2ServiceSubjectBMTResult;
import com.example.demo.model.RunbackRouteMr;
import com.example.demo.model.ServiceBmt2Result;
import com.example.demo.payload.BmtFilterPayload;
import com.example.demo.payload.BmtPayload;
import com.example.demo.payload.EdserviceSubPayload;
import com.example.demo.payload.ServiceSubTermPayload;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.GSO2ServiceSubjectBMTResultRepo;
import com.example.demo.repository.RunbackRouteMrRepo;
import com.example.demo.repository.ServiceBmt2ResultRepository;
import com.example.demo.service.AdminBattalionService;
import com.example.demo.service.GSO2ServiceSubjectBMTResultService;

@Service
public class GSO2ServiceSubjectBMTResultServiceImpl implements GSO2ServiceSubjectBMTResultService {
	@Autowired
	public GSO2ServiceSubjectBMTResultRepo repo;

	@Autowired
	AdminCadetRepo cadetRepo;

	@Autowired
	RunbackRouteMrRepo runbackRouteMrRepo;

	@Autowired
	private ServiceBmt2ResultRepository serviceBmt2ResultRepository;

	@Autowired
	AdminBattalionService battalionService;

	@Override
	public GSO2ServiceSubjectBMTResult createGSO2ServiceSubjectBMTResult(
			GSO2ServiceSubjectBMTResult gSO2ServiceSubjectBMTResult) {
		// TODO Auto-generated method stub

		gSO2ServiceSubjectBMTResult.setCreatedAt(new Date());

		return repo.save(gSO2ServiceSubjectBMTResult);
	}

	@Override
	public GSO2ServiceSubjectBMTResult getByid(Long id) {
		// TODO Auto-generated method stub
		Optional<GSO2ServiceSubjectBMTResult> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public GSO2ServiceSubjectBMTResult getGSO2ServiceSubjectBMTResult(Long termId, String subjectType,
			String assesmentTermType, Integer status, String serviceId) {
		// TODO Auto-generated method stub

		GSO2ServiceSubjectBMTResult result = repo
				.findByTermIdAndSubjectTypeAndAssesmentTermTypeAndStatusAndServiceIdOrderByIdDesc(termId, subjectType,
						assesmentTermType, status, serviceId);
		if (result == null) {
			return null;
		}
		return result;
	}

	@Override
	public GSO2ServiceSubjectBMTResult updateGSO2ServiceSubjectBMTResult(
			GSO2ServiceSubjectBMTResult gSO2ServiceSubjectBMTResult) {
		// TODO Auto-generated method stub
		GSO2ServiceSubjectBMTResult result = null;
		if (gSO2ServiceSubjectBMTResult != null && gSO2ServiceSubjectBMTResult.getId() != null
				&& gSO2ServiceSubjectBMTResult.getId() != 0) {

			Optional<GSO2ServiceSubjectBMTResult> data = repo.findById(gSO2ServiceSubjectBMTResult.getId());
			if (data.isPresent()) {
				result = data.get();
				if (gSO2ServiceSubjectBMTResult.getObtainedMarks() != null) {
					result.setObtainedMarks(gSO2ServiceSubjectBMTResult.getObtainedMarks());
				}
				result.setUpdatedAt(new Date());
			}
			result = repo.save(result);
		}
		return result;
	}

	@Override
	public List<GSO2ServiceSubjectBMTResult> getGSO2ServiceSubjectBMTResult(String serviceId) {
		List<GSO2ServiceSubjectBMTResult> result = repo.findByServiceIdOrderByIdDesc(serviceId);

		return result;
	}

	@Override
	public BmtPayload getCadetsByTermIdAndBattaionAndCompany(Long termId, String battalion, String serviceSubjectType,
			String assesmentTermType, String company, String serviceId, Pageable pageable) {
		Integer totalRecords = 0;
		BmtPayload bmtPayload = new BmtPayload();
		List<BmtFilterPayload> bmtFilterList = new ArrayList<BmtFilterPayload>();
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
				BmtFilterPayload bmtFilterPayload = new BmtFilterPayload();
				bmtFilterPayload.setId(cad.getId());
				bmtFilterPayload.setTermId(cad.getTerm());
				bmtFilterPayload.setName(cad.getName());
				bmtFilterPayload.setBattalian(cad.getBattalian());
				bmtFilterPayload.setCompany(cad.getCompany());
				bmtFilterPayload.setRank(cad.getCadetRank());
				bmtFilterPayload.setServiceId(cad.getServiceId());
				bmtFilterPayload.setCourse(cad.getCourse());
				bmtFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				bmtFilterPayload.setNationality(cad.getNationality());
				GSO2ServiceSubjectBMTResult sO2ServiceSubjectBMTResult = getGSO2ServiceSubjectBMTResult(cad.getTerm(),
						serviceSubjectType, assesmentTermType, 1, cad.getServiceId());
				if (sO2ServiceSubjectBMTResult != null) {
					bmtFilterPayload.setgSO2ServiceSubjectBMTResult(sO2ServiceSubjectBMTResult);
				} else {
					/*
					 * GSO2ServiceSubjectBMTResult serviceMatResult = new
					 * GSO2ServiceSubjectBMTResult(); List<GSO2ServiceSubjectBMTResult>
					 * subjectResultList = new ArrayList<GSO2ServiceSubjectBMTResult>();
					 * List<GSO2ServiceSubjectBMTResult> result =
					 * sO2ServiceSubjectBMTResult.getByStatusAndTermId(1, cad.getTerm()); Integer
					 * totalMarks = 0; for (TRG_EQTNSubject subject : result) {
					 * TRG_EQTNSubjectResult subjectResult = new TRG_EQTNSubjectResult(); totalMarks
					 * = totalMarks + subject.getTotalMarks();
					 * subjectResult.setSubjectId(subject.getId());
					 * subjectResult.setTotalMarks(subject.getTotalMarks());
					 * subjectResult.setSubjectName(subject.getSubjectName());
					 * subjectResultList.add(subjectResult); } //
					 * trgMatResult.setLeadershipSubjectResult(leadershipSubjectResult);
					 * trgMatResult.setTrgEQTNSubResult(subjectResultList);
					 * trgMatResult.setTotalMarks(totalMarks);
					 * qtnFilterPayload.setTrgEQTNResult(trgMatResult);
					 */
					GSO2ServiceSubjectBMTResult serviceSubjectBMTResult = new GSO2ServiceSubjectBMTResult();
					serviceSubjectBMTResult.setAssesmentTermType(assesmentTermType);
					serviceSubjectBMTResult.setSubjectType(serviceSubjectType);
					serviceSubjectBMTResult.setServiceId(cad.getServiceId());
					serviceSubjectBMTResult.setTermId(cad.getTerm());
					serviceSubjectBMTResult.setStatus(1);
					bmtFilterPayload.setgSO2ServiceSubjectBMTResult(serviceSubjectBMTResult);
				}
				bmtFilterList.add(bmtFilterPayload);
			}
			bmtPayload.setTotalRecords(totalRecords);
			bmtPayload.setBmtFilterPayload(bmtFilterList);
			return bmtPayload;
		} else {
			return null;
		}
	}

	@Override
	public String updateBulkGSO2ServiceSubjectBMTResult(List<BmtFilterPayload> bmtPayloadList) {
		String result = "failed";
		int size = bmtPayloadList.size();
		if (size > 0) {
			for (BmtFilterPayload bmtPayload : bmtPayloadList) {
				GSO2ServiceSubjectBMTResult gSO2ServiceSubjectBMTResult = bmtPayload.getgSO2ServiceSubjectBMTResult();
				if (gSO2ServiceSubjectBMTResult != null && gSO2ServiceSubjectBMTResult.getId() != null
						&& gSO2ServiceSubjectBMTResult.getId() != 0) {
					updateGSO2ServiceSubjectBMTResult(gSO2ServiceSubjectBMTResult);
				} else if (gSO2ServiceSubjectBMTResult != null) {
					if (gSO2ServiceSubjectBMTResult.getObtainedMarks() != null) {
						gSO2ServiceSubjectBMTResult.setCreatedAt(new Date());
						createGSO2ServiceSubjectBMTResult(gSO2ServiceSubjectBMTResult);
					}
				}
			}
			result = "success";
		}
		return result;
	}

	@Override
	public BmtPayload getCadetsBySearch(Long termId, String serviceId, String serviceSubjectType,
			String assesmentTermType, Pageable pageable) {
		BmtPayload bmtPayload = null;
		List<BmtFilterPayload> bmtFilterList = new ArrayList<BmtFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		if (termId == null || termId == 0) {
			return bmtPayload;
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
			bmtPayload = new BmtPayload();
			for (Cadet cad : cadetList) {
				BmtFilterPayload bmtFilterPayload = new BmtFilterPayload();
				bmtFilterPayload.setId(cad.getId());
				bmtFilterPayload.setTermId(cad.getTerm());
				bmtFilterPayload.setName(cad.getName());
				bmtFilterPayload.setBattalian(cad.getBattalian());
				bmtFilterPayload.setCompany(cad.getCompany());
				bmtFilterPayload.setRank(cad.getCadetRank());
				bmtFilterPayload.setServiceId(cad.getServiceId());
				bmtFilterPayload.setCourse(cad.getCourse());
				bmtFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				bmtFilterPayload.setNationality(cad.getNationality());
				// eqtnFilterList.add(qtnFilterPayload);
				GSO2ServiceSubjectBMTResult sO2ServiceSubjectBMTResult = getGSO2ServiceSubjectBMTResult(cad.getTerm(),
						serviceSubjectType, assesmentTermType, 1, cad.getServiceId());
				if (sO2ServiceSubjectBMTResult != null) {
					bmtFilterPayload.setgSO2ServiceSubjectBMTResult(sO2ServiceSubjectBMTResult);
				} else {
					/*
					 * TRG_EQTNResult trgMatResult = new TRG_EQTNResult();
					 * List<TRG_EQTNSubjectResult> subjectResultList = new
					 * ArrayList<TRG_EQTNSubjectResult>(); List<TRG_EQTNSubject> result =
					 * tRG_EQTNSubjectService.getByStatusAndTermId(1, cad.getTerm()); Integer
					 * totalMarks = 0; for (TRG_EQTNSubject subject : result) {
					 * TRG_EQTNSubjectResult subjectResult = new TRG_EQTNSubjectResult(); totalMarks
					 * = totalMarks + subject.getTotalMarks();
					 * subjectResult.setSubjectId(subject.getId());
					 * subjectResult.setTotalMarks(subject.getTotalMarks());
					 * subjectResult.setSubjectName(subject.getSubjectName());
					 * subjectResultList.add(subjectResult); }
					 */

					// trgMatResult.setLeadershipSubjectResult(leadershipSubjectResult);
					GSO2ServiceSubjectBMTResult serviceSubjectBMTResult = new GSO2ServiceSubjectBMTResult();
					serviceSubjectBMTResult.setAssesmentTermType(assesmentTermType);
					serviceSubjectBMTResult.setSubjectType(serviceSubjectType);
					serviceSubjectBMTResult.setServiceId(cad.getServiceId());
					serviceSubjectBMTResult.setTermId(cad.getTerm());
					serviceSubjectBMTResult.setStatus(1);
					bmtFilterPayload.setgSO2ServiceSubjectBMTResult(serviceSubjectBMTResult);
				}
				bmtFilterList.add(bmtFilterPayload);
			}
			bmtPayload.setTotalRecords(totalRecords);
			bmtPayload.setBmtFilterPayload(bmtFilterList);
			return bmtPayload;
		} else {
			return null;
		}
	}

	@Override
	public ServiceSubTermPayload findBmt1Bmt2Mrprac(String serviceId, String resultType, String serviceSubjectType,
			String assesmentTermType) {
		ServiceSubTermPayload result = null;
		List<GSO2ServiceSubjectBMTResult> bmt1List = repo
				.findByServiceIdAndSubjectTypeAndAssesmentTermTypeOrderByTermId(serviceId, serviceSubjectType,
						assesmentTermType);
		List<ServiceBmt2Result> bmt2List = serviceBmt2ResultRepository.findByServiceIdOrderByTermId(serviceId);

		List<RunbackRouteMr> mrList = runbackRouteMrRepo.findByServiceIdAndResultTypeOrderByTermId(serviceId,
				resultType);
		if (bmt1List.size() > 0 || bmt2List.size() > 0 || mrList.size() > 0) {
			result = new ServiceSubTermPayload();
			result.setTerm1(new EdserviceSubPayload());
			result.setTerm2(new EdserviceSubPayload());
			result.setTerm3(new EdserviceSubPayload());
			result.setTech2(new EdserviceSubPayload());
			// for BMT-1
			for (GSO2ServiceSubjectBMTResult bmt1 : bmt1List) {
				if (bmt1.getTermId() == 1) {
					// EdserviceSubPayload term1Bmt = new EdserviceSubPayload();
					result.getTerm1().setBmt1(bmt1);
				}
				if (bmt1.getTermId() == 2) {
					result.getTerm2().setBmt1(bmt1);
				}
				if (bmt1.getTermId() == 3) {
					result.getTerm3().setBmt1(bmt1);
				}
				if (bmt1.getTermId() == 7) {
					result.getTech2().setBmt1(bmt1);
				}
			}
			// for BMT-2
			for (ServiceBmt2Result bmt2 : bmt2List) {
				if (bmt2.getTermId() == 1) {
					result.getTerm1().setBmt2(bmt2);
				}
				if (bmt2.getTermId() == 2) {
					result.getTerm2().setBmt2(bmt2);
				}
				if (bmt2.getTermId() == 3) {
					result.getTerm3().setBmt2(bmt2);
				}
				if (bmt2.getTermId() == 7) {
					result.getTech2().setBmt2(bmt2);
				}
			}
			// for Mr Prac
			for (RunbackRouteMr mr : mrList) {
				if (mr.getTermId() == 1) {
					result.getTerm1().setMrPrac(mr);
				}
				if (mr.getTermId() == 2) {
					result.getTerm2().setMrPrac(mr);
				}
				if (mr.getTermId() == 3) {
					result.getTerm3().setMrPrac(mr);
				}
				if (mr.getTermId() == 7) {
					result.getTech2().setMrPrac(mr);
				}
			}

			if (result.getTerm1().getBmt1() == null && result.getTerm1().getBmt2() == null
					&& result.getTerm1().getMrPrac() == null) {
				result.setTerm1(null);
			}
			if (result.getTerm2().getBmt1() == null && result.getTerm2().getBmt2() == null
					&& result.getTerm2().getMrPrac() == null) {
				result.setTerm2(null);
			}
			if (result.getTerm3().getBmt1() == null && result.getTerm3().getBmt2() == null
					&& result.getTerm3().getMrPrac() == null) {
				result.setTerm3(null);
			}
			if (result.getTech2().getBmt1() == null && result.getTech2().getBmt2() == null
					&& result.getTech2().getMrPrac() == null) {
				result.setTech2(null);
			}
		}

		return result;
	}

	@Override
	public ServiceSubTermPayload findBmt1Bmt2MrpracNew1(String serviceId, String resultType,
			String serviceSubjectType) {
		ServiceSubTermPayload result = null;
		List<GSO2ServiceSubjectBMTResult> bmt1List = repo.findByServiceIdAndSubjectTypeOrderByTermId(serviceId,
				serviceSubjectType);

		List<ServiceBmt2Result> bmt2List = serviceBmt2ResultRepository.findByServiceIdOrderByTermId(serviceId);

		List<RunbackRouteMr> mrList = runbackRouteMrRepo.findByServiceIdAndResultTypeOrderByTermId(serviceId,
				resultType);
		if (bmt1List.size() > 0 || bmt2List.size() > 0 || mrList.size() > 0) {
			result = new ServiceSubTermPayload();
			result.setTerm1(new EdserviceSubPayload());
			result.setTerm2(new EdserviceSubPayload());
			result.setTerm3(new EdserviceSubPayload());
			result.setTech2(new EdserviceSubPayload());
			// for BMT-1
			for (GSO2ServiceSubjectBMTResult bmt1 : bmt1List) {

				if (bmt1.getTermId() == 1) {
					// EdserviceSubPayload term1Bmt = new EdserviceSubPayload();
					// result.getTerm1().setBmt1(bmt1);
					List<GSO2ServiceSubjectBMTResult> bmt1Term1List = repo
							.findByServiceIdAndSubjectTypeAndTermId(serviceId, serviceSubjectType, bmt1.getTermId());
					if (bmt1Term1List.size() > 0) {
						GSO2ServiceSubjectBMTResult bmt1Term1 = new GSO2ServiceSubjectBMTResult();
						for (GSO2ServiceSubjectBMTResult bmt1Term11 : bmt1Term1List) {
							if (bmt1Term11.getAssesmentTermType().equalsIgnoreCase("MID-TERM")) {
								bmt1Term1.setMidId(bmt1Term11.getId());
								bmt1Term1.setMidObtainedMarks(bmt1Term11.getObtainedMarks());
							}
							if (bmt1Term11.getAssesmentTermType().equalsIgnoreCase("Final-TERM")) {
								bmt1Term1.setFinalId(bmt1Term11.getId());
								bmt1Term1.setFinalObtainedMarks(bmt1Term11.getObtainedMarks());
							}
						}
						result.getTerm1().setBmt1(bmt1Term1);
					}

				}
				if (bmt1.getTermId() == 2) {
					// result.getTerm2().setBmt1(bmt1);
					List<GSO2ServiceSubjectBMTResult> bmt1Term1List = repo
							.findByServiceIdAndSubjectTypeAndTermId(serviceId, serviceSubjectType, bmt1.getTermId());
					if (bmt1Term1List.size() > 0) {
						GSO2ServiceSubjectBMTResult bmt1Term1 = new GSO2ServiceSubjectBMTResult();
						for (GSO2ServiceSubjectBMTResult bmt1Term11 : bmt1Term1List) {
							if (bmt1Term11.getAssesmentTermType().equalsIgnoreCase("MID-TERM")) {
								bmt1Term1.setMidId(bmt1Term11.getId());
								bmt1Term1.setMidObtainedMarks(bmt1Term11.getObtainedMarks());
							}
							if (bmt1Term11.getAssesmentTermType().equalsIgnoreCase("Final-TERM")) {
								bmt1Term1.setFinalId(bmt1Term11.getId());
								bmt1Term1.setFinalObtainedMarks(bmt1Term11.getObtainedMarks());
							}
						}
						result.getTerm2().setBmt1(bmt1Term1);
					}
				}
				if (bmt1.getTermId() == 3) {

					List<GSO2ServiceSubjectBMTResult> bmt1Term1List = repo
							.findByServiceIdAndSubjectTypeAndTermId(serviceId, serviceSubjectType, bmt1.getTermId());
					if (bmt1Term1List.size() > 0) {
						GSO2ServiceSubjectBMTResult bmt1Term1 = new GSO2ServiceSubjectBMTResult();
						for (GSO2ServiceSubjectBMTResult bmt1Term11 : bmt1Term1List) {
							if (bmt1Term11.getAssesmentTermType().equalsIgnoreCase("Final-TERM")) {
								bmt1Term1.setFinalId(bmt1Term11.getId());
								bmt1Term1.setFinalObtainedMarks(bmt1Term11.getObtainedMarks());
							}
						}
						result.getTerm3().setBmt1(bmt1Term1);
					}

//					result.getTerm3().setBmt1(bmt1);
				}
				if (bmt1.getTermId() == 7) {
					// result.getTech2().setBmt1(bmt1);
					List<GSO2ServiceSubjectBMTResult> bmt1Term1List = repo
							.findByServiceIdAndSubjectTypeAndTermId(serviceId, serviceSubjectType, bmt1.getTermId());
					if (bmt1Term1List.size() > 0) {
						GSO2ServiceSubjectBMTResult bmt1Term1 = new GSO2ServiceSubjectBMTResult();
						for (GSO2ServiceSubjectBMTResult bmt1Term11 : bmt1Term1List) {
							if (bmt1Term11.getAssesmentTermType().equalsIgnoreCase("MID-TERM")) {
								bmt1Term1.setMidId(bmt1Term11.getId());
								bmt1Term1.setMidObtainedMarks(bmt1Term11.getObtainedMarks());
							}
							if (bmt1Term11.getAssesmentTermType().equalsIgnoreCase("Final-TERM")) {
								bmt1Term1.setFinalId(bmt1Term11.getId());
								bmt1Term1.setFinalObtainedMarks(bmt1Term11.getObtainedMarks());
							}
						}
						result.getTech2().setBmt1(bmt1Term1);
					}
				}
			}
			// for BMT-2
			for (ServiceBmt2Result bmt2 : bmt2List) {
				if (bmt2.getTermId() == 1) {
					result.getTerm1().setBmt2(bmt2);
				}
				if (bmt2.getTermId() == 2) {
					result.getTerm2().setBmt2(bmt2);
				}
				if (bmt2.getTermId() == 3) {
					result.getTerm3().setBmt2(bmt2);
				}
				if (bmt2.getTermId() == 7) {
					result.getTech2().setBmt2(bmt2);
				}
			}
			// for Mr Prac
			for (RunbackRouteMr mr : mrList) {
				if (mr.getTermId() == 1) {
					result.getTerm1().setMrPrac(mr);
				}
				if (mr.getTermId() == 2) {
					result.getTerm2().setMrPrac(mr);
				}
				if (mr.getTermId() == 3) {
					result.getTerm3().setMrPrac(mr);
				}
				if (mr.getTermId() == 7) {
					result.getTech2().setMrPrac(mr);
				}
			}

			if (result.getTerm1().getBmt1() == null && result.getTerm1().getBmt2() == null
					&& result.getTerm1().getMrPrac() == null) {
				result.setTerm1(null);
			}
			if (result.getTerm2().getBmt1() == null && result.getTerm2().getBmt2() == null
					&& result.getTerm2().getMrPrac() == null) {
				result.setTerm2(null);
			}
			if (result.getTerm3().getBmt1() == null && result.getTerm3().getBmt2() == null
					&& result.getTerm3().getMrPrac() == null) {
				result.setTerm3(null);
			}
			if (result.getTech2().getBmt1() == null && result.getTech2().getBmt2() == null
					&& result.getTech2().getMrPrac() == null) {
				result.setTech2(null);
			}
		}

		return result;

	}

	@Override
	public String updateBmt1Bmt2Mrprac(EdserviceSubPayload edServiceSubPayload) {
		String result = "failed";
		if (edServiceSubPayload != null) {
			GSO2ServiceSubjectBMTResult bmt1 = edServiceSubPayload.getBmt1();
			RunbackRouteMr mrPrac = edServiceSubPayload.getMrPrac();
			// update bmt-1
			bmt1 = updateGSO2ServiceSubjectBMTResult(bmt1);
			// update Mr Prac
			mrPrac = battalionService.updateRunbackRouteMr(mrPrac);
			if (bmt1 != null || mrPrac != null) {
				result = "success";
			}
		}
		return result;
	}
}
