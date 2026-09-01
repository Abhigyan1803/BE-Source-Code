package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EdAssessmentOqFinal;
import com.example.demo.repository.EdAssessmentOqFinalRepository;
import com.example.demo.service.EdAssessmentOqFinalService;

@Service
public class EdAssessmentOqFinalServiceImpl implements EdAssessmentOqFinalService {
	@Autowired
	private EdAssessmentOqFinalRepository repo;

	@Override
	public EdAssessmentOqFinal createEdAssessmentOqFinal(EdAssessmentOqFinal edAssessmentOqFinal) {
		// TODO Auto-generated method stub
		EdAssessmentOqFinal result = repo.save(edAssessmentOqFinal);
		return result;
	}

	@Override
	public EdAssessmentOqFinal findByServiceIdAndTermId(String serviceId, Long termId) {
		// TODO Auto-generated method stub
		EdAssessmentOqFinal result = repo.findByServiceIdAndTermId(serviceId, termId);
		return result;
	}

	@Override
	public List<EdAssessmentOqFinal> findByServiceId(String serviceId) {
		// TODO Auto-generated method stub
		List<EdAssessmentOqFinal> result = repo.findByServiceId(serviceId);
		return result;
	}

	@Override
	public EdAssessmentOqFinal updateEdAssessmentOqFinal(EdAssessmentOqFinal edAssessmentOqFinal) {
		// TODO Auto-generated method stub
		EdAssessmentOqFinal oqFinal = null;
		if (edAssessmentOqFinal != null && edAssessmentOqFinal.getId() != null && edAssessmentOqFinal.getId() != 0) {

			Optional<EdAssessmentOqFinal> aa = repo.findById(edAssessmentOqFinal.getId());
			if (aa.isPresent()) {

				oqFinal = aa.get();

				if (edAssessmentOqFinal.getPreMidTermObtainedMarks() != null) {

					oqFinal.setPreMidTermObtainedMarks(edAssessmentOqFinal.getPreMidTermObtainedMarks());
				}
				if (edAssessmentOqFinal.getPreFinalTermObtainedMarks() != null) {

					oqFinal.setPreFinalTermObtainedMarks(edAssessmentOqFinal.getPreFinalTermObtainedMarks());
				}
				if (edAssessmentOqFinal.getAvgPrePostObtainedMarks() != null) {

					oqFinal.setAvgPrePostObtainedMarks(edAssessmentOqFinal.getAvgPrePostObtainedMarks());
				}
				if (edAssessmentOqFinal.getDcCiObtainedMarks() != null) {

					oqFinal.setDcCiObtainedMarks(edAssessmentOqFinal.getDcCiObtainedMarks());
				}
				if (edAssessmentOqFinal.getComdtObtainedMarks() != null) {

					oqFinal.setComdtObtainedMarks(edAssessmentOqFinal.getComdtObtainedMarks());
				}
				if (edAssessmentOqFinal.getGrandObtaionedMarks() != null) {

					oqFinal.setGrandObtaionedMarks(edAssessmentOqFinal.getGrandObtaionedMarks());
				}
			}
			oqFinal = repo.save(oqFinal);
		}
		return oqFinal;
	}
}
