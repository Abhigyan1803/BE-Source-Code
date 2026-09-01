package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Course;
import com.example.demo.repository.CoursesRepo;
import com.example.demo.service.CoursesService;
@Service
public class CourseServiceImpl implements CoursesService{
	
	@Autowired
	CoursesRepo courseRepo;

	@Override
	public List<Course> getAll() {
		return courseRepo.findAll();
	}

}
