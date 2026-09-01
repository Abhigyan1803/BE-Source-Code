package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.DRILLResult;
import com.example.demo.payload.DrillFilterPayload;
import com.example.demo.payload.DrillPayload;

public interface DRILLResultService {

	DRILLResult createDrillResult(DRILLResult dRILLResult);

	DRILLResult getDrillResult(String serviceId, Long termId);

	DRILLResult updateDrillResult(DRILLResult drillResult);

	List<DRILLResult> getAllDrillResult(String serviceId);

	DrillPayload getCadetsByTermIdAndBattaionAndCompanyAndEntryTypeId(Long termId, String battalion, String company,
			String serviceId, Pageable pageable);

	String updateDrillResult(List<DrillFilterPayload> drillPayloadList);

	DrillPayload getCadetsBySearch(String serviceId, Long termId, Pageable pageable);

}
