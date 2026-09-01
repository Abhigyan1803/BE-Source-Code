package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AviationList;

@Repository
public interface AviationListRepo extends JpaRepository<AviationList, Long> {

	List<AviationList> findAllByStatusOrderByIdDesc(int status);

	List<AviationList> findAllByOrderByIdDesc();

	List<AviationList> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<AviationList> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
