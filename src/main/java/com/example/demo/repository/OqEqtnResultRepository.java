package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OqEqtnResult;

@Repository
public interface OqEqtnResultRepository extends JpaRepository<OqEqtnResult, Long> {

	Optional<OqEqtnResult> findByServiceIdAndTermIdAndTermType(String serviceId, Long termId, String termType);

	List<OqEqtnResult> findByServiceIdOrderByTermId(String serviceId);

	List<OqEqtnResult> findByServiceIdAndTermTypeOrderByTermId(String serviceId, String string);

}
