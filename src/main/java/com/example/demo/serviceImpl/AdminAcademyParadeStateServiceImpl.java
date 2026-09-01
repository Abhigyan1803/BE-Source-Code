package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AcademyParadeState;
import com.example.demo.repository.AdminAcademyParadeStateRepo;
import com.example.demo.service.AdminAcademyParadeStateService;

@Service
public class AdminAcademyParadeStateServiceImpl implements AdminAcademyParadeStateService {

	@Autowired
	AdminAcademyParadeStateRepo paradeStateRepo;

	@Override
	public AcademyParadeState createParadeState(AcademyParadeState paradeState) {
		return paradeStateRepo.save(paradeState);
	}

	@Override
	public List<AcademyParadeState> getAllParadeStateList(Integer status) {
		if (status < 2) {
			List<AcademyParadeState> list = paradeStateRepo.findByStatusOrderByIdDesc(status);
			return list;
		}

		else {
			List<AcademyParadeState> list = paradeStateRepo.findAllByOrderByIdDesc();
			return list;
		}
	}

	@Override
	public AcademyParadeState getParadeStateById(Integer id) {
		Optional<AcademyParadeState> list = paradeStateRepo.findById(id);
		return list.get();
	}

	@Override
	public AcademyParadeState updateParadeState(AcademyParadeState paradeState) {
		AcademyParadeState parade = null;
		Optional<AcademyParadeState> par = paradeStateRepo.findById(paradeState.getId());
		if (par.isPresent()) {

			parade = par.get();

			if (StringUtils.isNotBlank(paradeState.getDoc())) {
				parade.setDoc(paradeState.getDoc());
			}

			if (paradeState.getName() != null) {

				parade.setName(paradeState.getName());
			}

			if (paradeState.getDescription() != null) {

				parade.setDescription(paradeState.getDescription());
			}

			if (paradeState.getStatus() != null) {

				parade.setStatus(paradeState.getStatus());
			}

			parade.setUpdatedAt(new Date());

		}
		AcademyParadeState list = paradeStateRepo.save(parade);
		return list;
	}

}
