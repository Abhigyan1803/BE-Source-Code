package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SessionWeek;

@Repository
public interface SessionWeekRepo extends JpaRepository<SessionWeek, Long> {

	SessionWeek findBySessionYearIdAndWeek(Long id, String week);

	List<SessionWeek> findBySessionYearId(Long termSeason);

}
