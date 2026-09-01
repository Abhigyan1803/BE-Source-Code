package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.AcademicOqMatrixResult;
import com.example.demo.model.OqEqtnResult;
import com.example.demo.payload.OqEqtnFilterPayload;
import com.example.demo.payload.OqEqtnPayload;

public interface OqEqtnResultService {

	OqEqtnResult findByServiceIdAndTermIdAndTermType(String serviceId, Long termId,String termType);

	
	OqEqtnPayload getCadetsBySearch(Long termId, String termType, String serviceId, Pageable pageable);


	OqEqtnPayload getCadetsByTermIdAndTermTypeAndBattaionAndCompany(Long termId, String termType, String battalion,
			String company, String serviceId, Pageable pageable);

	OqEqtnResult createOqEqtnResult(OqEqtnResult oqEqtnResult);

	
	OqEqtnResult updateOqEqtnResult(OqEqtnResult oqEqtnResult);
	


	String updateBulkOqEqtnResult(List<OqEqtnFilterPayload> oqEqtnPayloadList);
}
