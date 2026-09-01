package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepo;
import com.example.demo.service.DepartmentsService;

@Service
public class DepartmentServiceImpl implements DepartmentsService{

	
	@Autowired
	DepartmentRepo departmentRepo;
	
	@Override
	public List<Department> getAllDepartments() {
		return departmentRepo.findAllByOrderByIdAsc();
	}

}
