package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PCList;

@Repository
public interface PCListRepo extends JpaRepository<PCList, Long> {

	List<PCList> findAllByStatusOrderByIdDesc(int status);

	List<PCList> findAllByOrderByIdDesc();

	List<PCList> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<PCList> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
