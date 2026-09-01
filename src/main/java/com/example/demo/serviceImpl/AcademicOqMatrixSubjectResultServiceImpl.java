package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AcademicOqMatrixSubjectResult;
import com.example.demo.repository.AcademicOqMatrixSubjectResultRepo;
import com.example.demo.service.AcademicOqMatrixSubjectResultService;

@Service
public class AcademicOqMatrixSubjectResultServiceImpl implements AcademicOqMatrixSubjectResultService {

	@Autowired
	private AcademicOqMatrixSubjectResultRepo academicOqMatrixSubjectResultRepo;

	@Override
	public AcademicOqMatrixSubjectResult createSubResult(AcademicOqMatrixSubjectResult oqSubjectResult) {
		// TODO Auto-generated method stub
		AcademicOqMatrixSubjectResult saveOqSubRslt = academicOqMatrixSubjectResultRepo.save(oqSubjectResult);
		return saveOqSubRslt;
	}

	@Override
	public AcademicOqMatrixSubjectResult getSubResultById(Long id) {
		// TODO Auto-generated method stub

		Optional<AcademicOqMatrixSubjectResult> getOqSubResult = academicOqMatrixSubjectResultRepo.findById(id);
		return getOqSubResult.get();
	}

	@Override
	public List<AcademicOqMatrixSubjectResult> getSubResultAll() {
		// TODO Auto-generated method stub
		return academicOqMatrixSubjectResultRepo.findAll();
	}

	@Override
	public AcademicOqMatrixSubjectResult updateSubResult(AcademicOqMatrixSubjectResult oqSubjectResult) {
		AcademicOqMatrixSubjectResult oqSubResult = null;
		// TODO Auto-generated method stub
		if (oqSubjectResult != null) {
			Optional<AcademicOqMatrixSubjectResult> getOqSubResult = academicOqMatrixSubjectResultRepo
					.findById(oqSubjectResult.getId());
			oqSubResult = getOqSubResult.get();
			oqSubResult.setObtainedMarks(oqSubjectResult.getObtainedMarks());
			oqSubResult.setSubjectId(oqSubjectResult.getSubjectId());
			oqSubResult.setStatus(oqSubjectResult.getStatus());
			oqSubResult.setTermId(oqSubjectResult.getTermId());
			oqSubResult.setTotalMarks(oqSubjectResult.getTotalMarks());
			oqSubResult.setUpdatedAt(oqSubjectResult.getUpdatedAt());
		}

		return academicOqMatrixSubjectResultRepo.save(oqSubResult);
	}

}
