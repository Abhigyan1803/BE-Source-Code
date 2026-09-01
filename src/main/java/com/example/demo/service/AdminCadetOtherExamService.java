package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.CadetOtherExams;
import com.example.demo.myexception.MyException;

public interface AdminCadetOtherExamService {

	CadetOtherExams createExam(CadetOtherExams exam) throws MyException;

	List<CadetOtherExams> getAllExam();

	Optional<CadetOtherExams> getExamById(Long id);

	CadetOtherExams updateExam(CadetOtherExams exam);

}
