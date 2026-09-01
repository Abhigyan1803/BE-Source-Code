package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ServiceBmt2SubjectResult;
@Repository
public interface ServiceBmt2SubjectResultRepository extends JpaRepository<ServiceBmt2SubjectResult, Long>{

}
