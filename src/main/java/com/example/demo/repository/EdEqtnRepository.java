package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EdEqtn;

@Repository
public interface EdEqtnRepository extends JpaRepository<EdEqtn, Long> {

	EdEqtn findByServiceId(String serviceId);

}
