package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CavList;

@Repository
public interface CavListRepo extends JpaRepository<CavList, Long> {

	List<CavList> findAllByStatusOrderByIdDesc(int status);

	List<CavList> findAllByOrderByIdDesc();

	List<CavList> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<CavList> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
