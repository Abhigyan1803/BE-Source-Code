package com.example.demo.service;

import com.example.demo.model.EDossierSSBReport;

public interface EDossierSSBReportService {

	EDossierSSBReport addAcademicSyllabus(EDossierSSBReport eDossierSSBReport);

	EDossierSSBReport getById(Long id);

	EDossierSSBReport getByServiceId(String serviceId);

	EDossierSSBReport updateEDossierSSBReport(EDossierSSBReport eDossierSSBReport);

}
