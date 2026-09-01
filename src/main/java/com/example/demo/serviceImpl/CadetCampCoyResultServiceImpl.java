package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.CadetCampCoyCdrResult;
import com.example.demo.repository.CadetCampCoyCdrRepo;
import com.example.demo.service.CadetCampCoyService;

@Service
public class CadetCampCoyResultServiceImpl implements CadetCampCoyService{

	
	@Autowired
	CadetCampCoyCdrRepo cadetCampCoyCdrRepo;
	
	
	@Override
	public List<CadetCampCoyCdrResult> addCampCoyResult(List<CadetCampCoyCdrResult> result) {
			List<CadetCampCoyCdrResult> saveResult = cadetCampCoyCdrRepo.saveAll(result);
			return saveResult;
	}

	@Override
	public List<CadetCampCoyCdrResult> getCadetCampCoyResult(String serviceId, Long termId) {
		List<CadetCampCoyCdrResult> list = cadetCampCoyCdrRepo.findByServiceIdAndTermId(serviceId, termId);
		return list;
	}
	
	@Override
	public List<CadetCampCoyCdrResult> updateResult(List<CadetCampCoyCdrResult> result) {
	
		List<CadetCampCoyCdrResult> list = new ArrayList<>();
		for (CadetCampCoyCdrResult cadetResult : result) {

			Optional<CadetCampCoyCdrResult> cadRes = cadetCampCoyCdrRepo.findByServiceIdAndTermIdAndCampAttributesId(
					cadetResult.getServiceId(), cadetResult.getTermId(), cadetResult.getCampAttributes().getId());
			CadetCampCoyCdrResult res = null;
			if (cadRes.isPresent()) {
				res = cadRes.get();

				if (cadetResult.getServiceId() != null) {
					res.setServiceId(cadetResult.getServiceId());
				}
				if (cadetResult.getTermId() != null) {
					res.setTermId(cadetResult.getTermId());
				}
				if (cadetResult.getStatus() != null) {
					res.setStatus(cadetResult.getStatus());
				}
				if (cadetResult.getCoyCdrMarks() != null) {
					res.setCoyCdrMarks(cadetResult.getCoyCdrMarks());
				}
			}
			CadetCampCoyCdrResult data = cadetCampCoyCdrRepo.save(res);
			list.add(data);
		}
		return list;
	}

	
	
}
