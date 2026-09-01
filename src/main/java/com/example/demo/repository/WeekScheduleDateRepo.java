package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.WeeklyScheduleDate;

@Repository
public interface WeekScheduleDateRepo extends JpaRepository<WeeklyScheduleDate, Long> {

//	List<WeeklyScheduleDate> findByDateOrderByIdDesc(Date dt);

	Optional<WeeklyScheduleDate> findById(Integer id);

//	List<WeeklyScheduleDate> WeekIdAndTermIdAndSessionTermIdAndYearAndBattalianIdAndWeeklyScheduleDateDateOrderByIdDesc(
//			Long weekId, Long termId, Long termSeasonId, String year, Integer battalianId, Date dt);

}
