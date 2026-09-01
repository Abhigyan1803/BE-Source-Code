package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GCActivities;
import com.example.demo.repository.GCActivitiesRepo;
import com.example.demo.service.GCActivitiesService;

@Service
public class GCActivitiesServiceImpl implements GCActivitiesService {

	@Autowired
	GCActivitiesRepo gcRepo;

	@Override
	public GCActivities createPerformanceHighlights(GCActivities gCActivities) {
		return gcRepo.save(gCActivities);
	}

	@Override
	public List<GCActivities> getAllGCActivitiesList(Integer status, Integer battalianId) {
		if (status == 2 && battalianId == 0) {
			List<GCActivities> list = gcRepo.findAllByOrderByIdDesc();
			return list;
		} else if (battalianId > 0 && battalianId < 5 && status < 2) {
			List<GCActivities> list = gcRepo.findByStatusAndBattalianIdOrderByIdDesc(status, battalianId);
			return list;
		} else if (battalianId > 0 && battalianId < 5 && status == 2) {
			List<GCActivities> list = gcRepo.findByBattalianIdOrderByIdDesc(battalianId);
			return list;
		} else if (battalianId == 0 && status < 2) {
			List<GCActivities> list = gcRepo.findByStatusOrderByIdDesc(status);
			return list;
		} else {
			List<GCActivities> list = gcRepo.findAllByOrderByIdDesc();
			return list;
		}
	}

	@Override
	public Optional<GCActivities> getGCActivitiesById(Integer id) {
		Optional<GCActivities> list = gcRepo.findById(id);
		return list;
	}

	@Override
	public GCActivities updateActivities(GCActivities gCActivities) {
		GCActivities activities = null;
		Optional<GCActivities> g = gcRepo.findById(gCActivities.getId());
		if (g.isPresent()) {

			activities = g.get();

			if (gCActivities.getStatus() != null) {
				activities.setStatus(gCActivities.getStatus());
			}
			if (gCActivities.getBattalian() != null) {
				activities.setBattalian(gCActivities.getBattalian());
			}

			if (gCActivities.getImage() != null) {
				activities.setImage(gCActivities.getImage());
			}

			activities.setUpdatedAt(new Date());
		}
		GCActivities list = gcRepo.save(activities);
		return list;
	}

}
