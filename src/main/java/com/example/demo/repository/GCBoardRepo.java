package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GCBoard;

@Repository
public interface GCBoardRepo extends JpaRepository<GCBoard, Long> {

	List<GCBoard> findAllByStatusOrderByIdDesc(int status);

	List<GCBoard> findAllByOrderByIdDesc();

	List<GCBoard> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<GCBoard> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
