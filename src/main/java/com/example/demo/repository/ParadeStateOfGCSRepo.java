package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ParadeStateOfGCS;

@Repository
public interface ParadeStateOfGCSRepo extends JpaRepository<ParadeStateOfGCS, Long> {

	Page<ParadeStateOfGCS> findAllByOrderByIdDesc(Pageable pagedData);

	List<ParadeStateOfGCS> findAllByOrderByIdDesc();

	List<ParadeStateOfGCS> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
