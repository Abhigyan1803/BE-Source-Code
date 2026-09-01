package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Adventure;
import com.example.demo.repository.AdventureRepo;
import com.example.demo.service.AdventureService;

@Service
public class AdventureServiceImpl implements AdventureService {
	@Autowired
	private AdventureRepo repo;

	@Override
	public Adventure addAdventure(Adventure adventure) {
		// TODO Auto-generated method stub
		return repo.save(adventure);
	}

	@Override
	public Adventure getById(Long id) {
		// TODO Auto-generated method stub
		Optional<Adventure> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<Adventure> getBystatus(Integer status) {
		// TODO Auto-generated method stub
		List<Adventure> result = repo.findBystatus(status);
		return result;
	}

	@Override
	public Adventure updateAdventure(Adventure adventure) {
		if (adventure.getId() != null && adventure.getId() != 0) {
			return repo.save(adventure);
		}
		return null;
	}

	@Override
	public Adventure getByServiceId(String serviceId) {
		Optional<Adventure> result = repo.findByServiceId(serviceId);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public Adventure findByServiceIdAndTermId(String serviceId, Long termId) {
		return repo.findByServiceIdAndTermId(serviceId, termId);
	}

}
