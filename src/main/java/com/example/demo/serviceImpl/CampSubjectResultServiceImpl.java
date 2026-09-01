package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CampSubjectResult;
import com.example.demo.repository.CampSubjectResultRepo;
import com.example.demo.service.CampSubjectResultService;

@Service
public class CampSubjectResultServiceImpl implements CampSubjectResultService {

	@Autowired
	private CampSubjectResultRepo campSubjectResultRepo;

	@Override
	public CampSubjectResult createSubResult(CampSubjectResult campSubjectResult) {
		// TODO Auto-generated method stub
		CampSubjectResult saveCampSubRslt = campSubjectResultRepo.save(campSubjectResult);
		return saveCampSubRslt;
	}

	@Override
	public CampSubjectResult getSubResultById(Long id) {
		// TODO Auto-generated method stub

		Optional<CampSubjectResult> getCampSubResult = campSubjectResultRepo.findById(id);
		return getCampSubResult.get();
	}

	@Override
	public List<CampSubjectResult> getSubResultAll() {
		// TODO Auto-generated method stub
		return campSubjectResultRepo.findAll();
	}

	@Override
	public CampSubjectResult updateSubResult(CampSubjectResult campSubjectResult) {
		CampSubjectResult campSubResult = null;
		// TODO Auto-generated method stub
		if (campSubjectResult != null) {
			Optional<CampSubjectResult> getCampSubResult = campSubjectResultRepo.findById(campSubjectResult.getId());
			campSubResult = getCampSubResult.get();
			campSubResult.setObtainedMarks(campSubjectResult.getObtainedMarks());
			campSubResult.setSubjectId(campSubjectResult.getSubjectId());
			campSubResult.setStatus(campSubjectResult.getStatus());
			campSubResult.setTermId(campSubjectResult.getTermId());
			campSubResult.setTotalMarks(campSubjectResult.getTotalMarks());
			campSubResult.setUpdatedAt(campSubjectResult.getUpdatedAt());
		}

		return campSubjectResultRepo.save(campSubResult);
	}

}
