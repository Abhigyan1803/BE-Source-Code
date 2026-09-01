package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Resignation;

@Repository
public interface AdminResignationRepo extends JpaRepository<Resignation, Integer> {

	List<Resignation> findByStatusOrderByIdDesc(Integer status);

	List<Resignation> findAllByOrderByIdDesc();

	List<Resignation> findByStatusAndStatusNotInOrderByIdDesc(Integer status, Integer[] deletedStatus);

	List<Resignation> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
