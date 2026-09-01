package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EdInitialInterview;

@Repository
public interface EdInitialInterviewRepo extends JpaRepository<EdInitialInterview, Long> {

	List<EdInitialInterview> findByServiceId(String serviceId);

	Optional<EdInitialInterview> findByServiceIdAndSubmittedBy(String serviceId, String submittedBy);

}
