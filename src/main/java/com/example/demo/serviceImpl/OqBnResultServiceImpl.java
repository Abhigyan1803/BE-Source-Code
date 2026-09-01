package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.OqBnResult;
import com.example.demo.repository.OqBnResultRepo;
import com.example.demo.service.OqBnResultService;

@Service
public class OqBnResultServiceImpl implements OqBnResultService{

	@Autowired
	OqBnResultRepo oqBnResultRepo;
	
	
	@Override
	public List<OqBnResult> addOqBnResult(List<OqBnResult> result) {
			List<OqBnResult> saveResult = oqBnResultRepo.saveAll(result);
			return saveResult;
	}

	@Override
	public List<OqBnResult> getOqBnResult(String serviceId, Long termId) {
		List<OqBnResult> list = oqBnResultRepo.findByServiceIdAndTermId(serviceId, termId);
		return list;
	}
	
	@Override
	public List<OqBnResult> updateResult(List<OqBnResult> result) {
	
		List<OqBnResult> list = new ArrayList<>();
		for (OqBnResult bnResult : result) {

			Optional<OqBnResult> bnRes = oqBnResultRepo.findByServiceIdAndTermIdAndOqSubjectAttributeId(
					bnResult.getServiceId(),bnResult.getTermId(),bnResult.getOqSubjectAttribute().getId());
			OqBnResult res = null;
			if (bnRes.isPresent()) {
				res = bnRes.get();

				if (bnResult.getServiceId() != null) {
					res.setServiceId(bnResult.getServiceId());
				}
				if (bnResult.getTermId() != null) {
					res.setTermId(bnResult.getTermId());
				}
				if (bnResult.getStatus() != null) {
					res.setStatus(bnResult.getStatus());
				}
				if (bnResult.getBnCdrMarks() != null) {
					res.setBnCdrMarks(bnResult.getBnCdrMarks());
				}
			}
			OqBnResult data = oqBnResultRepo.save(res);
			list.add(data);
		}
		return list;
	}

	
	
}
