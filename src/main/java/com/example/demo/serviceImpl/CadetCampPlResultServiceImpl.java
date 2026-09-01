package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CadetCampPlCdrResult;
import com.example.demo.repository.CadetCampPlRepo;
import com.example.demo.service.CadetPlCdrCampResultService;

@Service
public class CadetCampPlResultServiceImpl  implements CadetPlCdrCampResultService{

	
	@Autowired
	CadetCampPlRepo cadetCampPlRepo;
	
	
	@Override
	public List<CadetCampPlCdrResult> addCampPlResult(List<CadetCampPlCdrResult> result) {
			List<CadetCampPlCdrResult> saveResult = cadetCampPlRepo.saveAll(result);
			return saveResult;
	}

	@Override
	public List<CadetCampPlCdrResult> getCadetResult(String serviceId, Long termId) {
		List<CadetCampPlCdrResult> list = cadetCampPlRepo.findByServiceIdAndTermId(serviceId, termId);
		return list;
	}

	@Override
	public List<CadetCampPlCdrResult> updateResult(List<CadetCampPlCdrResult> result) {
	
		List<CadetCampPlCdrResult> list = new ArrayList<>();
		for (CadetCampPlCdrResult cadetResult : result) {

			Optional<CadetCampPlCdrResult> cadRes = cadetCampPlRepo.findByServiceIdAndTermIdAndCampAttributesId(
					cadetResult.getServiceId(), cadetResult.getTermId(), cadetResult.getCampAttributes().getId());
			CadetCampPlCdrResult res = null;
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
				if (cadetResult.getPlCdrMarks() != null) {
					res.setPlCdrMarks(cadetResult.getPlCdrMarks());
				}
			}
			CadetCampPlCdrResult data = cadetCampPlRepo.save(res);
			list.add(data);
		}
		return list;
	}

}
