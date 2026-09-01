package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SportsSubject;

@Repository
public interface SportsSubjectRepo extends JpaRepository<SportsSubject, Long> {

	List<SportsSubject> findByStatus(Integer status);

	List<SportsSubject> findByStatusAndTermSession(Integer status, String termSession);

}
