package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DelayDashboard;

@Repository
public interface DelayDashboardRepository extends JpaRepository<DelayDashboard, Long> {

	DelayDashboard findByUserIdAndModuleIdAndTermId(Long userId, Long moduleId, Long termId);

}
