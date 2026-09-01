package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.AcademicCreditForExcellenceResult;
import com.example.demo.payload.CreditExcellenceFilterPayload;
import com.example.demo.payload.CreditExcellencePayload;

public interface AcademicCreditForExcellenceResultService {

//	AcademicCreditForExcellenceResult createAcademicCreditForExcellenceResult(
//			AcademicCreditForExcellenceResult academicCreditForExcellenceResult);
//
//	AcademicCreditForExcellenceResult findByServiceIdAndTermId(String serviceId, int termId);
//
//	AcademicCreditForExcellenceSubjectResult createSubResult(
//			AcademicCreditForExcellenceSubjectResult creditForExcellenceSubjectResult);
//
//	AcademicCreditForExcellenceResult updateAcademicCreditForExcellenceResult(
//			AcademicCreditForExcellenceResult academicCreditForExcellenceMatrixResult);
//
//	AcademicCreditForExcellenceSubjectResult getSubResultById(Long id);

	AcademicCreditForExcellenceResult createAcademicCreditForExcellenceResult(
			AcademicCreditForExcellenceResult academicCreditForExcellenceResult);

	AcademicCreditForExcellenceResult findByServiceIdAndTermId(String serviceId, int termId);

	AcademicCreditForExcellenceResult updateAcademicCreditForExcellenceResult(
			AcademicCreditForExcellenceResult academicCreditForExcellenceResult);

	CreditExcellencePayload getCadetsByTermIdAndBattaionAndCompany(Long termId, String battalion, String company,
			String serviceId, Pageable pageable);

	String updateBulkAcademicCreditForExcellenceResult(List<CreditExcellenceFilterPayload> creditExcellencePayloadList);

	CreditExcellencePayload getCadetsBySearch(Long termId, String serviceId, Pageable pageable);

}
