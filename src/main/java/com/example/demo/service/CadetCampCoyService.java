package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CadetCampCoyCdrResult;

public interface CadetCampCoyService {

	List<CadetCampCoyCdrResult> updateResult(List<CadetCampCoyCdrResult> result);

	List<CadetCampCoyCdrResult> addCampCoyResult(List<CadetCampCoyCdrResult> result);

	List<CadetCampCoyCdrResult> getCadetCampCoyResult(String serviceId, Long termId);

}
