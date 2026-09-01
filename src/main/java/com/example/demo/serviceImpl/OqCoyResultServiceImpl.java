package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.OqCoyResult;
import com.example.demo.repository.OqCoyResultRepo;
import com.example.demo.service.OqCoyResultService;

@Service
public class OqCoyResultServiceImpl implements OqCoyResultService{

	
	@Autowired
	OqCoyResultRepo oqCoyResultRepo;
	
	
	@Override
	public List<OqCoyResult> addOqCoyResult(List<OqCoyResult> result) {
			List<OqCoyResult> saveResult = oqCoyResultRepo.saveAll(result);
			return saveResult;
	}

	@Override
	public List<OqCoyResult> getOqCoyResult(String serviceId, Long termId) {
		List<OqCoyResult> list = oqCoyResultRepo.findByServiceIdAndTermId(serviceId, termId);
		return list;
	}
	
	@Override
	public List<OqCoyResult> updateResult(List<OqCoyResult> result) {
	
		List<OqCoyResult> list = new ArrayList<>();
		for (OqCoyResult coyResult : result) {

			Optional<OqCoyResult> coyRes = oqCoyResultRepo.findByServiceIdAndTermIdAndOqSubjectAttributeId(
					coyResult.getServiceId(),coyResult.getTermId(),coyResult.getOqSubjectAttribute().getId());
			OqCoyResult res = null;
			if (coyRes.isPresent()) {
				res = coyRes.get();

				if (coyResult.getServiceId() != null) {
					res.setServiceId(coyResult.getServiceId());
				}
				if (coyResult.getTermId() != null) {
					res.setTermId(coyResult.getTermId());
				}
				if (coyResult.getStatus() != null) {
					res.setStatus(coyResult.getStatus());
				}
				if (coyResult.getCoyCdrMarks() != null) {
					res.setCoyCdrMarks(coyResult.getCoyCdrMarks());
				}
			}
			OqCoyResult data = oqCoyResultRepo.save(res);
			list.add(data);
		}
		return list;
	}

	
	
}
