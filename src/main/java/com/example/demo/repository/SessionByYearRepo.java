package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SessionByYear;

@Repository
public interface SessionByYearRepo  extends JpaRepository<SessionByYear, Long>{

	SessionByYear findBySessionYearAndTermSeasonIdAndStatus(String year, Long seasonYear, int i);

	List<SessionByYear> findAllByStatusOrderByIdDesc(int status);

	

}
