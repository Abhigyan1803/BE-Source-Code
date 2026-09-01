package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SchoolList;
import com.example.demo.repository.AdminSchoolRepo;
import com.example.demo.service.AdminSchoolService;

@Service
public class AdminSchoolServiceImpl implements AdminSchoolService {

	@Autowired
	AdminSchoolRepo schoolRepo;

	@Override
	public SchoolList createSchool(SchoolList school) {
		// TODO Auto-generated method stub
		return null;
	}

}
