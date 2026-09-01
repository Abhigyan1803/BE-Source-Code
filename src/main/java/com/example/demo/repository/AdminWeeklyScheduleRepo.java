package com.example.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.WeeklySchedule;

@Repository
public interface AdminWeeklyScheduleRepo extends JpaRepository<WeeklySchedule, Integer> {

	List<WeeklySchedule> findAllByStatus(Integer one);

	List<WeeklySchedule> findAllByOrderByIdDesc();

	WeeklySchedule findByWeekId(Long id);

	// List<WeeklySchedule>
	// findByWeekIdOrTermIdOrSessionTermIdOrYearOrBattalianIdOrderByIdDesc(Long
	// weekId, Long termId,
	// Long termSeasonId, String year, Integer battalianId);

	List<WeeklySchedule> findByWeekIdAndTermIdAndSessionTermIdAndYearAndBattalianIdOrderByIdDesc(Long weekId,
			Long termId, Long termSeasonId, String year, Integer battalianId);

	List<WeeklySchedule> WeekIdAndTermIdAndSessionTermIdAndYearAndBattalianIdAndWeeklyScheduleDateDateOrderByIdDesc(
			Long weekId, Long termId, Long termSeasonId, String year, Integer battalianId, Date dt);

	Optional<WeeklySchedule> findByIdOrderByWeeklyScheduleDateDateDesc(Integer id);

	List<WeeklySchedule> TermIdAndBattalianIdAndWeeklyScheduleDateDate(Long termId, Integer battalianId, Date dt);

}
