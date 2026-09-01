package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SopsDetails;

@Repository
public interface SopDetailsRepo extends JpaRepository<SopsDetails, Long> {

	Page<SopsDetails> findAllByOrderById(Pageable page);

	Page<SopsDetails> findAllByOrderByIdDesc(Pageable pagedData);

	List<SopsDetails> findAllByOrderByIdDesc();

}
