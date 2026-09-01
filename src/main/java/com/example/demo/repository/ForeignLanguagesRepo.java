package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ForeignLanguages;

@Repository
public interface ForeignLanguagesRepo extends JpaRepository<ForeignLanguages, Long> {
	
	

}
