package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Week;

@Repository
public interface AdminWeekRepo extends JpaRepository<Week, Long> {

	List<Week> findAllByStatus(Integer one);

	List<Week> findAllByOrderByIdDesc();

	List<Week> findAllByOrderByIdAsc();

}
