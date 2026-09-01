package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CadetCampPlCdrResult;
import com.example.demo.model.CadetWeaponTrainingResult;

public interface CadetPlCdrCampResultService {

	List<CadetCampPlCdrResult> addCampPlResult(List<CadetCampPlCdrResult> result);

	List<CadetCampPlCdrResult> getCadetResult(String serviceId, Long termId);

	List<CadetCampPlCdrResult> updateResult(List<CadetCampPlCdrResult> result);

}
