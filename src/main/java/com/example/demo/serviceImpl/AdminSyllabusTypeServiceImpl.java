package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SyllabusType;
import com.example.demo.repository.AdminSyllabusTypeRepo;
import com.example.demo.service.AdminSyllabusTypeService;

@Service
public class AdminSyllabusTypeServiceImpl implements AdminSyllabusTypeService {

	@Autowired
	AdminSyllabusTypeRepo syllabusTypeRepo;

	@Override
	public List<SyllabusType> getAllSyllabusTypeList() {
//		List<SyllabusType> list = syllabusTypeRepo.findAllByStatus(ConstantVar.ONE);
		List<SyllabusType> list = syllabusTypeRepo.findAllByOrderByIdDesc();

		return list;
	}

}
