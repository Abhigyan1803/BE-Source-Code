package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AcademicCreditForExcellenceSubjectResult;
import com.example.demo.repository.AcademicCreditForExcellenceSubjectResultRepo;
import com.example.demo.service.AcademicCreditForExcellenceSubjectResultService;

@Service
public class AcademicCreditForExcellenceSubjectResultServiceImpl
		implements AcademicCreditForExcellenceSubjectResultService {
	@Autowired
	private AcademicCreditForExcellenceSubjectResultRepo CreditForExcellenceSubjectResultRepo;

	@Override
	public AcademicCreditForExcellenceSubjectResult createSubResult(
			AcademicCreditForExcellenceSubjectResult leadershipSubjectResult) {
		// TODO Auto-generated method stub
		AcademicCreditForExcellenceSubjectResult saveCampSubRslt = CreditForExcellenceSubjectResultRepo
				.save(leadershipSubjectResult);
		return saveCampSubRslt;
	}

	@Override
	public AcademicCreditForExcellenceSubjectResult getSubResultById(Long id) {
		// TODO Auto-generated method stub

		Optional<AcademicCreditForExcellenceSubjectResult> getAcademicCreditForExcellenceSubjectResult = CreditForExcellenceSubjectResultRepo
				.findById(id);
		return getAcademicCreditForExcellenceSubjectResult.get();
	}
//	@Override
//	public AcademicLeadershipMatrixResult findByServiceIdAndTermId(String serviceId, int termId) {
//		// TODO Auto-generated method stub
//		AcademicLeadershipMatrixResult getAcademicLeadershipMatrixSubjectResult = LeadershipMatrixSubjectResultRepo
//				.findByServiceIdAndTermId(serviceId, termId);
//		return getAcademicLeadershipMatrixSubjectResult;
//	}

//	@Override
//	public List<AcademicLeadershipMatrixSubjectResult> getSubResultAll() {
//		// TODO Auto-generated method stub
//		return LeadershipMatrixSubjectResultRepo.findAll();
//	}

	@Override
	public AcademicCreditForExcellenceSubjectResult updateSubResult(
			AcademicCreditForExcellenceSubjectResult creditForExcellenceSubjectResult) {
		// TODO Auto-generated method stub
		if (creditForExcellenceSubjectResult != null) {
			Optional<AcademicCreditForExcellenceSubjectResult> getCreditForExcellenceSubResult = CreditForExcellenceSubjectResultRepo
					.findById(creditForExcellenceSubjectResult.getId());
			AcademicCreditForExcellenceSubjectResult subResult = getCreditForExcellenceSubResult.get();
			subResult.setObtainedMarks(creditForExcellenceSubjectResult.getObtainedMarks());
			subResult.setSubjectId(creditForExcellenceSubjectResult.getSubjectId());
			// CreditForExcellenceSubjectResult.setStatus(CreditForExcellenceSubjectResult.getStatus());
			// CreditForExcellenceSubjectResult.setTermId(CreditForExcellenceSubjectResult.getTermId());
			subResult.setTotalMarks(creditForExcellenceSubjectResult.getTotalMarks());
			subResult.setUpdatedAt(new Date());
			return CreditForExcellenceSubjectResultRepo.save(subResult);
		}
		return null;

	}

	@Override
	public void createCreditForExcellenceSubResult(
			AcademicCreditForExcellenceSubjectResult creditForExcellenceSubRslt) {
		// TODO Auto-generated method stub

	}

}
