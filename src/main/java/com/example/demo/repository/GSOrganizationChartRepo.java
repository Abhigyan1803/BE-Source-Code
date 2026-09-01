package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GSOrganizationChart;

@Repository
public interface GSOrganizationChartRepo extends JpaRepository<GSOrganizationChart, Long>{

	GSOrganizationChart findByGsPositionIdAndStatus(Long position, int i);

	List<GSOrganizationChart> findByStatusOrderByIdDesc(int status);

	List<GSOrganizationChart> findAllByOrderByIdDesc();

	GSOrganizationChart findByICNum(String icNum);

}
