package com.example.demo.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TRG_EQTNSubjectResult;
import com.example.demo.repository.TRG_EQTNSubjectResultRepo;
import com.example.demo.service.TRG_EQTNSubjectResultService;

@Service
public class TRG_EQTNSubjectResultServiceImpl implements TRG_EQTNSubjectResultService {
	@Autowired
	private TRG_EQTNSubjectResultRepo eQTNSubjectResultRepo;
//	@Override
//	public TRG_EQTNSubjectResult createTRG_EQTNResult(TRG_EQTNResult tRG_EQTNResult) {
//		// TODO Auto-generated method stub
//		TRG_EQTNSubjectResult saveTRG_EQTNSubRslt = EQTNSubjectResultRepo.save(tRG_EQTNSubjectResult);
//		return saveTRG_EQTNSubRslt;
//	}

	@Override
	public TRG_EQTNSubjectResult createSubResult(TRG_EQTNSubjectResult tRG_EQTNSubjectResult) {
		// TODO Auto-generated method stub

		return eQTNSubjectResultRepo.save(tRG_EQTNSubjectResult);
	}

	@Override
	public TRG_EQTNSubjectResult getSubResultById(Long id) {
		// TODO Auto-generated method stub

		Optional<TRG_EQTNSubjectResult> getTRG_EQTNSubjectResult = eQTNSubjectResultRepo.findById(id);
		return getTRG_EQTNSubjectResult.get();
	}

	@Override
	public TRG_EQTNSubjectResult updateSubResult(TRG_EQTNSubjectResult eQTNSubjectResult) {
		// TODO Auto-generated method stub
		if (eQTNSubjectResult != null) {
			Optional<TRG_EQTNSubjectResult> getEQTNSubResult = eQTNSubjectResultRepo
					.findById(eQTNSubjectResult.getId());
			TRG_EQTNSubjectResult subjectResult = getEQTNSubResult.get();
			subjectResult.setObtainedMarks(eQTNSubjectResult.getObtainedMarks());
			subjectResult.setSubjectId(eQTNSubjectResult.getSubjectId());
			subjectResult.setRemarks(eQTNSubjectResult.getRemarks());
			// subjectResult.setStatus(EQTNSubjectResult.getStatus());
			// subjectResult.setTermId(EQTNSubjectResult.getTermId());
			subjectResult.setTotalMarks(eQTNSubjectResult.getTotalMarks());
			subjectResult.setUpdatedAt(eQTNSubjectResult.getUpdatedAt());
			return eQTNSubjectResultRepo.save(subjectResult);
		}
		return null;

	}

}
