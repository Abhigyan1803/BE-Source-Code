package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DateSheet;

@Repository
public interface DateSheetRepository extends JpaRepository<DateSheet, Long> {

	// List<DateSheet> findAllByOrderByIdDesc();

	List<DateSheet> findAllByTermIdOrderByIdDesc(Long termId);

}
