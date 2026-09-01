package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TRG_EQTNResult;

@Repository
public interface TRG_EQTNResultRepo extends JpaRepository<TRG_EQTNResult, Long> {

	Optional<TRG_EQTNResult> findByServiceIdAndTermId(String serviceId, Long termId);

	TRG_EQTNResult findByServiceId(String serviceId);

	List<TRG_EQTNResult> findByServiceIdOrderByTermId(String serviceId);

}
