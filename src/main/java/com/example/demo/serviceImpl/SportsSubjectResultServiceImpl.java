package com.example.demo.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SportsSubjectResult;
import com.example.demo.repository.SportsSubjectResultRepo;
import com.example.demo.service.SportsSubjectResultService;

@Service
public class SportsSubjectResultServiceImpl implements SportsSubjectResultService {
	@Autowired
	private SportsSubjectResultRepo sportsSubjectResultRepo;

	@Override
	public SportsSubjectResult createSubResult(SportsSubjectResult sportsSubjectResult) {
		// TODO Auto-generated method stub
		return sportsSubjectResultRepo.save(sportsSubjectResult);
	}

	@Override
	public SportsSubjectResult updateSubResult(SportsSubjectResult sprtsSubjectResult) {
		// TODO Auto-generated method stub
		if (sprtsSubjectResult != null) {
			Optional<SportsSubjectResult> getSportsSubResult = sportsSubjectResultRepo
					.findById(sprtsSubjectResult.getId());
			SportsSubjectResult subjectResult = getSportsSubResult.get();
			subjectResult.setObtainedMarks(sprtsSubjectResult.getObtainedMarks());
			subjectResult.setSubjectId(sprtsSubjectResult.getSubjectId());
			// subjectResult.setStatus(EQTNSubjectResult.getStatus());
			// subjectResult.setTermId(EQTNSubjectResult.getTermId());
			subjectResult.setTotalMarks(sprtsSubjectResult.getTotalMarks());
			subjectResult.setUpdatedAt(sprtsSubjectResult.getUpdatedAt());
			return sportsSubjectResultRepo.save(subjectResult);
		}
		return null;
	}

	@Override
	public SportsSubjectResult getSubResultById(Long id) {
		// TODO Auto-generated method stub

		Optional<SportsSubjectResult> getSportsSubjectResult = sportsSubjectResultRepo.findById(id);
		return getSportsSubjectResult.get();
	}
}
