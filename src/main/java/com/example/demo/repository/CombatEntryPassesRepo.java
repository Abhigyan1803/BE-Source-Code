package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CombatEntryPasses;

@Repository
public interface CombatEntryPassesRepo extends JpaRepository<CombatEntryPasses, Long>{

	List<CombatEntryPasses> findAllByStatusOrderByIdDesc(int status);

	List<CombatEntryPasses> findAllByOrderByIdDesc();

}
