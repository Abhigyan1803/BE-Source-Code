package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Counselling;
import com.example.demo.model.ObsnSheet;

public interface ObsnSheetService {
	List<ObsnSheet> getObsnSheetList(Integer status, String serviceId);

	List<ObsnSheet> getObsnSheetList(Long id);

	List<ObsnSheet> addObsnSheet(List<ObsnSheet> obsnSheet);


}
