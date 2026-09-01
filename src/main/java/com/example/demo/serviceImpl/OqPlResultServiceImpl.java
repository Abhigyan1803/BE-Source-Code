package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.OqPlResult;
import com.example.demo.repository.OqPlResultRepo;
import com.example.demo.service.OqPlResultService;

@Service
public class OqPlResultServiceImpl implements OqPlResultService{

		@Autowired
		OqPlResultRepo oqPlResultRepo;
		
		
		@Override
		public List<OqPlResult> addOqPlResult(List<OqPlResult> result) {
				List<OqPlResult> saveResult = oqPlResultRepo.saveAll(result);
				return saveResult;
		}

		@Override
		public List<OqPlResult> getOqPlResult(String serviceId, Long termId) {
			List<OqPlResult> list = oqPlResultRepo.findByServiceIdAndTermId(serviceId, termId);
			return list;
		}

		@Override
		public List<OqPlResult> updateResult(List<OqPlResult> result) {
		
			List<OqPlResult> list = new ArrayList<>();
			for (OqPlResult plResult : result) {

				Optional<OqPlResult> plRes = oqPlResultRepo.findByServiceIdAndTermIdAndOqSubjectAttributeId(
						plResult.getServiceId(), plResult.getTermId(), plResult.getOqSubjectAttribute().getId());
				OqPlResult res = null;
				if (plRes.isPresent()) {
					res = plRes.get();

					if (plResult.getServiceId() != null) {
						res.setServiceId(plResult.getServiceId());
					}
					if (plResult.getTermId() != null) {
						res.setTermId(plResult.getTermId());
					}
					if (plResult.getStatus() != null) {
						res.setStatus(plResult.getStatus());
					}
					if (plResult.getPlCdrMarks() != null) {
						res.setPlCdrMarks(plResult.getPlCdrMarks());
					}
				}
				OqPlResult data = oqPlResultRepo.save(res);
				list.add(data);
			}
			return list;
		}

}
