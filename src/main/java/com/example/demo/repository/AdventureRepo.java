package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Adventure;

@Repository

public interface AdventureRepo extends JpaRepository<Adventure, Long> {

	List<Adventure> findBystatus(Integer status);

	Optional<Adventure> findByServiceId(String serviceId);

	Adventure findByServiceIdAndTermId(String serviceId, Long termId);

}
