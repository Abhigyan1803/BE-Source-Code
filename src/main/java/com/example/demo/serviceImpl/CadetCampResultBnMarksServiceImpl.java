package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CadetCampBnCdrResult;
import com.example.demo.repository.CadetCampBnRepo;
import com.example.demo.service.CadetCampBnMarksService;

@Service
public class CadetCampResultBnMarksServiceImpl implements CadetCampBnMarksService{

	@Autowired
	CadetCampBnRepo cadetCampBnRepo;
	
	@Override
	public List<CadetCampBnCdrResult> addCampBnResult(List<CadetCampBnCdrResult> result) {
			List<CadetCampBnCdrResult> saveResult = cadetCampBnRepo.saveAll(result);
			return saveResult;
	}

	@Override
	public List<CadetCampBnCdrResult> getCadetCampBnResult(String serviceId, Long termId) {
		List<CadetCampBnCdrResult> list = cadetCampBnRepo.findByServiceIdAndTermId(serviceId, termId);
		return list;
	}
	
	@Override
	public List<CadetCampBnCdrResult> updateResultBn(List<CadetCampBnCdrResult> result) {
	
		List<CadetCampBnCdrResult> list = new ArrayList<>();
		for (CadetCampBnCdrResult cadetResult : result) {

			Optional<CadetCampBnCdrResult> cadRes = cadetCampBnRepo.findByServiceIdAndTermIdAndCampAttributesId(
					cadetResult.getServiceId(), cadetResult.getTermId(), cadetResult.getCampAttributes().getId());
			CadetCampBnCdrResult res = null;
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
				if (cadetResult.getBnCdrMarks() != null) {
					res.setBnCdrMarks(cadetResult.getBnCdrMarks());
				}
			}
			CadetCampBnCdrResult data = cadetCampBnRepo.save(res);
			list.add(data);
		}
		return list;
	}

}
