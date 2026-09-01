package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Course;

@Repository
public interface CoursesRepo extends JpaRepository<Course, Long> {

}
