package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Syllabus;
import com.example.demo.repository.AdminSyllabusRepo;
import com.example.demo.service.AdminSyllabusService;

@Service
public class AdminSyllabusServiceImpl implements AdminSyllabusService {

	@Autowired
	AdminSyllabusRepo syllabusRepo;

	@Override
	public Syllabus createSyllabus(Syllabus syllbus) {
		return syllabusRepo.save(syllbus);
	}

	@Override
	public List<Syllabus> getAllSyllabusList(String type, Integer status, Long termId) {
		// List<Syllabus> list = syllabusRepo.findAllByStatus(ConstantVar.ONE);
		// List<Syllabus> list =
		// syllabusRepo.findAllByStatusAndSyllabusType(ConstantVar.ONE, type);
		List<Syllabus> list = null;
		if (status != null && status == 1) {
			if (termId != null) {
				list = syllabusRepo.findAllByStatusAndSyllabusTypeAndTermIdOrderByIdDesc(status, type, termId);
			} else {
				list = syllabusRepo.findAllByStatusAndSyllabusTypeOrderByIdDesc(status, type);
			}

		} else {
			if (termId != null) {
				list = syllabusRepo.findAllBySyllabusTypeAndTermIdOrderByIdDesc(type, termId);
			} else {
				list = syllabusRepo.findAllBySyllabusTypeOrderByIdDesc(type);
			}

		}

		return list;
	}

	@Override
	public Syllabus getSyllabusById(Integer id) {
		Optional<Syllabus> list = syllabusRepo.findById(id);
		return list.get();
	}

	@Override
	public Syllabus updateSyllabus(Syllabus syllbus) {
		Syllabus sylab = null;
		Optional<Syllabus> syl = syllabusRepo.findById(syllbus.getId());
		if (syl.isPresent()) {

			sylab = syl.get();

			if (StringUtils.isNotBlank(syllbus.getDoc())) {
				sylab.setDoc(syllbus.getDoc());
			}

			if (syllbus.getName() != null) {

				sylab.setName(syllbus.getName());
			}
			if (syllbus.getSyllabusType() != null) {

				sylab.setSyllabusType(syllbus.getSyllabusType());
			}
			if (syllbus.getDate() != null) {

				sylab.setDate(syllbus.getDate());
			}

			if (syllbus.getDescription() != null) {

				sylab.setDescription(syllbus.getDescription());
			}

			if (syllbus.getStatus() != null) {

				sylab.setStatus(syllbus.getStatus());
			}

			if (syllbus.getTermId() != null) {

				sylab.setTermId(syllbus.getTermId());
			}

			sylab.setUpdatedAt(new Date());

		}
		Syllabus list = syllabusRepo.save(sylab);
		return list;
	}

}
