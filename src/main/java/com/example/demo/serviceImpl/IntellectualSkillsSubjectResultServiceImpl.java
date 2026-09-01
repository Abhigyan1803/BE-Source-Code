package com.example.demo.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.IntellectualSkillsSubjectResult;
import com.example.demo.repository.IntellectualSkillsSubjectResultRepo;
import com.example.demo.service.IntellectualSkillsSubjectResultService;

@Service
public class IntellectualSkillsSubjectResultServiceImpl implements IntellectualSkillsSubjectResultService {
	@Autowired
	private IntellectualSkillsSubjectResultRepo intellectualSkillsSubjectResultRepo;

	@Override
	public IntellectualSkillsSubjectResult createSubResult(
			IntellectualSkillsSubjectResult intellectualSkillsSubResult) {
		// TODO Auto-generated method stub
		return intellectualSkillsSubjectResultRepo.save(intellectualSkillsSubResult);

	}

	@Override
	public IntellectualSkillsSubjectResult getSubResultById(Long id) {
		// TODO Auto-generated method stub

		Optional<IntellectualSkillsSubjectResult> getIntellectualSkillsSubjectResult = intellectualSkillsSubjectResultRepo
				.findById(id);
		return getIntellectualSkillsSubjectResult.get();
	}

	@Override
	public IntellectualSkillsSubjectResult updateSubResult(
			IntellectualSkillsSubjectResult intellectualSkillsSubjectResult) {
		// TODO Auto-generated method stub
		if (intellectualSkillsSubjectResult != null) {
			Optional<IntellectualSkillsSubjectResult> getIntellectualSkillsSubResult = intellectualSkillsSubjectResultRepo
					.findById(intellectualSkillsSubjectResult.getId());
			IntellectualSkillsSubjectResult subjectResult = getIntellectualSkillsSubResult.get();
			subjectResult.setMidObtainedMarks(intellectualSkillsSubjectResult.getMidObtainedMarks());
			subjectResult.setFinalObtainedMarks(intellectualSkillsSubjectResult.getFinalObtainedMarks());
			subjectResult.setSubjectId(intellectualSkillsSubjectResult.getSubjectId());
			// subjectResult.setStatus(EQTNSubjectResult.getStatus());
			// subjectResult.setTermId(EQTNSubjectResult.getTermId());
			subjectResult.setMidTotalMarks(intellectualSkillsSubjectResult.getMidTotalMarks());
			subjectResult.setFinalTotalMarks(intellectualSkillsSubjectResult.getFinalTotalMarks());
			subjectResult.setUpdatedAt(intellectualSkillsSubjectResult.getUpdatedAt());
			return intellectualSkillsSubjectResultRepo.save(subjectResult);
		}
		return null;
	}
}
