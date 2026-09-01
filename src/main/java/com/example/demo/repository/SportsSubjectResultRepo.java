package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SportsSubjectResult;

@Repository
public interface SportsSubjectResultRepo extends JpaRepository<SportsSubjectResult, Long> {

}
