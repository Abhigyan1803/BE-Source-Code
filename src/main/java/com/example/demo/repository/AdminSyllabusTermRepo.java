package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SyllabusTerm;

@Repository
public interface AdminSyllabusTermRepo extends JpaRepository<SyllabusTerm, Integer> {

	List<SyllabusTerm> findAllByStatus(Integer one);

	List<SyllabusTerm> findAllByOrderByIdDesc();

}
