package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EDossierSSBReport;
import com.example.demo.repository.EDossierSSBReportRepository;
import com.example.demo.service.EDossierSSBReportService;

@Service
public class EDossierSSBReportServiceImpl implements EDossierSSBReportService {

	@Autowired
	private EDossierSSBReportRepository repo;

	@Override
	public EDossierSSBReport addAcademicSyllabus(EDossierSSBReport eDossierSSBReport) {
		// TODO Auto-generated method stub
		return repo.save(eDossierSSBReport);
	}

	@Override
	public EDossierSSBReport getById(Long id) {
		// TODO Auto-generated method stub
		Optional<EDossierSSBReport> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public EDossierSSBReport getByServiceId(String serviceId) {
		// TODO Auto-generated method stub
		Optional<EDossierSSBReport> result = repo.findByServiceId(serviceId);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public EDossierSSBReport updateEDossierSSBReport(EDossierSSBReport eDossierSSBReport) {
		// TODO Auto-generated method stub
		EDossierSSBReport report = null;
		if (eDossierSSBReport != null && eDossierSSBReport.getId() != null && eDossierSSBReport.getId() != 0) {

			Optional<EDossierSSBReport> ed = repo.findById(eDossierSSBReport.getId());
			if (ed.isPresent()) {

				report = ed.get();

				if (eDossierSSBReport.getPositiveTraits() != null) {

					report.setPositiveTraits(eDossierSSBReport.getPositiveTraits());
				}
				if (eDossierSSBReport.getNegativeTraits() != null) {

					report.setNegativeTraits(eDossierSSBReport.getNegativeTraits());
				}
				if (eDossierSSBReport.getAvgResult() != null) {

					report.setAvgResult(eDossierSSBReport.getAvgResult());
				}
				if (eDossierSSBReport.getLocation() != null) {

					report.setLocation(eDossierSSBReport.getLocation());
				}
				if (eDossierSSBReport.getDate() != null) {

					report.setDate(eDossierSSBReport.getDate());
				}
				if (eDossierSSBReport.getResult() != null) {

					report.setResult(eDossierSSBReport.getResult());
				}
				if (eDossierSSBReport.getAchivements() != null) {

					report.setAchivements(eDossierSSBReport.getAchivements());
				}
				if (eDossierSSBReport.getWeakness() != null) {

					report.setWeakness(eDossierSSBReport.getWeakness());
				}
				if (eDossierSSBReport.getStatus() != null) {

					report.setStatus(eDossierSSBReport.getStatus());
				}

				report.setUpdatedAt(new Date());

			}
			report = repo.save(report);
		}
		return report;

	}
}
