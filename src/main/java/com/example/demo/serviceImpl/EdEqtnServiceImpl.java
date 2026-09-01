package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EdEqtn;
import com.example.demo.repository.EdEqtnRepository;
import com.example.demo.service.EdEqtnService;

@Service
public class EdEqtnServiceImpl implements EdEqtnService {

	@Autowired
	private EdEqtnRepository repo;

	@Override
	public EdEqtn addEdEqtn(EdEqtn edEqtn) {
		// TODO Auto-generated method stub
		return repo.save(edEqtn);

	}

	@Override
	public EdEqtn getByServiceId(String serviceId) {
		// TODO Auto-generated method stub
		return repo.findByServiceId(serviceId);
	}

	@Override
	public EdEqtn updateEdEqtn(EdEqtn edEqtn) {
		// TODO Auto-generated method stub
		return repo.save(edEqtn);
	}
}
