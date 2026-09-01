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
import com.example.demo.model.IntellectualSkillsResult;
import com.example.demo.model.IntellectualSkillsSubject;
import com.example.demo.model.IntellectualSkillsSubjectResult;
import com.example.demo.payload.IntellectualSkillsFilterPayload;
import com.example.demo.payload.IntellectualSkillsPayload;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.IntellectualSkillsResultRepo;
import com.example.demo.service.IntellectualSkillsResultService;
import com.example.demo.service.IntellectualSkillsSubjectResultService;
import com.example.demo.service.IntellectualSkillsSubjectService;

@Service
public class IntellectualSkillsResultServiceImpl implements IntellectualSkillsResultService {
	@Autowired
	private IntellectualSkillsResultRepo intellectualSkillsResultRepo;

	@Autowired
	private IntellectualSkillsSubjectService intellectualSkillsSubjectService;
	@Autowired
	private IntellectualSkillsSubjectResultService intellectualSkillsSubjectResultService;

	@Autowired
	private AdminCadetRepo cadetRepo;

	@Override
	public IntellectualSkillsResult createIntellectualSkillsResult(IntellectualSkillsResult intellectualSkillsResult) {
		// TODO Auto-generated method stub
		IntellectualSkillsResult saveIntellectualSkillsResult = intellectualSkillsResultRepo
				.save(intellectualSkillsResult);
		return saveIntellectualSkillsResult;
	}

	@Override
	public IntellectualSkillsResult findByServiceIdAndTermId(String serviceId, Long termId) {
		Optional<IntellectualSkillsResult> intellectualSkillsResult = intellectualSkillsResultRepo
				.findByServiceIdAndTermId(serviceId, termId);
		if (intellectualSkillsResult.isPresent()) {
			IntellectualSkillsResult leaderMatResult = intellectualSkillsResult.get();
			List<IntellectualSkillsSubjectResult> list = leaderMatResult.getIntellectualSkillsSubResult();
			for (IntellectualSkillsSubjectResult leadetMatSubReslt : list) {
				IntellectualSkillsSubject leaderSub = intellectualSkillsSubjectService
						.getSubjectById(leadetMatSubReslt.getSubjectId());
				leadetMatSubReslt.setSubjectName(leaderSub.getSubjectName());
			}
			return leaderMatResult;
		}
		return null;
	}

	@Override
	public IntellectualSkillsResult updateIntellectualSkillsResult(IntellectualSkillsResult intellectualSkillsResult) {
		IntellectualSkillsResult intellectualSkillsRslt = null;
		Optional<IntellectualSkillsResult> intellectualSkillsRsltData = intellectualSkillsResultRepo
				.findById(intellectualSkillsResult.getId());
		if (intellectualSkillsRsltData.isPresent()) {

			intellectualSkillsRslt = intellectualSkillsRsltData.get();
			if (intellectualSkillsRslt != null) {

				intellectualSkillsRslt.setMidObtainedMarks(intellectualSkillsResult.getMidObtainedMarks());
				intellectualSkillsRslt.setFinalObtainedMarks(intellectualSkillsResult.getFinalObtainedMarks());

				intellectualSkillsRslt.setRemarks(intellectualSkillsResult.getRemarks());
				intellectualSkillsRslt.setMidTotalMarks(intellectualSkillsResult.getMidTotalMarks());
				intellectualSkillsRslt.setFinalTotalMarks(intellectualSkillsResult.getFinalTotalMarks());
				intellectualSkillsRslt.setUpdatedAt(intellectualSkillsResult.getUpdatedAt());

				List<IntellectualSkillsSubjectResult> SubListTemp = new ArrayList<IntellectualSkillsSubjectResult>();
				List<IntellectualSkillsSubjectResult> SubList = intellectualSkillsResult
						.getIntellectualSkillsSubResult();
				for (IntellectualSkillsSubjectResult subject : SubList) {

					IntellectualSkillsSubjectResult intellectualSkillsSubjectResult = intellectualSkillsSubjectResultService
							.getSubResultById(subject.getId());
					intellectualSkillsSubjectResult.setMidObtainedMarks(subject.getMidObtainedMarks());
					intellectualSkillsSubjectResult.setFinalObtainedMarks(subject.getFinalObtainedMarks());
					intellectualSkillsSubjectResult.setUpdatedAt(new Date());
					SubListTemp.add(intellectualSkillsSubjectResult);
					intellectualSkillsSubjectResultService.updateSubResult(intellectualSkillsSubjectResult);
				}
				intellectualSkillsRslt.setIntellectualSkillsSubResult(SubListTemp);
			}
			intellectualSkillsRslt = intellectualSkillsResultRepo.save(intellectualSkillsRslt);
		}
		return intellectualSkillsRslt;

	}

	@Override
	public List<IntellectualSkillsResult> findByServiceId(String serviceId) {
		List<IntellectualSkillsResult> intellectualSkillsResultList = intellectualSkillsResultRepo
				.findByServiceIdOrderByTermId(serviceId);
		if (intellectualSkillsResultList != null && intellectualSkillsResultList.size() != 0) {
			for (IntellectualSkillsResult leaderMatResult : intellectualSkillsResultList) {
				List<IntellectualSkillsSubjectResult> list = leaderMatResult.getIntellectualSkillsSubResult();
				for (IntellectualSkillsSubjectResult leadetMatSubReslt : list) {
					IntellectualSkillsSubject leaderSub = intellectualSkillsSubjectService
							.getSubjectById(leadetMatSubReslt.getSubjectId());
					leadetMatSubReslt.setSubjectName(leaderSub.getSubjectName());
				}
				return intellectualSkillsResultList;
			}
		}
		return null;
	}

	@Override
	public IntellectualSkillsPayload getCadetsByTermIdAndBattaionAndCompany(Long termId, String battalion,
			String company, String serviceId, Pageable pageable) {
		// TODO Auto-generated method stub
		Integer totalRecords = 0;
		IntellectualSkillsPayload intellectualSkillsPayload = new IntellectualSkillsPayload();
		List<IntellectualSkillsFilterPayload> intellectualSkillsFilterList = new ArrayList<IntellectualSkillsFilterPayload>();
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
				IntellectualSkillsFilterPayload intellectualFilterPayload = new IntellectualSkillsFilterPayload();
				intellectualFilterPayload.setId(cad.getId());
				intellectualFilterPayload.setTermId(cad.getTerm());
				intellectualFilterPayload.setName(cad.getName());
				intellectualFilterPayload.setBattalian(cad.getBattalian());
				intellectualFilterPayload.setCompany(cad.getCompany());
				intellectualFilterPayload.setRank(cad.getCadetRank());
				intellectualFilterPayload.setServiceId(cad.getServiceId());
				intellectualFilterPayload.setCourse(cad.getCourse());
				intellectualFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				intellectualFilterPayload.setNationality(cad.getNationality());
				IntellectualSkillsResult intellectualResult = findByServiceIdAndTermId(cad.getServiceId(),
						Long.parseLong(cad.getTerm().toString()));
				if (intellectualResult != null) {
					///// sort logic
					List<IntellectualSkillsSubjectResult> subjectResultList = intellectualResult
							.getIntellectualSkillsSubResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					intellectualResult.setIntellectualSkillsSubResult(subjectResultList);
					///// till here
					intellectualFilterPayload.setIntellectualSkillsResult(intellectualResult);
				} else {
					IntellectualSkillsResult intMatResult = new IntellectualSkillsResult();
					List<IntellectualSkillsSubjectResult> intellectualSubjectResult = new ArrayList<IntellectualSkillsSubjectResult>();
					List<IntellectualSkillsSubject> result = intellectualSkillsSubjectService.getByStatusAndTermId(1,
							termId);
					// Integer totalMarks = 0;
					Integer midTotalMarks = 0;
					Integer finalTotalMarks = 0;
					for (IntellectualSkillsSubject subject : result) {
						IntellectualSkillsSubjectResult subjectResult = new IntellectualSkillsSubjectResult();
						midTotalMarks = midTotalMarks
								+ (subject.getMidTotalMarks() == null ? 0 : subject.getMidTotalMarks());
						finalTotalMarks = finalTotalMarks
								+ (subject.getFinalTotalMarks() == null ? 0 : subject.getFinalTotalMarks());
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setMidTotalMarks(subject.getMidTotalMarks());
						subjectResult.setFinalTotalMarks(subject.getFinalTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						intellectualSubjectResult.add(subjectResult);
					}

					intMatResult.setIntellectualSkillsSubResult(intellectualSubjectResult);
					intMatResult.setMidTotalMarks(midTotalMarks);
					intMatResult.setFinalTotalMarks(finalTotalMarks);
					intMatResult.setServiceId(cad.getServiceId());
					intMatResult.setTermId(cad.getTerm());
					intMatResult.setStatus(1);
					intellectualFilterPayload.setIntellectualSkillsResult(intMatResult);
				}
				intellectualSkillsFilterList.add(intellectualFilterPayload);
			}

			intellectualSkillsPayload.setTotalRecords(totalRecords);
			intellectualSkillsPayload.setIntellectualSkillsFilterPayload(intellectualSkillsFilterList);
			return intellectualSkillsPayload;
		} else {
			return null;
		}
	}

	@Override
	public String updateBulkIntellectualSkillsResult(
			List<IntellectualSkillsFilterPayload> intellectualSkillsPayloadList) {
		String result = "failed";
		int size = intellectualSkillsPayloadList.size();
		if (size > 0) {
			for (IntellectualSkillsFilterPayload intellectualSkillsPayload : intellectualSkillsPayloadList) {
				IntellectualSkillsResult intellectualSkillsResult = intellectualSkillsPayload
						.getIntellectualSkillsResult();
				if (intellectualSkillsResult != null && intellectualSkillsResult.getId() != null
						&& intellectualSkillsResult.getId() != 0) {
					updateIntellectualSkillsResult(intellectualSkillsResult);
				} else if (intellectualSkillsResult != null) {
					List<IntellectualSkillsSubjectResult> intellectualSkillsSubjectResult = intellectualSkillsResult
							.getIntellectualSkillsSubResult();
					if (intellectualSkillsSubjectResult != null) {
						for (IntellectualSkillsSubjectResult intellectualSkillsSubResult : intellectualSkillsSubjectResult) {
							intellectualSkillsSubjectResultService.createSubResult(intellectualSkillsSubResult);
						}
					}
					if (intellectualSkillsResult.getMidObtainedMarks() != null
							|| intellectualSkillsResult.getFinalObtainedMarks() != null) {
					}
					intellectualSkillsResult.setCreatedAt(new Date());
					createIntellectualSkillsResult(intellectualSkillsResult);
				}
			}
			result = "success";
		}
		return result;
	}

	@Override
	public IntellectualSkillsPayload getCadetsBySearch(String serviceId, Long termId, Pageable pageable) {
		IntellectualSkillsPayload intellectualSkillsPayload = null;
		List<IntellectualSkillsFilterPayload> intellectualSkillsFilterList = new ArrayList<IntellectualSkillsFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		if (termId == null || termId == 0) {
			return intellectualSkillsPayload;
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
			intellectualSkillsPayload = new IntellectualSkillsPayload();
			for (Cadet cad : cadetList) {
				IntellectualSkillsFilterPayload intellectualFilterPayload = new IntellectualSkillsFilterPayload();
				intellectualFilterPayload.setId(cad.getId());
				intellectualFilterPayload.setTermId(cad.getTerm());
				intellectualFilterPayload.setName(cad.getName());
				intellectualFilterPayload.setBattalian(cad.getBattalian());
				intellectualFilterPayload.setCompany(cad.getCompany());
				intellectualFilterPayload.setRank(cad.getCadetRank());
				intellectualFilterPayload.setServiceId(cad.getServiceId());
				intellectualFilterPayload.setCourse(cad.getCourse());
				intellectualFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				intellectualFilterPayload.setNationality(cad.getNationality());
				IntellectualSkillsResult intellectualResult = findByServiceIdAndTermId(cad.getServiceId(),
						Long.parseLong(cad.getTerm().toString()));
				if (intellectualResult != null) {
					///// sort logic
					List<IntellectualSkillsSubjectResult> subjectResultList = intellectualResult
							.getIntellectualSkillsSubResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					intellectualResult.setIntellectualSkillsSubResult(subjectResultList);
					///// till here
					intellectualFilterPayload.setIntellectualSkillsResult(intellectualResult);
				} else {
					IntellectualSkillsResult intMatResult = new IntellectualSkillsResult();
					List<IntellectualSkillsSubjectResult> intellectualSubjectResult = new ArrayList<IntellectualSkillsSubjectResult>();
					List<IntellectualSkillsSubject> result = intellectualSkillsSubjectService.getByStatusAndTermId(1,
							termId);
					Integer midTotalMarks = 0;
					Integer finalTotalMarks = 0;
					for (IntellectualSkillsSubject subject : result) {
						IntellectualSkillsSubjectResult subjectResult = new IntellectualSkillsSubjectResult();
						midTotalMarks = midTotalMarks
								+ (subject.getMidTotalMarks() == null ? 0 : subject.getMidTotalMarks());
						finalTotalMarks = finalTotalMarks
								+ (subject.getFinalTotalMarks() == null ? 0 : subject.getFinalTotalMarks());
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setMidTotalMarks(subject.getMidTotalMarks());
						subjectResult.setFinalTotalMarks(subject.getFinalTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						intellectualSubjectResult.add(subjectResult);
					}

					intMatResult.setIntellectualSkillsSubResult(intellectualSubjectResult);
					intMatResult.setMidTotalMarks(midTotalMarks);
					intMatResult.setFinalTotalMarks(finalTotalMarks);
					intMatResult.setServiceId(cad.getServiceId());
					intMatResult.setTermId(cad.getTerm());
					intMatResult.setStatus(1);
					intellectualFilterPayload.setIntellectualSkillsResult(intMatResult);
				}
				intellectualSkillsFilterList.add(intellectualFilterPayload);
			}
			intellectualSkillsPayload.setTotalRecords(totalRecords);
			intellectualSkillsPayload.setIntellectualSkillsFilterPayload(intellectualSkillsFilterList);
			return intellectualSkillsPayload;
		} else {
			return null;
		}
	}
}
