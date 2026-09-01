package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ScheduleOfCentralLecture;
@Repository
public interface ScheduleOfCentralLectureRepository extends JpaRepository<ScheduleOfCentralLecture, Long>{

	List<ScheduleOfCentralLecture> findAllByOrderByIdDesc();

}
