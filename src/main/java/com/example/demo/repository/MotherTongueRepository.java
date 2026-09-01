package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MotherTongue;

@Repository
public interface MotherTongueRepository extends JpaRepository<MotherTongue, Long> {

}
