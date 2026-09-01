package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ReportsIntelligenceSecurity;

@Repository
public interface ReportsIntelligenceSecurityRepo extends JpaRepository<ReportsIntelligenceSecurity, Long>{

	List<ReportsIntelligenceSecurity> findAllByStatusOrderByIdDesc(int status);

	List<ReportsIntelligenceSecurity> findAllByOrderByIdDesc();

}
