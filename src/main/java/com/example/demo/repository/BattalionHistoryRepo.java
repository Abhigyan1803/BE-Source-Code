package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Battalion;
import com.example.demo.model.BattalionHistory;

@Repository
public interface BattalionHistoryRepo extends JpaRepository<BattalionHistory, Long>{

	Optional<BattalionHistory> findTopByBattalionTypeIdAndStatusOrderByCreatedAtDesc(Integer id, int i);


}
