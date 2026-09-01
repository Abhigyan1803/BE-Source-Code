package com.example.demo.service;

import java.util.List;

import com.example.demo.model.EDossierLve;

public interface EDossierLveService {

	EDossierLve getById(Long id);

	List<EDossierLve> getBystatus(Integer status);

	EDossierLve updateEDossierLve(EDossierLve eDossierLve);

	EDossierLve addEDossierLve(EDossierLve eDossierLve);

	EDossierLve getByServiceId(String serviceId);

	EDossierLve findByServiceIdAndTermId(String serviceId, Long termId);

}
