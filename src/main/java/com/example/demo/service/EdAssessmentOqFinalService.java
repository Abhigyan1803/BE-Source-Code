package com.example.demo.service;

import java.util.List;

import com.example.demo.model.EdAssessmentOqFinal;

public interface EdAssessmentOqFinalService {

	EdAssessmentOqFinal createEdAssessmentOqFinal(EdAssessmentOqFinal edAssessmentOqFinal);

	EdAssessmentOqFinal findByServiceIdAndTermId(String serviceId, Long termId);

	List<EdAssessmentOqFinal> findByServiceId(String serviceId);

	EdAssessmentOqFinal updateEdAssessmentOqFinal(EdAssessmentOqFinal edAssessmentOqFinal);

}
