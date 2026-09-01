package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CadetCampBnCdrResult;

public interface CadetCampBnMarksService {

	List<CadetCampBnCdrResult> addCampBnResult(List<CadetCampBnCdrResult> result);

	List<CadetCampBnCdrResult> getCadetCampBnResult(String serviceId, Long termId);

	List<CadetCampBnCdrResult> updateResultBn(List<CadetCampBnCdrResult> result);

}
