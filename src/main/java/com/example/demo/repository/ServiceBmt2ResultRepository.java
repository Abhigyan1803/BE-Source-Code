package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ServiceBmt2Result;

@Repository
public interface ServiceBmt2ResultRepository extends JpaRepository<ServiceBmt2Result, Long> {

	Optional<ServiceBmt2Result> findByServiceIdAndTermId(String serviceId, Long termId);

	List<ServiceBmt2Result> findByServiceIdOrderByTermId(String serviceId);

}
