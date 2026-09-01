package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GSPosition;

@Repository
public interface GSPositionRepo extends JpaRepository<GSPosition, Long> {

	List<GSPosition> findAllByStatus(int i);

}
