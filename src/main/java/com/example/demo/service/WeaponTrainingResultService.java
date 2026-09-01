package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.CadetWeaponTrainingMainResult;
import com.example.demo.model.CadetWeaponTrainingResult;
import com.example.demo.model.SpotTestWtt;
import com.example.demo.model.WeaponTrainingResult;
import com.example.demo.payload.WeaponTrainingResultFilterPayload;
import com.example.demo.payload.WeaponTrainingResultPayload;

public interface WeaponTrainingResultService {

	List<CadetWeaponTrainingResult> createResult(List<CadetWeaponTrainingResult> result);

	List<CadetWeaponTrainingResult> getCadetResult(String serviceId, Long termId);

	List<CadetWeaponTrainingResult> updateResult(List<CadetWeaponTrainingResult> result);

	List<CadetWeaponTrainingMainResult> createMainResult(List<CadetWeaponTrainingMainResult> mainResult);

	List<CadetWeaponTrainingMainResult> getCadetMainResult(String serviceId, Long termId);

	CadetWeaponTrainingMainResult updateMainResult(CadetWeaponTrainingMainResult result);

	WeaponTrainingResult createCadetWTResult(WeaponTrainingResult result);

	SpotTestWtt addSpotTestAndWttMarks(SpotTestWtt spotTestWtt);

	List<SpotTestWtt> getAllSpotTestAndWttMarksByStatus(Integer status);

	SpotTestWtt getByTermId(Long termId);

	SpotTestWtt updateSpotTestAndWttMarks(SpotTestWtt spotTestWtt);

	WeaponTrainingResult getCadetWeaponMainResult(String serviceId, Long termId);

	WeaponTrainingResult updateCadetWeaponResult(WeaponTrainingResult weaponTrainingResult);

	List<WeaponTrainingResult> getCadetWeaponMainResultByServiceId(String serviceId);

	WeaponTrainingResultPayload getCadetsByTermIdAndBattaionAndCompany(Long termId, String battalion, String company,
			String serviceId, Pageable pageable);

	WeaponTrainingResultPayload getCadetsBySearch(String serviceId, Long termId, Pageable pageable);

	String updateBulkWeaponTrainingResult(List<WeaponTrainingResultFilterPayload> weaponTrainingResultPayloadList);

}
