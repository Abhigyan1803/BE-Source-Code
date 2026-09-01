package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.GCActivities;

public interface GCActivitiesService {

	GCActivities createPerformanceHighlights(GCActivities gCActivities);

	List<GCActivities> getAllGCActivitiesList(Integer status, Integer battalianId);

	Optional<GCActivities> getGCActivitiesById(Integer id);

	GCActivities updateActivities(GCActivities gCActivities);

}
