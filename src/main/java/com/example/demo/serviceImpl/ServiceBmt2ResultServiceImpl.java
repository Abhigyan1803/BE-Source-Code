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
import com.example.demo.model.ServiceBmt2Result;
import com.example.demo.model.ServiceBmt2Subject;
import com.example.demo.model.ServiceBmt2SubjectResult;
import com.example.demo.payload.ServiceBmt2FilterPayload;
import com.example.demo.payload.ServiceBmt2Payload;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.ServiceBmt2ResultRepository;
import com.example.demo.service.ServiceBmt2ResultService;
import com.example.demo.service.ServiceBmt2SubjectResultService;
import com.example.demo.service.ServiceBmt2SubjectService;

@Service
public class ServiceBmt2ResultServiceImpl implements ServiceBmt2ResultService {

	@Autowired
	private ServiceBmt2ResultRepository serviceBmt2ResultRepository;

	@Autowired
	private ServiceBmt2SubjectService serviceBmt2SubjectService;

	@Autowired
	private ServiceBmt2SubjectResultService serviceBmt2SubjectResultService;

	@Autowired
	AdminCadetRepo cadetRepo;

	@Override
	public ServiceBmt2Result createServiceBmt2Result(ServiceBmt2Result serviceBmt2Result) {
		// TODO Auto-generated method stub
		return serviceBmt2ResultRepository.save(serviceBmt2Result);
	}

	@Override
	public ServiceBmt2Result findByServiceIdAndTermId(String serviceId, Long termId) {
		Optional<ServiceBmt2Result> serviceBmt2MatResult = serviceBmt2ResultRepository
				.findByServiceIdAndTermId(serviceId, termId);
		if (serviceBmt2MatResult.isPresent()) {
			ServiceBmt2Result bmt2MatResult = serviceBmt2MatResult.get();
			List<ServiceBmt2SubjectResult> list = bmt2MatResult.getServiceBmt2SubjectResult();
			for (ServiceBmt2SubjectResult bmt2MatSubReslt : list) {
				ServiceBmt2Subject bmt2Sub = serviceBmt2SubjectService.getSubjectById(bmt2MatSubReslt.getSubjectId());
				bmt2MatSubReslt.setSubjectName(bmt2Sub.getSubjectName());
			}
			return bmt2MatResult;
		}
		return null;
	}

	@Override
	public ServiceBmt2Result updateServiceBmt2Result(ServiceBmt2Result serviceBmt2Result) {
		ServiceBmt2Result serviceBmt2Rslt = null;
		Optional<ServiceBmt2Result> serviceBmt2RsltData = serviceBmt2ResultRepository
				.findById(serviceBmt2Result.getId());
		if (serviceBmt2RsltData.isPresent()) {
			serviceBmt2Rslt = serviceBmt2RsltData.get();
			if (serviceBmt2Rslt != null) {
				serviceBmt2Rslt.setObtainedMarks(serviceBmt2Result.getObtainedMarks());
				serviceBmt2Rslt.setRemarks(serviceBmt2Result.getRemarks());
				serviceBmt2Rslt.setTotalMarks(serviceBmt2Result.getTotalMarks());
				serviceBmt2Rslt.setUpdatedAt(new Date());
				List<ServiceBmt2SubjectResult> SubListTemp = new ArrayList<ServiceBmt2SubjectResult>();
				List<ServiceBmt2SubjectResult> SubList = serviceBmt2Result.getServiceBmt2SubjectResult();
				for (ServiceBmt2SubjectResult subject : SubList) {
					ServiceBmt2SubjectResult serviceBmt2SubjectResult = serviceBmt2SubjectResultService
							.getSubResultById(subject.getId());
					serviceBmt2SubjectResult.setObtainedMarks(subject.getObtainedMarks());
					SubListTemp.add(serviceBmt2SubjectResult);
					serviceBmt2SubjectResultService.updateSubResult(serviceBmt2SubjectResult);
				}
				serviceBmt2Rslt.setServiceBmt2SubjectResult(SubListTemp);
				serviceBmt2Rslt = serviceBmt2ResultRepository.save(serviceBmt2Rslt);
			}
		}
		return serviceBmt2Rslt;
	}

	@Override
	public ServiceBmt2Payload getCadetsByTermIdAndBattaionAndCompany(Long termId, String battalion, String company,
			String serviceId, Pageable pageable) {
		// TODO Auto-generated method stub
		Integer totalRecords = 0;
		ServiceBmt2Payload serviceBmt2Payload = new ServiceBmt2Payload();
		List<ServiceBmt2FilterPayload> serviceBmt2FilterList = new ArrayList<ServiceBmt2FilterPayload>();
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
				ServiceBmt2FilterPayload serviceFilterPayload = new ServiceBmt2FilterPayload();
				serviceFilterPayload.setId(cad.getId());
				serviceFilterPayload.setTermId(cad.getTerm());
				serviceFilterPayload.setName(cad.getName());
				serviceFilterPayload.setBattalian(cad.getBattalian());
				serviceFilterPayload.setCompany(cad.getCompany());
				serviceFilterPayload.setRank(cad.getCadetRank());
				serviceFilterPayload.setServiceId(cad.getServiceId());
				serviceFilterPayload.setCourse(cad.getCourse());
				serviceFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				serviceFilterPayload.setNationality(cad.getNationality());
				ServiceBmt2Result serviceResult = findByServiceIdAndTermId(cad.getServiceId(), cad.getTerm());
				if (serviceResult != null) {
					///// sort logic
					List<ServiceBmt2SubjectResult> subjectResultList = serviceResult.getServiceBmt2SubjectResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					serviceResult.setServiceBmt2SubjectResult(subjectResultList);
					///// till here
					serviceFilterPayload.setServiceBmt2Result(serviceResult);
				} else {
					ServiceBmt2Result serviceMatResult = new ServiceBmt2Result();
					List<ServiceBmt2SubjectResult> serviceBmt2SubjectResult = new ArrayList<ServiceBmt2SubjectResult>();
					List<ServiceBmt2Subject> result = serviceBmt2SubjectService.getByStatusAndTermId(1, termId);
					Integer totalMarks = 0;
					for (ServiceBmt2Subject subject : result) {
						ServiceBmt2SubjectResult subjectResult = new ServiceBmt2SubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarks(subject.getTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						serviceBmt2SubjectResult.add(subjectResult);
					}

					serviceMatResult.setServiceBmt2SubjectResult(serviceBmt2SubjectResult);
					serviceMatResult.setTotalMarks(totalMarks);
					serviceMatResult.setServiceId(cad.getServiceId());
					serviceMatResult.setTermId(cad.getTerm());
					serviceMatResult.setStatus(1);
					serviceFilterPayload.setServiceBmt2Result(serviceMatResult);
				}
				serviceBmt2FilterList.add(serviceFilterPayload);
			}

			serviceBmt2Payload.setTotalRecords(totalRecords);
			serviceBmt2Payload.setServiceBmt2FilterPayload(serviceBmt2FilterList);
			return serviceBmt2Payload;
		} else {
			return null;
		}
	}

	@Override
	public String updateBulkServiceBmt2Result(List<ServiceBmt2FilterPayload> serviceBmt2FilterPayload) {
		// TODO Auto-generated method stub
		String result = "failed";
		int size = serviceBmt2FilterPayload.size();
		if (size > 0) {
			for (ServiceBmt2FilterPayload serviceBmt2Payload : serviceBmt2FilterPayload) {
				ServiceBmt2Result serviceBmt2Result = serviceBmt2Payload.getServiceBmt2Result();
				if (serviceBmt2Result != null && serviceBmt2Result.getId() != null && serviceBmt2Result.getId() != 0) {
					// update logic
					updateServiceBmt2Result(serviceBmt2Result);
				} else if (serviceBmt2Result != null) {
					// add logic
					List<ServiceBmt2SubjectResult> serviceBmt2SubjectResult = serviceBmt2Result
							.getServiceBmt2SubjectResult();
					if (serviceBmt2SubjectResult.size() > 0) {
						for (ServiceBmt2SubjectResult serviceBmt2SubRslt : serviceBmt2SubjectResult) {
							serviceBmt2SubjectResultService.createSubResult(serviceBmt2SubRslt);
						}
					}
					if (serviceBmt2Result.getObtainedMarks() != null) {
						serviceBmt2Result.setCreatedAt(new Date());
						createServiceBmt2Result(serviceBmt2Result);
					}

				}
			}
			result = "success";
		}
		return result;
	}

	@Override
	public ServiceBmt2Payload getCadetsBySearch(Long termId, String serviceId, Pageable pageable) {
		ServiceBmt2Payload serviceBmt2Payload = null;
		List<ServiceBmt2FilterPayload> serviceBmt2FilterList = new ArrayList<ServiceBmt2FilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		if (termId == null || termId == 0) {
			return serviceBmt2Payload;
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
			serviceBmt2Payload = new ServiceBmt2Payload();
			for (Cadet cad : cadetList) {
				ServiceBmt2FilterPayload serviceFilterPayload = new ServiceBmt2FilterPayload();
				serviceFilterPayload.setId(cad.getId());
				serviceFilterPayload.setTermId(cad.getTerm());
				serviceFilterPayload.setName(cad.getName());
				serviceFilterPayload.setBattalian(cad.getBattalian());
				serviceFilterPayload.setCompany(cad.getCompany());
				serviceFilterPayload.setRank(cad.getCadetRank());
				serviceFilterPayload.setServiceId(cad.getServiceId());
				serviceFilterPayload.setCourse(cad.getCourse());
				serviceFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				serviceFilterPayload.setNationality(cad.getNationality());
				ServiceBmt2Result serviceResult = findByServiceIdAndTermId(cad.getServiceId(), (cad.getTerm()));
				if (serviceResult != null) {
					///// sort logic
					List<ServiceBmt2SubjectResult> subjectResultList = serviceResult.getServiceBmt2SubjectResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					serviceResult.setServiceBmt2SubjectResult(subjectResultList);
					///// till here
					serviceFilterPayload.setServiceBmt2Result(serviceResult);
				} else {
					ServiceBmt2Result serviceMatResult = new ServiceBmt2Result();
					List<ServiceBmt2SubjectResult> serviceBmt2SubjectResult = new ArrayList<ServiceBmt2SubjectResult>();
					List<ServiceBmt2Subject> result = serviceBmt2SubjectService.getByStatusAndTermId(1, termId);
					Integer totalMarks = 0;
					for (ServiceBmt2Subject subject : result) {
						ServiceBmt2SubjectResult subjectResult = new ServiceBmt2SubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarks(subject.getTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						serviceBmt2SubjectResult.add(subjectResult);
					}

					serviceMatResult.setServiceBmt2SubjectResult(serviceBmt2SubjectResult);
					serviceMatResult.setTotalMarks(totalMarks);
					serviceMatResult.setServiceId(cad.getServiceId());
					serviceMatResult.setTermId(cad.getTerm());
					serviceMatResult.setStatus(1);
					serviceFilterPayload.setServiceBmt2Result(serviceMatResult);
				}
				serviceBmt2FilterList.add(serviceFilterPayload);
			}
			serviceBmt2Payload.setTotalRecords(totalRecords);
			serviceBmt2Payload.setServiceBmt2FilterPayload(serviceBmt2FilterList);
			return serviceBmt2Payload;
		} else {
			return null;
		}

	}
}
