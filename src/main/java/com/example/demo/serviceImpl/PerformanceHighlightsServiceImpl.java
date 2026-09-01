package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PerformanceHighlights;
import com.example.demo.repository.PerformanceHighlightsRepo;
import com.example.demo.service.PerformanceHighlightsService;

@Service
public class PerformanceHighlightsServiceImpl implements PerformanceHighlightsService {

	@Autowired
	PerformanceHighlightsRepo performanceHighlightsRepo;

	@Override
	public PerformanceHighlights createPerformanceHighlights(PerformanceHighlights performanceHighlights) {
		return performanceHighlightsRepo.save(performanceHighlights);
	}

	@Override
	public List<PerformanceHighlights> getAllPerformanceHighlightsList(Integer status, Integer battalianId) {
		if (status == 2 && battalianId == 0) {
			List<PerformanceHighlights> list = performanceHighlightsRepo.findAllByOrderByIdDesc();
			return list;
		} else if (battalianId > 0 && battalianId < 5 && status < 2) {
			List<PerformanceHighlights> list = performanceHighlightsRepo.findByStatusAndBattalianIdOrderByIdDesc(status,
					battalianId);
			return list;
		} else if (battalianId > 0 && battalianId < 5 && status == 2) {
			List<PerformanceHighlights> list = performanceHighlightsRepo.findByBattalianIdOrderByIdDesc(battalianId);
			return list;
		} else if (battalianId == 0 && status < 2) {
			List<PerformanceHighlights> list = performanceHighlightsRepo.findByStatusOrderByIdDesc(status);
			return list;
		} else {
			List<PerformanceHighlights> list = performanceHighlightsRepo.findAllByOrderByIdDesc();
			return list;
		}
	}

	@Override
	public Optional<PerformanceHighlights> getPerformanceById(Integer id) {
		Optional<PerformanceHighlights> list = performanceHighlightsRepo.findById(id);
		return list;
	}

	@Override
	public PerformanceHighlights updatePerformance(PerformanceHighlights performanceHighlights) {
		PerformanceHighlights perform = null;
		Optional<PerformanceHighlights> p = performanceHighlightsRepo.findById(performanceHighlights.getId());
		if (p.isPresent()) {

			perform = p.get();

//			if (performanceHighlights.getName() != null) {
//				perform.setName(performanceHighlights.getName());
//			}
//			if (performanceHighlights.getPerformanceRank() != null) {
//				perform.setPerformanceRank(performanceHighlights.getPerformanceRank());
//			}
			if (performanceHighlights.getStatus() != null) {
				perform.setStatus(performanceHighlights.getStatus());
			}
			if (performanceHighlights.getBattalian() != null) {
				perform.setBattalian(performanceHighlights.getBattalian());
			}
			if (performanceHighlights.getCompany() != null) {
				perform.setCompany(performanceHighlights.getCompany());
			}
			if (performanceHighlights.getDescription() != null) {
				perform.setDescription(performanceHighlights.getDescription());
			}
//			if (performanceHighlights.getImage() != null) {
//				perform.setImage(performanceHighlights.getImage());
//			}

			perform.setUpdatedAt(new Date());
		}
		PerformanceHighlights list = performanceHighlightsRepo.save(perform);
		return list;
	}

	@Override
	public List<PerformanceHighlights> getAllSyllabusList() {
		// TODO Auto-generated method stub
		return null;
	}

}
