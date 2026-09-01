package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BattalionAward;

@Repository
public interface BattalionAwardRepo extends JpaRepository<BattalionAward,Long> {

	Page<BattalionAward> findAllByOrderByIdDesc(Pageable pagedData);

	List<BattalionAward> findAllByOrderByIdDesc();

	List<BattalionAward> findByBattalionIdOrderByIdDesc(int battalionId);

	List<BattalionAward> findByBattalionIdAndStatusOrderByIdDesc(int battalionId, int status);

	List<BattalionAward> findAllByStatusOrderByIdDesc(int status);

	
	
}
