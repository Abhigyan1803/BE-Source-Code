package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ACSFP;

@Repository
public interface ACSFPRepo extends JpaRepository<ACSFP, Long> {

	List<ACSFP> findAllByStatusOrderByIdDesc(int status);

	List<ACSFP> findAllByOrderByIdDesc();

	List<ACSFP> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<ACSFP> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
