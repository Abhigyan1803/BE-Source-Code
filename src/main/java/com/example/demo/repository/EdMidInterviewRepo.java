package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EdMidInterview;

@Repository
public interface EdMidInterviewRepo extends JpaRepository<EdMidInterview, Long> {

	Optional<EdMidInterview> findByServiceIdAndSubmittedBy(String serviceId, String submittedBy);

	List<EdMidInterview> findByServiceIdOrderById(String serviceId);

}
