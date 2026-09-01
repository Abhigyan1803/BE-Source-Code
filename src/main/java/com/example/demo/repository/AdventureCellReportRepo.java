package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AdventureCellReport;

@Repository
public interface AdventureCellReportRepo extends JpaRepository<AdventureCellReport, Long>{

	List<AdventureCellReport> findAllByOrderByIdDesc();


}
