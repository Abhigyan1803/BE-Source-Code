package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.POC;

@Repository
public interface POCRepo extends JpaRepository<POC, Long> {

	List<POC> findAllByStatusOrderByIdDesc(int status);

	List<POC> findAllByOrderByIdDesc();

	List<POC> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<POC> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
