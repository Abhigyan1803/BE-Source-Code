package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CampSubjectResult;

@Repository
public interface CampSubjectResultRepo extends JpaRepository<CampSubjectResult, Long> {

}
