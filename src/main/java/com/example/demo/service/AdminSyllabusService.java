package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Syllabus;

public interface AdminSyllabusService {

	Syllabus createSyllabus(Syllabus syllbus);

	List<Syllabus> getAllSyllabusList(String type, Integer status, Long termId);

	Syllabus getSyllabusById(Integer id);

	Syllabus updateSyllabus(Syllabus syllbus);

}
