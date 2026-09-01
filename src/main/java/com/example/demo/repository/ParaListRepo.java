package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ParaList;

@Repository
public interface ParaListRepo extends JpaRepository<ParaList, Long> {

	List<ParaList> findAllByStatusOrderByIdDesc(int status);

	List<ParaList> findAllByOrderByIdDesc();

	List<ParaList> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<ParaList> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
