package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DemoCoys;

@Repository
public interface DemoCoysRepo extends JpaRepository<DemoCoys, Long> {

	List<DemoCoys> findAllByStatusOrderByIdDesc(int status);

	List<DemoCoys> findAllByOrderByIdDesc();

	List<DemoCoys> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<DemoCoys> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
