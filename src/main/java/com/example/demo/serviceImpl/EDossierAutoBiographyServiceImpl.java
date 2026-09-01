package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EDossierAutoBiography;
import com.example.demo.repository.EDossierAutoBiographyRepository;
import com.example.demo.service.EDossierAutoBiographyService;

@Service
public class EDossierAutoBiographyServiceImpl implements EDossierAutoBiographyService {
	@Autowired
	private EDossierAutoBiographyRepository repo;

	@Override
	public EDossierAutoBiography createAutoBiography(EDossierAutoBiography autoBiography) {
		autoBiography.setCreatedAt(new Date());
		return repo.save(autoBiography);
	}

	@Override
	public EDossierAutoBiography getAutoBiographyById(Long id) {
		Optional<EDossierAutoBiography> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public EDossierAutoBiography getAutoBiographyByServiceId(String serviceId) {
		Optional<EDossierAutoBiography> result = repo.findByServiceId(serviceId);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public EDossierAutoBiography updateAutoBiography(EDossierAutoBiography autoBiography) {

		if (autoBiography != null && autoBiography.getId() != null && autoBiography.getId() != 0) {
			Optional<EDossierAutoBiography> result = repo.findById(autoBiography.getId());
			if (result.isPresent()) {
				EDossierAutoBiography biography = result.get();
				if (autoBiography.getGeneral() != null) {
					biography.setGeneral(autoBiography.getGeneral());
				}
				if (autoBiography.getSpecialAchivement() != null) {
					biography.setSpecialAchivement(autoBiography.getSpecialAchivement());
				}
				if (autoBiography.getStrength() != null) {
					biography.setStrength(autoBiography.getStrength());
				}
				if (autoBiography.getWeakness() != null) {
					biography.setWeakness(autoBiography.getWeakness());
				}
				if (autoBiography.getDate() != null) {
					biography.setDate(autoBiography.getDate());
				}
				if (autoBiography.getIsDeclared() != null) {
					biography.setIsDeclared(autoBiography.getIsDeclared());
				}
				if (autoBiography.getStatus() != null) {
					biography.setStatus(autoBiography.getStatus());
				}

				autoBiography.setUpdatedAt(new Date());

				return repo.save(biography);

			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
