package com.example.demo.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AcademicLeadershipMatrixSubjectResult;
import com.example.demo.repository.AcademicLeadershipMatrixSubjectResultRepo;
import com.example.demo.service.AcademicLeadershipMatrixSubjectResultService;

@Service
public class AcademicLeadershipMatrixSubjectResultServiceImpl implements AcademicLeadershipMatrixSubjectResultService {

	@Autowired
	private AcademicLeadershipMatrixSubjectResultRepo LeadershipMatrixSubjectResultRepo;

	@Override
	public AcademicLeadershipMatrixSubjectResult createSubResult(
			AcademicLeadershipMatrixSubjectResult leadershipSubjectResult) {
		// TODO Auto-generated method stub
		AcademicLeadershipMatrixSubjectResult saveCampSubRslt = LeadershipMatrixSubjectResultRepo
				.save(leadershipSubjectResult);
		return saveCampSubRslt;
	}

	@Override
	public AcademicLeadershipMatrixSubjectResult getSubResultById(Long id) {
		// TODO Auto-generated method stub

		Optional<AcademicLeadershipMatrixSubjectResult> getAcademicLeadershipMatrixSubjectResult = LeadershipMatrixSubjectResultRepo
				.findById(id);
		return getAcademicLeadershipMatrixSubjectResult.get();
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
	public AcademicLeadershipMatrixSubjectResult updateSubResult(
			AcademicLeadershipMatrixSubjectResult leadershipSubjectResult) {
		AcademicLeadershipMatrixSubjectResult leadershipSubResult = null;
		// TODO Auto-generated method stub
		if (leadershipSubjectResult != null) {
			Optional<AcademicLeadershipMatrixSubjectResult> getLeadershipSubResult = LeadershipMatrixSubjectResultRepo
					.findById(leadershipSubjectResult.getId());
			leadershipSubResult = getLeadershipSubResult.get();
			leadershipSubResult.setObtainedMarks(leadershipSubjectResult.getObtainedMarks());
			leadershipSubResult.setSubjectId(leadershipSubjectResult.getSubjectId());
			leadershipSubResult.setStatus(leadershipSubjectResult.getStatus());
			leadershipSubResult.setTermId(leadershipSubjectResult.getTermId());
			leadershipSubResult.setTotalMarks(leadershipSubjectResult.getTotalMarks());
			leadershipSubResult.setUpdatedAt(leadershipSubjectResult.getUpdatedAt());
		}

		return LeadershipMatrixSubjectResultRepo.save(leadershipSubjectResult);
	}

}
