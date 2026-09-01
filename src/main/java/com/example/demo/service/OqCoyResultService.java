package com.example.demo.service;

import java.util.List;

import com.example.demo.model.OqCoyResult;

public interface OqCoyResultService {

	List<OqCoyResult> addOqCoyResult(List<OqCoyResult> result);

	List<OqCoyResult> getOqCoyResult(String serviceId, Long termId);

	List<OqCoyResult> updateResult(List<OqCoyResult> result);

	
}
