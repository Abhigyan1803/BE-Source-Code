package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ProfessionalDetails;

@Repository
public interface ProfessionalDetailsRepo extends JpaRepository<ProfessionalDetails, Long> {

}
