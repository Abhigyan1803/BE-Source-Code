package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Relegation;
import com.example.demo.repository.AdminRelegationRepo;
import com.example.demo.service.AdminRelegationService;

@Service
public class AdminRelegationServiceImpl implements AdminRelegationService {

	@Autowired
	AdminRelegationRepo relegationRepo;

	@Override
	public Relegation createRelegation(Relegation relegation) {
		return relegationRepo.save(relegation);
	}

	@Override
	public List<Relegation> getAllRelegationList(Integer status) {
		Integer[] deletedStatus = { 2 };
		if (status < 2) {
			List<Relegation> list = relegationRepo.findByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
			return list;
		} else {
			List<Relegation> list = relegationRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			return list;
		}
	}

	@Override
	public Relegation getRelegationById(Integer id) {
		Optional<Relegation> list = relegationRepo.findById(id);
		return list.get();
	}

	@Override
	public Relegation updateRelegation(Relegation relegation) {
		Relegation rel = null;
		Optional<Relegation> r = relegationRepo.findById(relegation.getId());
		if (r.isPresent()) {

			rel = r.get();

			if (StringUtils.isNotBlank(relegation.getDoc())) {
				rel.setDoc(relegation.getDoc());
			}

			if (relegation.getName() != null) {

				rel.setName(relegation.getName());
			}

			if (relegation.getDescription() != null) {

				rel.setDescription(relegation.getDescription());
			}

			if (relegation.getStatus() != null) {

				rel.setStatus(relegation.getStatus());
			}

			rel.setUpdatedAt(new Date());

		}
		Relegation list = relegationRepo.save(rel);
		return list;
	}
}
