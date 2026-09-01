package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CourtCase;
import com.example.demo.repository.AdminCourtCaseRepo;
import com.example.demo.service.AdminCourtCaseService;

@Service
public class AdminCourtCaseServiceImpl implements AdminCourtCaseService {

	@Autowired
	AdminCourtCaseRepo caseRepo;

	@Override
	public CourtCase createCourtCase(CourtCase courtCase) {
		return caseRepo.save(courtCase);
	}

	@Override
	public List<CourtCase> getAllCourtCaseList(Integer status) {
		Integer[] deletedStatus = { 2 };
		if (status < 2) {
			List<CourtCase> list = caseRepo.findByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
			return list;
		} else {
			List<CourtCase> list = caseRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			return list;
		}
	}

	@Override
	public CourtCase getCourtCaseById(Integer id) {
		Optional<CourtCase> list = caseRepo.findById(id);
		return list.get();
	}

	@Override
	public CourtCase updateCourtCase(CourtCase courtCase) {
		CourtCase ccase = null;
		Optional<CourtCase> c = caseRepo.findById(courtCase.getId());
		if (c.isPresent()) {

			ccase = c.get();

			if (StringUtils.isNotBlank(courtCase.getDoc())) {
				ccase.setDoc(courtCase.getDoc());
			}

			if (courtCase.getName() != null) {

				ccase.setName(courtCase.getName());
			}

			if (courtCase.getDescription() != null) {

				ccase.setDescription(courtCase.getDescription());
			}

			if (courtCase.getStatus() != null) {

				ccase.setStatus(courtCase.getStatus());
			}

			ccase.setUpdatedAt(new Date());

		}
		CourtCase list = caseRepo.save(ccase);
		return list;
	}
}
