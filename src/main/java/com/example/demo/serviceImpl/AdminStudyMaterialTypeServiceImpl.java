package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.model.StudyMaterialType;
import com.example.demo.repository.AdminStudyMaterialTypeRepo;
import com.example.demo.service.AdminStudyMaterialTypeService;
@Service
public class AdminStudyMaterialTypeServiceImpl implements AdminStudyMaterialTypeService{
	
	@Autowired
	AdminStudyMaterialTypeRepo studyMaterialTypeRepo;
	
	@Override
public List<StudyMaterialType> getAllStudyMaterialTypeList() {
	// TODO Auto-generated method stub
	List<StudyMaterialType> list = studyMaterialTypeRepo.findAllOrderByIdDesc();

	return list;
}
}
