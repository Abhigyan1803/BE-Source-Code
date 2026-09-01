package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PerformanceHighlights;

@Repository
public interface PerformanceHighlightsRepo extends JpaRepository<PerformanceHighlights, Integer> {

	List<PerformanceHighlights> findAllByOrderByIdDesc();

	List<PerformanceHighlights> findByStatusAndBattalianIdOrderByIdDesc(Integer status, Integer battalianId);

	List<PerformanceHighlights> findByBattalianIdOrderByIdDesc(Integer battalianId);

	List<PerformanceHighlights> findByStatusOrderByIdDesc(Integer status);

}
