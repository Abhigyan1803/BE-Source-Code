package com.example.demo.service;

import java.util.List;

import com.example.demo.model.EdCampMarks;

public interface EdCampMarksService {

	EdCampMarks addEdCampMarks(EdCampMarks edCampMarks);

	List<EdCampMarks> getByServiceId(String serviceId);

	EdCampMarks getByServiceIdAndTermId(String serviceId, Long termId);

	EdCampMarks updateEdCampMarks(EdCampMarks edCampMarks);

}
