package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ExerciseType;

@Repository
public interface ExerciseTypeRepo extends JpaRepository<ExerciseType, Long> {

	List<ExerciseType> findByStatus(Integer status);

	List<ExerciseType> findAllByStatus(int i);

	ExerciseType findByType(String type);

}
