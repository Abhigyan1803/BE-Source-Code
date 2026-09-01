package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.AcademicCreditForExcellenceResult;
import com.example.demo.model.AcademicCreditForExcellenceSubject;
import com.example.demo.model.AcademicCreditForExcellenceSubjectResult;
import com.example.demo.model.Cadet;
import com.example.demo.payload.CreditExcellenceFilterPayload;
import com.example.demo.payload.CreditExcellencePayload;
import com.example.demo.repository.AcademicCreditForExcellenceResultRepo;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.service.AcademicCreditForExcellenceResultService;
import com.example.demo.service.AcademicCreditForExcellenceSubjectResultService;
import com.example.demo.service.AcademicCreditForExcellenceSubjectService;

@Service
public class AcademicCreditForExcellenceResultServiceImpl implements AcademicCreditForExcellenceResultService {
	@Autowired
	private AcademicCreditForExcellenceResultRepo academicCreditForExcellenceResultRepo;

	@Autowired
	private AcademicCreditForExcellenceSubjectResultService academicCreditForExcellenceSubjectResultService;

	@Autowired
	private AcademicCreditForExcellenceSubjectService academicCreditForExcellenceSubjectService;
	@Autowired
	private AdminCadetRepo cadetRepo;

	@Override
	public AcademicCreditForExcellenceResult createAcademicCreditForExcellenceResult(
			AcademicCreditForExcellenceResult academicCreditForExcellenceResult) {
		AcademicCreditForExcellenceResult saveAcademicCreditForExcellenceResult = academicCreditForExcellenceResultRepo
				.save(academicCreditForExcellenceResult);
		return saveAcademicCreditForExcellenceResult;
	}

	@Override
	public AcademicCreditForExcellenceResult updateAcademicCreditForExcellenceResult(
			AcademicCreditForExcellenceResult academicCreditForExcellenceResult) {
		// TODO Auto-generated method stub
		AcademicCreditForExcellenceResult academicCreditForExcellenceRslt = null;
		Optional<AcademicCreditForExcellenceResult> academicCreditForExcellenceRsltData = academicCreditForExcellenceResultRepo
				.findById(academicCreditForExcellenceResult.getId());
		if (academicCreditForExcellenceRsltData.isPresent()) {

			academicCreditForExcellenceRslt = academicCreditForExcellenceRsltData.get();
			if (academicCreditForExcellenceRslt != null) {

				academicCreditForExcellenceRslt.setObtainedMarks(academicCreditForExcellenceResult.getObtainedMarks());
				academicCreditForExcellenceRslt.setRemarks(academicCreditForExcellenceResult.getRemarks());
				academicCreditForExcellenceRslt.setTotalMarks(academicCreditForExcellenceResult.getTotalMarks());
				academicCreditForExcellenceRslt.setUpdatedAt(academicCreditForExcellenceResult.getUpdatedAt());

				List<AcademicCreditForExcellenceSubjectResult> SubListTemp = new ArrayList<AcademicCreditForExcellenceSubjectResult>();
				List<AcademicCreditForExcellenceSubjectResult> SubList = academicCreditForExcellenceResult
						.getCreditExcellenceSubResult();
				for (AcademicCreditForExcellenceSubjectResult subject : SubList) {

					AcademicCreditForExcellenceSubjectResult academicCreditForExcellenceSubjectResult = academicCreditForExcellenceSubjectResultService
							.getSubResultById(subject.getId());

					System.out.println("id==>>" + subject.getId() + ",  obt marks==>>" + subject.getObtainedMarks());
					academicCreditForExcellenceSubjectResult.setObtainedMarks(subject.getObtainedMarks());
					// campSubjectResult.setSubjectId(subject.getSubjectId());
					// campSubjectResult.setTermId(subject.getTermId());
					// campSubjectResult.setTotalMarks(subject.getTotalMarks());
					academicCreditForExcellenceSubjectResult.setUpdatedAt(new Date());
					SubListTemp.add(academicCreditForExcellenceSubjectResult);
					academicCreditForExcellenceSubjectResultService
							.updateSubResult(academicCreditForExcellenceSubjectResult);
					// campMarksRslt.setCampSubjectResult((List<CampSubjectResult>)
					// campSubjectResult);
				}

				academicCreditForExcellenceRslt.setCreditExcellenceSubResult(SubListTemp);
			}

			academicCreditForExcellenceRslt = academicCreditForExcellenceResultRepo
					.save(academicCreditForExcellenceRslt);

		}

		return academicCreditForExcellenceRslt;
	}

	@Override
	public AcademicCreditForExcellenceResult findByServiceIdAndTermId(String serviceId, int termId) {
		Optional<AcademicCreditForExcellenceResult> CreditForExcellenceMatResult = academicCreditForExcellenceResultRepo
				.findByServiceIdAndTermId(serviceId, termId);
		if (CreditForExcellenceMatResult.isPresent()) {
			AcademicCreditForExcellenceResult leaderMatResult = CreditForExcellenceMatResult.get();
			List<AcademicCreditForExcellenceSubjectResult> list = leaderMatResult.getCreditExcellenceSubResult();
			for (AcademicCreditForExcellenceSubjectResult leadetMatSubReslt : list) {
				AcademicCreditForExcellenceSubject leaderSub = academicCreditForExcellenceSubjectService
						.getSubjectById(leadetMatSubReslt.getSubjectId());
				leadetMatSubReslt.setSubjectName(leaderSub.getSubjectName());
			}
			return leaderMatResult;
		}
		return null;
	}

	@Override
	public CreditExcellencePayload getCadetsByTermIdAndBattaionAndCompany(Long termId, String battalion, String company,
			String serviceId, Pageable pageable) {
		Integer totalRecords = 0;
		CreditExcellencePayload creditExcellencePayload = new CreditExcellencePayload();
		List<CreditExcellenceFilterPayload> creditExcellenceFilterList = new ArrayList<CreditExcellenceFilterPayload>();
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
				CreditExcellenceFilterPayload creditFilterPayload = new CreditExcellenceFilterPayload();
				creditFilterPayload.setId(cad.getId());
				creditFilterPayload.setTermId(cad.getTerm());
				creditFilterPayload.setName(cad.getName());
				creditFilterPayload.setBattalian(cad.getBattalian());
				creditFilterPayload.setCompany(cad.getCompany());
				creditFilterPayload.setRank(cad.getCadetRank());
				creditFilterPayload.setServiceId(cad.getServiceId());
				creditFilterPayload.setCourse(cad.getCourse());
				creditFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				creditFilterPayload.setNationality(cad.getNationality());
				AcademicCreditForExcellenceResult creditResult = findByServiceIdAndTermId(cad.getServiceId(),
						Integer.parseInt(cad.getTerm().toString()));
				if (creditResult != null) {
					///// sort logic
					List<AcademicCreditForExcellenceSubjectResult> subjectResultList = creditResult
							.getCreditExcellenceSubResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					creditResult.setCreditExcellenceSubResult(subjectResultList);
					///// till here
					creditFilterPayload.setAcademicCreditForExcellenceResult(creditResult);
				} else {
					AcademicCreditForExcellenceResult ledMatResult = new AcademicCreditForExcellenceResult();
					List<AcademicCreditForExcellenceSubjectResult> leadershipSubjectResult = new ArrayList<AcademicCreditForExcellenceSubjectResult>();
					List<AcademicCreditForExcellenceSubject> result = academicCreditForExcellenceSubjectService
							.getBystatus(1);
					Integer totalMarks = 0;
					for (AcademicCreditForExcellenceSubject subject : result) {
						AcademicCreditForExcellenceSubjectResult subjectResult = new AcademicCreditForExcellenceSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarks(subject.getTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						leadershipSubjectResult.add(subjectResult);
					}

					ledMatResult.setCreditExcellenceSubResult(leadershipSubjectResult);
					ledMatResult.setTotalMarks(totalMarks);
					ledMatResult.setServiceId(cad.getServiceId());
					ledMatResult.setTermId(Integer.parseInt(cad.getTerm().toString()));
					ledMatResult.setStatus(1);
					creditFilterPayload.setAcademicCreditForExcellenceResult(ledMatResult);
				}
				creditExcellenceFilterList.add(creditFilterPayload);
			}
			creditExcellencePayload.setTotalRecords(totalRecords);
			creditExcellencePayload.setCreditExcellenceFilterPayload(creditExcellenceFilterList);
			return creditExcellencePayload;
		} else {
			return null;
		}
	}

	@Override
	public String updateBulkAcademicCreditForExcellenceResult(
			List<CreditExcellenceFilterPayload> creditExcellencePayloadList) {
		String result = "failed";
		int size = creditExcellencePayloadList.size();
		if (size > 0) {
			for (CreditExcellenceFilterPayload creditExcellencePayload : creditExcellencePayloadList) {
				AcademicCreditForExcellenceResult academiccreditExcellenceResult = creditExcellencePayload
						.getAcademicCreditForExcellenceResult();
				if (academiccreditExcellenceResult != null && academiccreditExcellenceResult.getId() != null
						&& academiccreditExcellenceResult.getId() != 0) {
					updateAcademicCreditForExcellenceResult(academiccreditExcellenceResult);
				} else if (academiccreditExcellenceResult != null) {
					List<AcademicCreditForExcellenceSubjectResult> academicCreditForExcellenceSubjectResult = academiccreditExcellenceResult
							.getCreditExcellenceSubResult();
					if (academicCreditForExcellenceSubjectResult != null) {
						for (AcademicCreditForExcellenceSubjectResult CreditForExcellenceSubRslt : academicCreditForExcellenceSubjectResult) {
							academicCreditForExcellenceSubjectResultService.createSubResult(CreditForExcellenceSubRslt);
						}
					}
					if (academiccreditExcellenceResult.getObtainedMarks() != null) {
						academiccreditExcellenceResult.setCreatedAt(new Date());
						createAcademicCreditForExcellenceResult(academiccreditExcellenceResult);
					}
				}
			}
			result = "success";
		}
		return result;
	}

	@Override
	public CreditExcellencePayload getCadetsBySearch(Long termId, String serviceId, Pageable pageable) {
		CreditExcellencePayload creditExcellencePayload = null;
		List<CreditExcellenceFilterPayload> creditExcellenceFilterList = new ArrayList<CreditExcellenceFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		if (termId == null || termId == 0) {
			return creditExcellencePayload;
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
			creditExcellencePayload = new CreditExcellencePayload();
			for (Cadet cad : cadetList) {
				CreditExcellenceFilterPayload creditFilterPayload = new CreditExcellenceFilterPayload();
				creditFilterPayload.setId(cad.getId());
				creditFilterPayload.setTermId(cad.getTerm());
				creditFilterPayload.setName(cad.getName());
				creditFilterPayload.setBattalian(cad.getBattalian());
				creditFilterPayload.setCompany(cad.getCompany());
				creditFilterPayload.setRank(cad.getCadetRank());
				creditFilterPayload.setServiceId(cad.getServiceId());
				creditFilterPayload.setCourse(cad.getCourse());
				creditFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				creditFilterPayload.setNationality(cad.getNationality());
				AcademicCreditForExcellenceResult creditResult = findByServiceIdAndTermId(cad.getServiceId(),
						Integer.parseInt(cad.getTerm().toString()));
				if (creditResult != null) {
					///// sort logic
					List<AcademicCreditForExcellenceSubjectResult> subjectResultList = creditResult
							.getCreditExcellenceSubResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					creditResult.setCreditExcellenceSubResult(subjectResultList);
					///// till here
					creditFilterPayload.setAcademicCreditForExcellenceResult(creditResult);
				} else {
					AcademicCreditForExcellenceResult ledMatResult = new AcademicCreditForExcellenceResult();
					List<AcademicCreditForExcellenceSubjectResult> leadershipSubjectResult = new ArrayList<AcademicCreditForExcellenceSubjectResult>();
					List<AcademicCreditForExcellenceSubject> result = academicCreditForExcellenceSubjectService
							.getBystatus(1);
					Integer totalMarks = 0;
					for (AcademicCreditForExcellenceSubject subject : result) {
						AcademicCreditForExcellenceSubjectResult subjectResult = new AcademicCreditForExcellenceSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarks(subject.getTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						leadershipSubjectResult.add(subjectResult);
					}

					ledMatResult.setCreditExcellenceSubResult(leadershipSubjectResult);
					ledMatResult.setTotalMarks(totalMarks);
					ledMatResult.setServiceId(cad.getServiceId());
					ledMatResult.setTermId(Integer.parseInt(cad.getTerm().toString()));
					ledMatResult.setStatus(1);
					creditFilterPayload.setAcademicCreditForExcellenceResult(ledMatResult);
				}
				creditExcellenceFilterList.add(creditFilterPayload);
			}
			creditExcellencePayload.setTotalRecords(totalRecords);
			creditExcellencePayload.setCreditExcellenceFilterPayload(creditExcellenceFilterList);
			return creditExcellencePayload;
		} else {
			return null;
		}
	}

}
