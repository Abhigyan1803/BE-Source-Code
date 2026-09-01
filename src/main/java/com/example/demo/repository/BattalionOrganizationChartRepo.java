package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BattalionOrganizationChart;

@Repository
public interface BattalionOrganizationChartRepo extends JpaRepository<BattalionOrganizationChart, Long> {

	List<BattalionOrganizationChart> findAllByOrderByIdDesc();

	BattalionOrganizationChart findByBattalionPostIdAndBattalionTypeIdAndStatus(Long id, Integer id2, int i);

	BattalionOrganizationChart findByBattalionPostIdAndCompanyIdAndStatus(Long id, Long companyId, int i);

	List<BattalionOrganizationChart> findByBattalionTypeIdOrderByIdDesc(int battalionId);

	List<BattalionOrganizationChart> findByBattalionTypeIdAndStatusOrderByIdDesc(int battalionId, int status);

	List<BattalionOrganizationChart> findAllByStatusOrderByIdDesc(int status);

}
