package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.OqBnResult;

@Repository
public interface OqBnResultRepo extends JpaRepository<OqBnResult,Long>{

	Optional<OqBnResult> findByServiceIdAndTermIdAndOqSubjectAttributeId(String serviceId, Long termId, Integer id);

	List<OqBnResult> findByServiceIdAndTermId(String serviceId, Long termId);

}
