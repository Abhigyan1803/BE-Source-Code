package com.example.demo.service;

import com.example.demo.model.EDossierAutoBiography;

public interface EDossierAutoBiographyService {

	EDossierAutoBiography createAutoBiography(EDossierAutoBiography autoBiography);

	EDossierAutoBiography getAutoBiographyById(Long id);

	EDossierAutoBiography getAutoBiographyByServiceId(String serviceId);

	EDossierAutoBiography updateAutoBiography(EDossierAutoBiography autoBiography);

}
