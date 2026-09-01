package com.example.demo.service;

import java.util.List;

import com.example.demo.model.SyllabusTerm;
import com.example.demo.model.SyllabusType;

public interface AdminSyllabusTermService {

	SyllabusTerm createSyllabus(SyllabusTerm syllbus);

	List<SyllabusTerm> getAllSyllabusList();

	SyllabusTerm getSyllabusById(Integer id);

	SyllabusTerm updateSyllabus(SyllabusTerm syllbus);

}
