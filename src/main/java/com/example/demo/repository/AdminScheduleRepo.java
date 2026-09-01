package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Schedule;

@Repository
public interface AdminScheduleRepo
		extends JpaRepository<Schedule, Integer>, PagingAndSortingRepository<Schedule, Integer> {

//	Page<Schedule> findAllByStatus(Integer one, Pageable paging);

	// Page<Schedule> findAllByStatusOrderByIdDesc(Integer one, Pageable
	// paginationData);

//	Page<Schedule> findAllByOrderByIdDesc(Pageable paginationData);
//
//	List<Schedule> findAllByOrderByIdDesc();
//
//	List<Schedule> findByWeeklyScheduleDateId(Integer id);

}
