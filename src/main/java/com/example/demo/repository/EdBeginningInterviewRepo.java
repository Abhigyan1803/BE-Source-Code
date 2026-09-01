package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EdBeginningInterview;

@Repository
public interface EdBeginningInterviewRepo extends JpaRepository<EdBeginningInterview, Long> {

	Optional<EdBeginningInterview> findByServiceIdAndSubmittedBy(String serviceId, String submittedBy);

	List<EdBeginningInterview> findByServiceIdOrderById(String serviceId);

}
