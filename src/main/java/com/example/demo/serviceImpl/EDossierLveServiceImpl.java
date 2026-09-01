package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EDossierLve;
import com.example.demo.repository.EDossierLveRepo;
import com.example.demo.service.EDossierLveService;

@Service
public class EDossierLveServiceImpl implements EDossierLveService {
	@Autowired
	private EDossierLveRepo repo;

	@Override
	public EDossierLve getById(Long id) {
		// TODO Auto-generated method stub
		Optional<EDossierLve> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<EDossierLve> getBystatus(Integer status) {
		// TODO Auto-generated method stub
		List<EDossierLve> result = repo.findBystatus(status);
		return result;
	}

	@Override
	public EDossierLve updateEDossierLve(EDossierLve eDossierLve) {
		if (eDossierLve.getId() != null && eDossierLve.getId() != 0) {
			return repo.save(eDossierLve);
		}
		return null;

	}

	@Override
	public EDossierLve addEDossierLve(EDossierLve eDossierLve) {
		// TODO Auto-generated method stub
		return repo.save(eDossierLve);
	}

	@Override
	public EDossierLve getByServiceId(String serviceId) {
		Optional<EDossierLve> result = repo.findByServiceId(serviceId);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public EDossierLve findByServiceIdAndTermId(String serviceId, Long termId) {
		return repo.findByServiceIdAndTermId(serviceId, termId);
	}

}