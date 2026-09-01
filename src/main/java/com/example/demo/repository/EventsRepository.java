package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UpcomingEvents;

@Repository
public interface EventsRepository extends JpaRepository<UpcomingEvents, Long> {

	List<UpcomingEvents> findByStatus(int status);

//	@Query(value = "SELECT * FROM upcoming_events WHERE event_date >=?1 and status=1 and is_gc_event=false", nativeQuery = true)
	@Query(value = "SELECT * FROM upcoming_events WHERE event_date >=?1 and status=1 ", nativeQuery = true)
	List<UpcomingEvents> findUpcomingEventsByIsGcEvent(Long date);

	List<UpcomingEvents> findAllByIsGcEventOrderByIdDesc(Boolean isGcEvent);

	@Query(value = "SELECT * FROM upcoming_events WHERE event_date >=?1 AND event_date <?2", nativeQuery = true)
	List<UpcomingEvents> findProgramBetweenDates(long todaysDate, long nextDate);

	List<UpcomingEvents> findAllByOrderByIdDesc();

	@Query(value = "SELECT * FROM upcoming_events WHERE event_date >=?1 and status=1 and is_gc_event=?2", nativeQuery = true)
	List<UpcomingEvents> findAllByIsGcEventOrderByIdDesc(long time, Boolean isGcEvent);

	List<UpcomingEvents> findByStatusAndStatusNotIn(int status, Integer[] deletedStatus);

}
