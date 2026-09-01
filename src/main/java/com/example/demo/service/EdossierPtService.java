package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.EdossierPtResult;
import com.example.demo.model.EdossierPtSubjectResult;
import com.example.demo.payload.EdossierPtResultFilterPayload;
import com.example.demo.payload.EdossierPtResultPayload;

public interface EdossierPtService {

	EdossierPtSubjectResult createSubResult(EdossierPtSubjectResult ptSubRslt);

	EdossierPtResult createEdossierPtResult(EdossierPtResult edossierPtResult);

	EdossierPtResult findByServiceIdAndTermIdAndSubjectType(String serviceId, Long termId, String subjectType);

	EdossierPtResult updateEdossierPtResult(EdossierPtResult edossierPtResult);

	EdossierPtResultPayload getCadetsByTermIdAndBattaionAndCompanyAndSubjectType(Long termId, String battalion,
			String company, String subjectType, String serviceId, Pageable pageable);

	EdossierPtResultPayload getCadetsBySearch(String serviceId, Long termId, String subjectType, Pageable pageable);

	String updateBulkEdossierPtResult(List<EdossierPtResultFilterPayload> edossierPtResultPayloadList);

	EdossierPtResultPayload getCadetsByTermIdAndBattaionAndCompanyAndSubjectTypeWithoutPagination(Long termId,
			String battalion, String company, String subjectType, String serviceId);

	EdossierPtResultPayload getCadetsBySearchWithoutPagination(String serviceId, Long termId, String subjectType);

	// List<EdossierPtResult> findByServiceId(String serviceId);

	List<EdossierPtResult> findByServiceIdAndSubjectType(String serviceId, String subjectType);

}
