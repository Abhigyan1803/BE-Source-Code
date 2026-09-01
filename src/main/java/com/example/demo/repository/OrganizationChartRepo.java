package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OrganizationChart;

@Repository
public interface OrganizationChartRepo extends JpaRepository<OrganizationChart, Long> {

	OrganizationChart findByPositionId(Long reqPosition);

	List<OrganizationChart> findAllByStatus(int i);

	OrganizationChart findByPositionIdAndStatus(Long reqPosition, int i);

	List<OrganizationChart> findAllByOrderByIdDesc();

	List<OrganizationChart> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
