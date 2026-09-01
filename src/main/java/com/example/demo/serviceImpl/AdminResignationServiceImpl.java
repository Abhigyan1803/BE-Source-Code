package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Resignation;
import com.example.demo.repository.AdminResignationRepo;
import com.example.demo.service.AdminResignationService;

@Service
public class AdminResignationServiceImpl implements AdminResignationService {

	@Autowired
	AdminResignationRepo resignationRepo;

	@Override
	public Resignation createResignation(Resignation resignation) {
		return resignationRepo.save(resignation);
	}

	@Override
	public List<Resignation> getAllResignationList(Integer status) {
		Integer[] deletedStatus = { 2 };
		if (status < 2) {
			List<Resignation> list = resignationRepo.findByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
			return list;
		} else {
			List<Resignation> list = resignationRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			return list;
		}
	}

	@Override
	public Resignation getResignationById(Integer id) {
		Optional<Resignation> list = resignationRepo.findById(id);
		return list.get();
	}

	@Override
	public Resignation updateResignation(Resignation resignation) {
		Resignation resign = null;
		Optional<Resignation> res = resignationRepo.findById(resignation.getId());
		if (res.isPresent()) {

			resign = res.get();

			if (StringUtils.isNotBlank(resignation.getDoc())) {
				resign.setDoc(resignation.getDoc());
			}

			if (resignation.getName() != null) {

				resign.setName(resignation.getName());
			}

			if (resignation.getDescription() != null) {

				resign.setDescription(resignation.getDescription());
			}

			if (resignation.getStatus() != null) {

				resign.setStatus(resignation.getStatus());
			}

			resign.setUpdatedAt(new Date());

		}
		Resignation list = resignationRepo.save(resign);
		return list;
	}

}
