package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ForecastTRGEvent;

@Repository
public interface AdminForecastTRGEventRepo extends JpaRepository<ForecastTRGEvent, Long> {

	List<ForecastTRGEvent> findAllByStatus(Integer one);

	List<ForecastTRGEvent> findAllByOrderByIdDesc();

	List<ForecastTRGEvent> findByStatusOrderByIdDesc(int status);

	List<ForecastTRGEvent> findByStatusAndIsGcLecOrderByIdDesc(int status, Boolean isGcLec);

	List<ForecastTRGEvent> findAllByIsGcLecOrderByIdDesc(Boolean isGcLec);

}
