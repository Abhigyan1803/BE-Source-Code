package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SyllabusTerm;
import com.example.demo.repository.AdminSyllabusTermRepo;
import com.example.demo.service.AdminSyllabusTermService;

@Service
public class AdminSyllabusTermServiceImpl implements AdminSyllabusTermService {

	@Autowired
	AdminSyllabusTermRepo syllabusTermRepo;

	@Override
	public SyllabusTerm createSyllabus(SyllabusTerm syllbus) {
		return syllabusTermRepo.save(syllbus);
	}

	@Override
	public List<SyllabusTerm> getAllSyllabusList() {
//		List<SyllabusTerm> list = syllabusTermRepo.findAllByStatus(ConstantVar.ONE);
		List<SyllabusTerm> list = syllabusTermRepo.findAllByOrderByIdDesc();

		return list;
	}

	@Override
	public SyllabusTerm getSyllabusById(Integer id) {
		Optional<SyllabusTerm> list = syllabusTermRepo.findById(id);
		return list.get();
	}

	@Override
	public SyllabusTerm updateSyllabus(SyllabusTerm syllbus) {
		SyllabusTerm sylab = null;
		Optional<SyllabusTerm> syl = syllabusTermRepo.findById(syllbus.getId());
		if (syl.isPresent()) {

			sylab = syl.get();
			if (StringUtils.isNotBlank(syllbus.getDescription())) {
				sylab.setDescription(syllbus.getDescription());
			}
			if (StringUtils.isNotBlank(syllbus.getDoc())) {
				sylab.setDoc(syllbus.getDoc());
			}
			if (StringUtils.isNotBlank(syllbus.getTerm())) {

				sylab.setTerm(syllbus.getTerm());
			}

			if (StringUtils.isNotBlank(syllbus.getName())) {

				sylab.setName(syllbus.getName());
			}

			if (syllbus.getSyllabusType() != null) {

				sylab.setSyllabusType(syllbus.getSyllabusType());
			}

			if (syllbus.getStatus() != null) {

				sylab.setStatus(syllbus.getStatus());
			}
			if (syllbus.getDate() != null) {

				sylab.setDate(syllbus.getDate());
			}

			sylab.setCreated_at(new Date());

		}
		SyllabusTerm list = syllabusTermRepo.save(sylab);
		return list;
	}

}
