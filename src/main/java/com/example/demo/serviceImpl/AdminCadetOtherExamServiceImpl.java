package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CadetOtherExams;
import com.example.demo.myexception.MyException;
import com.example.demo.repository.CadetExamRepo;
import com.example.demo.service.AdminCadetOtherExamService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ConstantVar;

@Service
public class AdminCadetOtherExamServiceImpl implements AdminCadetOtherExamService {

	@Autowired
	CadetExamRepo examRepo;

	@Override
	public CadetOtherExams createExam(CadetOtherExams exam) throws MyException {
		CadetOtherExams already_added = examRepo.findByExamName(exam.getExamName());
		if (null != already_added) {
			throw new MyException(ConstantMessage.EXAM_EXIST);
		}
		exam.setStatus(ConstantVar.ONE);
		CadetOtherExams saveExam = examRepo.save(exam);
		return saveExam;
	}

	@Override
	public List<CadetOtherExams> getAllExam() {
		List<CadetOtherExams> list = examRepo.findAll();
		return list;
	}

	@Override
	public Optional<CadetOtherExams> getExamById(Long id) {
		Optional<CadetOtherExams> list = examRepo.findById(id);
		return list;
	}

	@Override
	public CadetOtherExams updateExam(CadetOtherExams exam) {
		CadetOtherExams exm = null;
		Optional<CadetOtherExams> ex = examRepo.findById(exam.getId());
		if (ex.isPresent()) {
			exm = ex.get();

			if (exam.getExamName() != null) {
				exm.setExamName(exam.getExamName());
			}

			if (exam.getgPointITerm() != null) {
				exm.setgPointITerm(exam.getgPointITerm());
			}

			if (exam.getgPointIITerm() != null) {
				exm.setgPointIITerm(exam.getgPointIITerm());
			}

			if (exam.getgPointIIITerm() != null) {
				exm.setgPointIIITerm(exam.getgPointIIITerm());
			}
			if (exam.getStatus() != null) {
				exm.setStatus(exam.getStatus());
			}

		}

		return examRepo.save(exm);
	}

}
