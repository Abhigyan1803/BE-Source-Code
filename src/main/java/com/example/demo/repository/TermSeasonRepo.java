package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TermSeason;

@Repository
public interface TermSeasonRepo extends JpaRepository<TermSeason,Long>{
	

}
