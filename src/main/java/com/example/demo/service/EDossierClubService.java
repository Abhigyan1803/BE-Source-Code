package com.example.demo.service;

import java.util.List;

import com.example.demo.model.EDossierClub;

public interface EDossierClubService {

	EDossierClub getById(Long id);

	List<EDossierClub> getBystatus(Integer status);

	EDossierClub addEDossierClub(EDossierClub eDossierClub);

	EDossierClub updateEDossierClub(EDossierClub eDossierClub);

	EDossierClub getByServiceId(String serviceId);

	EDossierClub findByServiceIdAndTermId(String serviceId, Long termId);

}
