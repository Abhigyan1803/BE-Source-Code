package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Logging;

@Repository
public interface LoggerRepo extends JpaRepository<Logging, Long> {

}
