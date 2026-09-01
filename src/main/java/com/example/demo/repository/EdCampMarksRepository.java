package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EdCampMarks;

@Repository
public interface EdCampMarksRepository extends JpaRepository<EdCampMarks, Long> {

	List<EdCampMarks> findByServiceId(String serviceId);

	EdCampMarks findByServiceIdAndTermId(String serviceId, Long termId);

}
