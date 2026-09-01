package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EDossierClub;
import com.example.demo.repository.EDossierClubRepo;
import com.example.demo.service.EDossierClubService;

@Service
public class EDossierClubServiceImpl implements EDossierClubService {
	@Autowired
	private EDossierClubRepo repo;

	@Override
	public EDossierClub addEDossierClub(EDossierClub eDossierClub) {
		// TODO Auto-generated method stub
		return repo.save(eDossierClub);

	}

	@Override
	public EDossierClub getById(Long id) {
		// TODO Auto-generated method stub
		Optional<EDossierClub> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public EDossierClub updateEDossierClub(EDossierClub eDossierClub) {
		if (eDossierClub.getId() != null && eDossierClub.getId() != 0) {
			return repo.save(eDossierClub);
		}
		return null;
	}

	@Override
	public List<EDossierClub> getBystatus(Integer status) {
		// TODO Auto-generated method stub
		List<EDossierClub> result = repo.findBystatus(status);
		return result;
	}

	@Override
	public EDossierClub getByServiceId(String serviceId) {
		Optional<EDossierClub> result = repo.findByServiceId(serviceId);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public EDossierClub findByServiceIdAndTermId(String serviceId, Long termId) {
		return repo.findByServiceIdAndTermId(serviceId, termId);
	}

}
