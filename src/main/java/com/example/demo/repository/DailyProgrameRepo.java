package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CommandantDailyProgramme;

@Repository
public interface DailyProgrameRepo extends JpaRepository<CommandantDailyProgramme, Long> {

//	@Query(value="SELECT * FROM commandant_daily_programme WHERE scheduled_date >=?1 AND scheduled_date <=?2" , nativeQuery=true)
//	List<CommandantDailyProgramme> findByScheduledDate(Long date,Long nextDay);

	@Query(value = "SELECT * FROM commandant_daily_programme WHERE scheduled_date >=?1 AND scheduled_date <?2", nativeQuery = true)
	List<CommandantDailyProgramme> findTodaysProgram(long todaysDate, long nextDate);

	@Query(value = "SELECT * FROM commandant_daily_programme WHERE scheduled_date >=?1 ORDER BY id", nativeQuery = true)
	List<CommandantDailyProgramme> findTodaysProgramNew(long todaysDate);
	List<CommandantDailyProgramme> findAllByOrderByIdDesc();

}
