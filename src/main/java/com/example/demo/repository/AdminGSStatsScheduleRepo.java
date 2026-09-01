package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GSStatsSchedule;

@Repository
public interface AdminGSStatsScheduleRepo extends JpaRepository<GSStatsSchedule, Integer> {

	List<GSStatsSchedule> findByStatusOrderByIdDesc(Integer status);

	List<GSStatsSchedule> findAllByOrderByIdDesc();

	List<GSStatsSchedule> findByStatusAndStatusNotInOrderByIdDesc(Integer status, Integer[] deletedStatus);

	List<GSStatsSchedule> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
