package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.GSO2ServiceSubjectBMTResult;
import com.example.demo.payload.BmtFilterPayload;
import com.example.demo.payload.BmtPayload;
import com.example.demo.payload.EdserviceSubPayload;
import com.example.demo.payload.ServiceSubTermPayload;

public interface GSO2ServiceSubjectBMTResultService {

	GSO2ServiceSubjectBMTResult createGSO2ServiceSubjectBMTResult(
			GSO2ServiceSubjectBMTResult gSO2ServiceSubjectBMTResult);

	GSO2ServiceSubjectBMTResult getByid(Long id);

	GSO2ServiceSubjectBMTResult getGSO2ServiceSubjectBMTResult(Long termId, String subjectType,
			String assesmentTermType, Integer status, String serviceId);

	GSO2ServiceSubjectBMTResult updateGSO2ServiceSubjectBMTResult(
			GSO2ServiceSubjectBMTResult gSO2ServiceSubjectBMTResult);

	List<GSO2ServiceSubjectBMTResult> getGSO2ServiceSubjectBMTResult(String serviceId);

	BmtPayload getCadetsByTermIdAndBattaionAndCompany(Long termId, String battalion, String serviceSubjectType,
			String assesmentTermType, String company, String serviceId, Pageable pageable);

	String updateBulkGSO2ServiceSubjectBMTResult(List<BmtFilterPayload> bmtPayloadList);

	BmtPayload getCadetsBySearch(Long termId, String serviceId, String serviceSubjectType, String assesmentTermType,
			Pageable pageable);

	ServiceSubTermPayload findBmt1Bmt2Mrprac(String serviceId, String resultType, String serviceSubjectType,
			String assesmentTermType);

	String updateBmt1Bmt2Mrprac(EdserviceSubPayload edServiceSubPayload);

	ServiceSubTermPayload findBmt1Bmt2MrpracNew1(String serviceId, String resultType, String serviceSubjectType);

}
