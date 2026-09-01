package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Exercises;

@Repository
public interface ExercisesRepo extends JpaRepository<Exercises, Long> {

	Page<Exercises> findAllByOrderByIdDesc(Pageable pagedData);

	List<Exercises> findAllByOrderByIdDesc();

	List<Exercises> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
