package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Battalion;

@Repository
public interface AdminBattalionRepo extends JpaRepository<Battalion, Integer> {

	List<Battalion> findAllByStatus(Integer one);

	// Battalion findByName(String battalian);

	Battalion findByShortName(String battalian);

	List<Battalion> findAllByStatusAndStatusNotIn(Integer one, Integer[] deletedStatus);

}
