package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.OqDrillResult;
import com.example.demo.model.OqEqtnResult;
import com.example.demo.payload.OqDrillFilterPayload;
import com.example.demo.payload.OqDrillPayload;

public interface OqDrillResultService {
	
	OqDrillResult findByServiceIdAndTermIdAndTermType(String serviceId, Long termId,String termType);


	OqDrillPayload getCadetsBySearch(Long termId, String termType, String serviceId, Pageable pageable);


	OqDrillPayload getCadetsByTermIdAndTermTypeAndBattaionAndCompany(Long termId, String termType, String battalion,
			String company, String serviceId, Pageable pageable);
	
	OqDrillResult createOqDrillResult(OqDrillResult oqDrillResult);

	OqDrillResult updateOqDrillResult(OqDrillResult oqDrillResult);

	String updateBulkOqdrillResult(List<OqDrillFilterPayload> oqDrillPayloadList);

}
