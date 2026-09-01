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
import com.example.demo.model.SportsResult;
import com.example.demo.model.SportsSubject;
import com.example.demo.model.SportsSubjectResult;
import com.example.demo.payload.SportsFilterPayload;
import com.example.demo.payload.SportsPayload;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.SportsResultRepo;
import com.example.demo.service.SportsResultService;
import com.example.demo.service.SportsSubjectResultService;
import com.example.demo.service.SportsSubjectService;

@Service
public class SportsResultServiceImpl implements SportsResultService {
	@Autowired
	private SportsResultRepo sportsResultRepo;

	@Autowired
	private SportsSubjectService sportsSubjectService;

	@Autowired
	private SportsSubjectResultService sportsSubjectResultService;

	@Autowired
	private AdminCadetRepo cadetRepo;

	@Override
	public SportsResult createSportsResult(SportsResult sportsResult) {
		// TODO Auto-generated method stub
		SportsResult saveSportsResult = sportsResultRepo.save(sportsResult);

		return saveSportsResult;

	}

	@Override
	public SportsResult findByServiceIdAndTermIdAndTermSession(String serviceId, Long termId, String termSession) {
		// TODO Auto-generated method stub
		Optional<SportsResult> sportsResult = sportsResultRepo.findByServiceIdAndTermIdAndTermSession(serviceId, termId,
				termSession);
		if (sportsResult.isPresent()) {

			SportsResult leaderMatResult = sportsResult.get();

			List<SportsSubjectResult> list = leaderMatResult.getSportsSubResult();

			for (SportsSubjectResult leadetMatSubReslt : list) {
				System.out.println("ghhjhjjh---" + leadetMatSubReslt.getSubjectId());
				SportsSubject leaderSub = sportsSubjectService.getSubjectById(leadetMatSubReslt.getSubjectId()).get();
				leadetMatSubReslt.setSubjectName(leaderSub.getSubjectName());
			}
			return leaderMatResult;
		}
		return null;
	}

	@Override
	public List<SportsResult> findByServiceId(String serviceId) {
		List<SportsResult> sportsResultList = sportsResultRepo.findByServiceIdOrderByTermId(serviceId);
		if (sportsResultList != null && sportsResultList.size() != 0) {
			for (SportsResult leaderMatResult : sportsResultList) {
				List<SportsSubjectResult> list = leaderMatResult.getSportsSubResult();
				for (SportsSubjectResult leadetMatSubReslt : list) {
					SportsSubject leaderSub = sportsSubjectService.getSubjectById(leadetMatSubReslt.getSubjectId())
							.get();
					leadetMatSubReslt.setSubjectName(leaderSub.getSubjectName());
				}
			}
			return sportsResultList;
		}
		return null;
	}

	@Override
	public SportsResult updateSportsResult(SportsResult sportsResult) {
		// TODO Auto-generated method stub
		SportsResult sportsRslt = null;
		Optional<SportsResult> sportsRsltData = sportsResultRepo.findById(sportsResult.getId());
		if (sportsRsltData.isPresent()) {

			sportsRslt = sportsRsltData.get();
			if (sportsRslt != null) {

				sportsRslt.setObtainedMarks(sportsResult.getObtainedMarks());
				sportsRslt.setRemarks(sportsResult.getRemarks());
				sportsRslt.setTotalMarks(sportsResult.getTotalMarks());
				sportsRslt.setUpdatedAt(sportsResult.getUpdatedAt());

				List<SportsSubjectResult> SubListTemp = new ArrayList<SportsSubjectResult>();
				List<SportsSubjectResult> SubList = sportsResult.getSportsSubResult();
				for (SportsSubjectResult subject : SubList) {

					SportsSubjectResult sportsSubjectResult = sportsSubjectResultService
							.getSubResultById(subject.getId());
					sportsSubjectResult.setObtainedMarks(subject.getObtainedMarks());
					sportsSubjectResult.setUpdatedAt(new Date());
					SubListTemp.add(sportsSubjectResult);
					sportsSubjectResultService.updateSubResult(sportsSubjectResult);
				}
				sportsRslt.setSportsSubResult(SubListTemp);
			}
			sportsRslt = sportsResultRepo.save(sportsRslt);
		}
		return sportsRslt;

	}

	@Override
	public SportsPayload getCadetsSportsByTermIdAndBattaionAndCompany(Long termId, String termSession, String battalion,
			String company, String serviceId, Pageable pageable) {
		Integer totalRecords = 0;
		SportsPayload sportsPayload = new SportsPayload();
		List<SportsFilterPayload> sportsFilterList = new ArrayList<SportsFilterPayload>();
		List<Cadet> cadetList = null;
		if (termId != null) {
			if (battalion != null) {
				if (company != null) {
					if (serviceId != null) {
						cadetList = cadetRepo
								.findAllByTermAndBattalianAndCompanyAndTermSessionAndStatusAndServiceIdLike(termId,
										battalion, company, termSession, 1, "%" + serviceId + "%", pageable);
						totalRecords = cadetRepo
								.findAllByTermAndBattalianAndCompanyAndTermSessionAndStatusAndServiceIdLike(termId,
										battalion, company, termSession, 1, "%" + serviceId + "%")
								.size();
					} else {
						cadetList = cadetRepo.findAllByTermAndBattalianAndCompanyAndTermSessionAndStatus(termId,
								battalion, company, termSession, 1, pageable);
						totalRecords = cadetRepo.findAllByTermAndBattalianAndCompanyAndTermSessionAndStatus(termId,
								battalion, company, termSession, 1).size();
					}

				} else {
					if (serviceId != null) {
						cadetList = cadetRepo.findAllByTermAndBattalianAndTermSessionAndStatusAndServiceIdLike(termId,
								battalion, termSession, 1, "%" + serviceId + "%", pageable);
						totalRecords = cadetRepo.findAllByTermAndBattalianAndTermSessionAndStatusAndServiceIdLike(
								termId, battalion, termSession, 1, "%" + serviceId + "%").size();
					} else {
						cadetList = cadetRepo.findAllByTermAndBattalianAndTermSessionAndStatus(termId, battalion,
								termSession, 1, pageable);
						totalRecords = cadetRepo
								.findAllByTermAndBattalianAndTermSessionAndStatus(termId, battalion, termSession, 1)
								.size();
					}

				}
			} else {
				if (serviceId != null) {
					cadetList = cadetRepo.findAllByTermAndTermSessionAndStatusAndServiceIdLike(termId, termSession, 1,
							"%" + serviceId + "%", pageable);
					totalRecords = cadetRepo.findAllByTermAndTermSessionAndStatusAndServiceIdLike(termId, termSession,
							1, "%" + serviceId + "%").size();
				} else {
					cadetList = cadetRepo.findAllByTermAndTermSessionAndStatus(termId, termSession, 1, pageable);
					totalRecords = cadetRepo.findAllByTermAndTermSessionAndStatus(termId, termSession, 1).size();
				}

			}
		} else {
			if (serviceId != null) {
				cadetList = cadetRepo.findByStatusAndServiceIdLike(1, "%" + serviceId + "%", pageable);
				totalRecords = cadetRepo.findByStatusAndServiceIdLike(1, "%" + serviceId + "%").size();
			} else {
				cadetList = cadetRepo.findAllByStatus(1, pageable);
				// cadetList = pageCadet.toList();
				totalRecords = cadetRepo.findAll().size();
			}

		}

		if (cadetList != null && cadetList.size() != 0) {
			for (Cadet cad : cadetList) {
				SportsFilterPayload sportFilterPayload = new SportsFilterPayload();
				sportFilterPayload.setId(cad.getId());
				sportFilterPayload.setTermId(cad.getTerm());
				sportFilterPayload.setName(cad.getName());
				sportFilterPayload.setBattalian(cad.getBattalian());
				sportFilterPayload.setCompany(cad.getCompany());
				sportFilterPayload.setRank(cad.getCadetRank());
				sportFilterPayload.setServiceId(cad.getServiceId());
				sportFilterPayload.setCourse(cad.getCourse());
				sportFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				sportFilterPayload.setNationality(cad.getNationality());
				SportsResult sportResult = findByServiceIdAndTermIdAndTermSession(cad.getServiceId(), cad.getTerm(),
						termSession);
				if (sportResult != null) {
					///// sort logic
					List<SportsSubjectResult> subjectResultList = sportResult.getSportsSubResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					sportResult.setSportsSubResult(subjectResultList);
					///// till here
					sportFilterPayload.setSportsResult(sportResult);
				} else {
					SportsResult ledMatResult = new SportsResult();
					List<SportsSubjectResult> leadershipSubjectResult = new ArrayList<SportsSubjectResult>();
					List<SportsSubject> result = sportsSubjectService.getByStatusAndTermSession(1, termSession);
					Integer totalMarks = 0;
					for (SportsSubject subject : result) {
						SportsSubjectResult subjectResult = new SportsSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarks(subject.getTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						subjectResult.setTermSession(termSession);
						leadershipSubjectResult.add(subjectResult);
					}

					ledMatResult.setSportsSubResult(leadershipSubjectResult);
					ledMatResult.setTotalMarks(totalMarks);
					ledMatResult.setServiceId(cad.getServiceId());
					ledMatResult.setTermId(cad.getTerm());
					ledMatResult.setStatus(1);
					ledMatResult.setTermSession(termSession);
					sportFilterPayload.setSportsResult(ledMatResult);
				}
				sportsFilterList.add(sportFilterPayload);
			}

			sportsPayload.setTotalRecords(totalRecords);
			sportsPayload.setSportsFilterPayload(sportsFilterList);
			return sportsPayload;
		} else {
			return null;
		}
	}

	@Override
	public SportsPayload getCadetsBySearch(Long termId, String termSession, String serviceId, Pageable pageable) {
		SportsPayload sportsPayload = null;
		List<SportsFilterPayload> sportsFilterList = new ArrayList<SportsFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		if (termId == null || termId == 0) {
			return sportsPayload;
		}
		if (serviceId != null && !serviceId.trim().equals("")) {
			cadetList = cadetRepo.findAllByTermAndTermSessionAndStatusAndServiceIdLike(termId, termSession, 1,
					"%" + serviceId + "%", pageable);
			totalRecords = cadetRepo
					.findAllByTermAndTermSessionAndStatusAndServiceIdLike(termId, termSession, 1, "%" + serviceId + "%")
					.size();
		} else {
			cadetList = cadetRepo.findAllByTermAndTermSessionAndStatus(termId, termSession, 1, pageable);
			totalRecords = cadetRepo.findAllByTermAndTermSessionAndStatus(termId, termSession, 1).size();
		}
		if (cadetList != null && cadetList.size() != 0) {
			sportsPayload = new SportsPayload();
			for (Cadet cad : cadetList) {
				SportsFilterPayload sportFilterPayload = new SportsFilterPayload();
				sportFilterPayload.setId(cad.getId());
				sportFilterPayload.setTermId(cad.getTerm());
				sportFilterPayload.setName(cad.getName());
				sportFilterPayload.setBattalian(cad.getBattalian());
				sportFilterPayload.setCompany(cad.getCompany());
				sportFilterPayload.setRank(cad.getCadetRank());
				sportFilterPayload.setServiceId(cad.getServiceId());
				sportFilterPayload.setCourse(cad.getCourse());
				sportFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				sportFilterPayload.setNationality(cad.getNationality());
				SportsResult sportResult = findByServiceIdAndTermIdAndTermSession(cad.getServiceId(), cad.getTerm(),
						termSession);
				if (sportResult != null) {
					///// sort logic
					List<SportsSubjectResult> subjectResultList = sportResult.getSportsSubResult();
					Collections.sort(subjectResultList, (sr1, sr2) -> {
						if (sr1.getSubjectId() > sr2.getSubjectId()) {
							return 1;
						} else {
							return -1;
						}
					});
					sportResult.setSportsSubResult(subjectResultList);
					///// till here
					sportFilterPayload.setSportsResult(sportResult);
				} else {
					SportsResult ledMatResult = new SportsResult();
					List<SportsSubjectResult> leadershipSubjectResult = new ArrayList<SportsSubjectResult>();
					List<SportsSubject> result = sportsSubjectService.getByStatusAndTermSession(1, termSession);
					Integer totalMarks = 0;
					for (SportsSubject subject : result) {
						SportsSubjectResult subjectResult = new SportsSubjectResult();
						totalMarks = totalMarks + subject.getTotalMarks();
						subjectResult.setSubjectId(subject.getId());
						subjectResult.setTotalMarks(subject.getTotalMarks());
						subjectResult.setSubjectName(subject.getSubjectName());
						subjectResult.setServiceId(cad.getServiceId());
						subjectResult.setTermId(cad.getTerm());
						subjectResult.setStatus(1);
						subjectResult.setTermSession(termSession);
						leadershipSubjectResult.add(subjectResult);
					}

					ledMatResult.setSportsSubResult(leadershipSubjectResult);
					ledMatResult.setTotalMarks(totalMarks);
					ledMatResult.setServiceId(cad.getServiceId());
					ledMatResult.setTermId(cad.getTerm());
					ledMatResult.setStatus(1);
					ledMatResult.setTermSession(termSession);
					sportFilterPayload.setSportsResult(ledMatResult);
				}
				sportsFilterList.add(sportFilterPayload);
			}

			sportsPayload.setTotalRecords(totalRecords);
			sportsPayload.setSportsFilterPayload(sportsFilterList);
			return sportsPayload;
		} else {
			return null;
		}
	}

	@Override
	public String updateBulkSportsResult(List<SportsFilterPayload> sportsPayloadList) {
		String result = "failed";
		int size = sportsPayloadList.size();
		if (size > 0) {
			for (SportsFilterPayload sportsPayload : sportsPayloadList) {
				SportsResult sportsResult = sportsPayload.getSportsResult();
				if (sportsResult != null && sportsResult.getId() != null && sportsResult.getId() != 0) {
					// update logic
					updateSportsResult(sportsResult);
				} else if (sportsResult != null) {
					// add logic
					List<SportsSubjectResult> sportsSubjectResult = sportsResult.getSportsSubResult();
					if (sportsSubjectResult != null) {
						for (SportsSubjectResult sportsSubResult : sportsSubjectResult) {
							sportsSubjectResultService.createSubResult(sportsSubResult);
						}
					}
					if (sportsResult.getObtainedMarks() != null) {
						sportsResult.setCreatedAt(new Date());
						createSportsResult(sportsResult);
					}

				}
			}
			result = "success";
		}
		return result;
	}
}
