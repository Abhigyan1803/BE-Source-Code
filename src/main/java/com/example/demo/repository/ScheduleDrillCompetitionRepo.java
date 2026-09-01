package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ScheduleDrillCompetition;

@Repository
public interface ScheduleDrillCompetitionRepo extends JpaRepository<ScheduleDrillCompetition, Long>{

	List<ScheduleDrillCompetition> findAllByStatusOrderByIdDesc(int status);

	List<ScheduleDrillCompetition> findAllByOrderByIdDesc();

	List<ScheduleDrillCompetition> findByTypeAndStatusOrderByIdDesc(String type, int status);

	List<ScheduleDrillCompetition> findByTypeOrderByIdDesc(String type);

}
