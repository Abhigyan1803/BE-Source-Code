package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PCABAndCOA;

@Repository
public interface AdminPCABAndCOARepo extends JpaRepository<PCABAndCOA, Integer> {

	List<PCABAndCOA> findByStatusOrderByIdDesc(Integer status);

	List<PCABAndCOA> findAllByOrderByIdDesc();

}
