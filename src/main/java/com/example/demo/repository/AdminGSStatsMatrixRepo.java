package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GSStatsMatrix;

@Repository
public interface AdminGSStatsMatrixRepo extends JpaRepository<GSStatsMatrix, Integer> {

	List<GSStatsMatrix> findByStatusOrderByIdDesc(Integer status);

	List<GSStatsMatrix> findAllByOrderByIdDesc();

	List<GSStatsMatrix> findByStatusAndStatusNotInOrderByIdDesc(Integer status, Integer[] deletedStatus);

	List<GSStatsMatrix> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
