package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.StandingTRGDirectives;
import com.example.demo.repository.AdminStandingTRGDirectivesRepo;
import com.example.demo.service.AdminStandingTRGDirectivesService;

@Service
public class AdminStandingTRGDirectivesServiceImpl implements AdminStandingTRGDirectivesService {

	@Autowired
	AdminStandingTRGDirectivesRepo directiveRepo;

	@Override
	public StandingTRGDirectives createTRGDirectives(StandingTRGDirectives trgDirective) {
		return directiveRepo.save(trgDirective);
	}

	@Override
	public List<StandingTRGDirectives> getAllTRGDirectivesList(Integer status) {
		if (status < 2) {
			List<StandingTRGDirectives> list = directiveRepo.findByStatusOrderByIdDesc(status);
			return list;
		}

		else {
			List<StandingTRGDirectives> list = directiveRepo.findAllByOrderByIdDesc();
			return list;
		}
	}

	@Override
	public StandingTRGDirectives getTRGDirectivesById(Integer id) {
		Optional<StandingTRGDirectives> list = directiveRepo.findById(id);
		return list.get();
	}

	@Override
	public StandingTRGDirectives updateTRGDirective(StandingTRGDirectives trgDirective) {
		StandingTRGDirectives trg_directive = null;
		Optional<StandingTRGDirectives> trg = directiveRepo.findById(trgDirective.getId());
		if (trg.isPresent()) {

			trg_directive = trg.get();

			if (StringUtils.isNotBlank(trgDirective.getDoc())) {
				trg_directive.setDoc(trgDirective.getDoc());
			}

			if (trgDirective.getName() != null) {

				trg_directive.setName(trgDirective.getName());
			}

			if (trgDirective.getDescription() != null) {

				trg_directive.setDescription(trgDirective.getDescription());
			}

			if (trgDirective.getStatus() != null) {

				trg_directive.setStatus(trgDirective.getStatus());
			}

			trg_directive.setUpdatedAt(new Date());

		}
		StandingTRGDirectives list = directiveRepo.save(trg_directive);
		return list;
	}
}
