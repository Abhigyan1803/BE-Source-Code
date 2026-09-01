package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GCActivities;

@Repository
public interface GCActivitiesRepo extends JpaRepository<GCActivities, Integer> {

	List<GCActivities> findAllByOrderByIdDesc();

	List<GCActivities> findByStatusAndBattalianIdOrderByIdDesc(Integer status, Integer battalianId);

	List<GCActivities> findByBattalianIdOrderByIdDesc(Integer battalianId);

	List<GCActivities> findByStatusOrderByIdDesc(Integer status);

}
