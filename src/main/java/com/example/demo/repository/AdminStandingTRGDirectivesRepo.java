package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.StandingTRGDirectives;

@Repository
public interface AdminStandingTRGDirectivesRepo extends JpaRepository<StandingTRGDirectives, Integer> {

	List<StandingTRGDirectives> findByStatusOrderByIdDesc(Integer status);

	List<StandingTRGDirectives> findAllByOrderByIdDesc();

}
