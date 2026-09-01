package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ServiceBmt2Subject;

@Repository
public interface ServiceBmt2SubjectRepository extends JpaRepository<ServiceBmt2Subject, Long> {

	List<ServiceBmt2Subject> findByStatus(Integer status);

	List<ServiceBmt2Subject> findByStatusAndTermId(Integer status, Long termId);

}
