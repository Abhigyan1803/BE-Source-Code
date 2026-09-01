package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CourtCase;

@Repository
public interface AdminCourtCaseRepo extends JpaRepository<CourtCase, Integer> {

	List<CourtCase> findByStatusOrderByIdDesc(Integer status);

	List<CourtCase> findAllByOrderByIdDesc();

	List<CourtCase> findByStatusAndStatusNotInOrderByIdDesc(Integer status, Integer[] deletedStatus);

	List<CourtCase> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
