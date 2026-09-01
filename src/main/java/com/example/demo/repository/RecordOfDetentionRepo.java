package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.RecordOfDetention;

@Repository
public interface RecordOfDetentionRepo extends JpaRepository<RecordOfDetention, Long> {

	List<RecordOfDetention> findBystatus(Integer status);

	Optional<RecordOfDetention> findByServiceId(String serviceId);

	RecordOfDetention findByServiceIdAndTermId(String serviceId, Long termId);

}
