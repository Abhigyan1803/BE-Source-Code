package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SyllabusType;

@Repository
public interface AdminSyllabusTypeRepo extends JpaRepository<SyllabusType, Integer> {

	List<SyllabusType> findAllByStatus(Integer one);

	List<SyllabusType> findAllByOrderByIdDesc();

}
