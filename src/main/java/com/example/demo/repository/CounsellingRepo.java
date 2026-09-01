package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Counselling;

@Repository
public interface CounsellingRepo extends JpaRepository<Counselling, Long>{


	List<Counselling> findByStatusAndServiceId(Integer status, String serviceId);

	List<Counselling> findAllById(Long id);





}
