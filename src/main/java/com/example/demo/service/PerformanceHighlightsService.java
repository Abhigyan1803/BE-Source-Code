package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.PerformanceHighlights;

public interface PerformanceHighlightsService {

	PerformanceHighlights createPerformanceHighlights(PerformanceHighlights performanceHighlights);

	List<PerformanceHighlights> getAllSyllabusList();

	List<PerformanceHighlights> getAllPerformanceHighlightsList(Integer status, Integer battalianId);

	Optional<PerformanceHighlights> getPerformanceById(Integer id);

	PerformanceHighlights updatePerformance(PerformanceHighlights performanceHighlights);

}