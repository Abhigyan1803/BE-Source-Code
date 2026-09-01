package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EdInterviewSheet;

@Repository
public interface EdInterviewSheetRepo extends JpaRepository<EdInterviewSheet, Long> {

	Optional<EdInterviewSheet> findByServiceId(String serviceId);

}
