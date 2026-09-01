package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AcademicFiles;

public interface AcademicFilesRepo extends JpaRepository<AcademicFiles, Long>{

// AcademicFiles save(AcademicFiles details);

}
