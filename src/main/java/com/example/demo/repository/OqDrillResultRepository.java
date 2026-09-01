package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OqDrillResult;

@Repository
public interface OqDrillResultRepository extends JpaRepository<OqDrillResult, Long> {

	Optional<OqDrillResult> findByServiceIdAndTermIdAndTermType(String serviceId, Long termId, String termType);

	List<OqDrillResult> findByServiceIdOrderByTermId(String serviceId);

	List<OqDrillResult> findByServiceIdAndTermTypeOrderByTermId(String serviceId, String string);

}
