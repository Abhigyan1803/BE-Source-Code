package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.TRG_EQTNResult;
import com.example.demo.payload.EqtnFilterPayload;
import com.example.demo.payload.EqtnPayload;

public interface TRG_EQTNResultService {

	// TRG_EQTNSubjectResult createTRG_EQTNResult(List<TRG_EQTNSubjectResult>
	// tRG_EQTNSubjectResult);

	// TRG_EQTNResult findByServiceIdAndTermId(String serviceId, int termId);

	TRG_EQTNResult createTRG_EQTNResult(TRG_EQTNResult tRG_EQTNResult);

	TRG_EQTNResult findByServiceIdAndTermId(String serviceId, Long termId);

	TRG_EQTNResult updateTRG_EQTNResult(TRG_EQTNResult tRG_EQTNResult);

	List<TRG_EQTNResult> findByServiceId(String serviceId);

	EqtnPayload getCadetsByTermIdAndBattaionAndCompany(Long termId, String battalion, String company, String serviceId,
			Pageable pageable);

	String updateBulkTRG_EQTNResult(List<EqtnFilterPayload> eqtnPayloadList);

	EqtnPayload getCadetsBySearch(Long termId, String serviceId, Pageable pageable);

	// TRG_EQTNSubjectResult updateTRG_EQTNResult(TRG_EQTNResult tRG_EQTNResult);

	// TRG_EQTNSubjectResult updateTRG_EQTNResult(TRG_EQTNResultService
	// tRG_EQTNResultService);

	// TRG_EQTNResult updateTRG_EQTNResult(TRG_EQTNResult tRG_EQTNResult);

//	static boolean isPresent() {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
