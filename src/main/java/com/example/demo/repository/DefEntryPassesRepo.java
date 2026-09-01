package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DefEntryPasses;

@Repository
public interface DefEntryPassesRepo extends JpaRepository<DefEntryPasses, Long>{

	List<DefEntryPasses> findAllByStatusOrderByIdDesc(int status);

	List<DefEntryPasses> findAllByOrderByIdDesc();

}
