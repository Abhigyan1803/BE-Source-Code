package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PodCastDetails;

@Repository
public interface PodCastRepo extends JpaRepository<PodCastDetails,Long> {

	List<PodCastDetails> findAllByOrderByIdDesc();

	
}
