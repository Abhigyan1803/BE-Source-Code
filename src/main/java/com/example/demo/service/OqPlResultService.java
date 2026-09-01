package com.example.demo.service;

import java.util.List;

import com.example.demo.model.OqPlResult;

public interface OqPlResultService {

		List<OqPlResult> addOqPlResult(List<OqPlResult> result);

		List<OqPlResult> getOqPlResult(String serviceId, Long termId);

		List<OqPlResult> updateResult(List<OqPlResult> result);
	
}

