package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EdSpecialInterview;

@Repository
public interface EdSpecialInterviewRepo extends JpaRepository<EdSpecialInterview, Long> {
	Optional<EdSpecialInterview> findByServiceId(String serviceId);

}
