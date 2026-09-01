package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PoliciesIntelligenceSecurity;

@Repository
public interface PoliciesIntelligenceSecurityRepo extends JpaRepository<PoliciesIntelligenceSecurity, Long> {

	List<PoliciesIntelligenceSecurity> findAllByStatusOrderByIdDesc(int status);

	List<PoliciesIntelligenceSecurity> findAllByOrderByIdDesc();

}
