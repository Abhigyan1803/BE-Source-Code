package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CentralLibrary;

@Repository
public interface CentralLibraryRepo extends JpaRepository<CentralLibrary, Long> {

	List<CentralLibrary> findAllByOrderByIdDesc();

	List<CentralLibrary> findByStatusOrderByIdDesc(int status);

	List<CentralLibrary> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
