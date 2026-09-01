package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.FGCPolicy;

@Repository
public interface AdminFGCPolicyRepo extends JpaRepository<FGCPolicy, Integer> {

	List<FGCPolicy> findByStatusOrderByIdDesc(Integer status);

	List<FGCPolicy> findAllByOrderByIdDesc();

	List<FGCPolicy> findByStatusAndStatusNotInOrderByIdDesc(Integer status, Integer[] deletedStatus);

	List<FGCPolicy> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
