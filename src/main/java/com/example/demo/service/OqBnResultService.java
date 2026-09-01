package com.example.demo.service;

import java.util.List;
import com.example.demo.model.OqBnResult;

public interface OqBnResultService {

	List<OqBnResult> addOqBnResult(List<OqBnResult> result);

	List<OqBnResult> getOqBnResult(String serviceId, Long termId);

	List<OqBnResult> updateResult(List<OqBnResult> result);
	
}
