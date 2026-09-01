package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.RecordOfDetention;
import com.example.demo.repository.RecordOfDetentionRepo;
import com.example.demo.service.RecordOfDetentionService;

@Service
public class RecordOfDetentionServiceImpl implements RecordOfDetentionService {
	@Autowired
	private RecordOfDetentionRepo repo;

	@Override
	public RecordOfDetention addRecordOfDetention(RecordOfDetention recordOfDetention) {
		// TODO Auto-generated method stub
		return repo.save(recordOfDetention);
	}

	@Override
	public RecordOfDetention getById(Long id) {
		// TODO Auto-generated method stub
		Optional<RecordOfDetention> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<RecordOfDetention> getBystatus(Integer status) {
		// TODO Auto-generated method stub
		List<RecordOfDetention> result = repo.findBystatus(status);
		return result;
	}

	@Override
	public RecordOfDetention updateRecordOfDetention(RecordOfDetention recordOfDetention) {
		if (recordOfDetention.getId() != null && recordOfDetention.getId() != 0) {
			return repo.save(recordOfDetention);
		}
		return null;
	}

	@Override
	public RecordOfDetention getByServiceId(String serviceId) {
		Optional<RecordOfDetention> result = repo.findByServiceId(serviceId);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public RecordOfDetention findByServiceIdAndTermId(String serviceId, Long termId) {
		return repo.findByServiceIdAndTermId(serviceId, termId);
	}

}
