package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GCBoard_Pcht_Ol_Achievements;

@Repository
public interface GCBoard_Pcht_Ol_AchievementsRepo extends JpaRepository<GCBoard_Pcht_Ol_Achievements, Long> {

	List<GCBoard_Pcht_Ol_Achievements> findAllByTypeAndStatusOrderByIdDesc(String type, int status);

	List<GCBoard_Pcht_Ol_Achievements> findAllByTypeOrderByIdDesc(String type);

	List<GCBoard_Pcht_Ol_Achievements> findAllByStatusOrderByIdDesc(int status);

	List<GCBoard_Pcht_Ol_Achievements> findAllByTypeAndSubTypeAndStatusOrderByIdDesc(String type, String subType,
			int status);

	List<GCBoard_Pcht_Ol_Achievements> findAllByTypeAndSubTypeOrderByIdDesc(String type, String subType);

	// List<GCBoard_Pcht_Ol_Achievements> findAllByStatusOrderByIdDesc(int status);
	//
	// List<GCBoard_Pcht_Ol_Achievements> findAllByOrderByIdDesc();

}
