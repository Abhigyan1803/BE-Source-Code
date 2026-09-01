package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GSO2ServiceSubjectBMTResult;

@Repository
public interface GSO2ServiceSubjectBMTResultRepo extends JpaRepository<GSO2ServiceSubjectBMTResult, Long> {

	GSO2ServiceSubjectBMTResult findByTermIdAndSubjectTypeAndAssesmentTermTypeAndStatusAndServiceIdOrderByIdDesc(
			Long termId, String subjectType, String assesmentTermType, Integer status, String serviceId);

	List<GSO2ServiceSubjectBMTResult> findByServiceIdOrderByIdDesc(String serviceId);

	List<GSO2ServiceSubjectBMTResult> findByServiceIdAndSubjectTypeAndAssesmentTermTypeOrderByTermId(String serviceId,
			String serviceSubjectType, String assesmentTermType);

	List<GSO2ServiceSubjectBMTResult> findByServiceIdAndSubjectTypeOrderByTermId(String serviceId,
			String serviceSubjectType);

	List<GSO2ServiceSubjectBMTResult> findByServiceIdAndSubjectTypeAndTermId(String serviceId,
			String serviceSubjectType, Long termId);

}
