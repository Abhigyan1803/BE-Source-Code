package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GeneralInstruction;

@Repository
public interface GeneralInstructionRepo extends JpaRepository<GeneralInstruction, Long> {

	List<GeneralInstruction> findByStatus(int status);

	List<GeneralInstruction> findAllByOrderByIdDesc();

}
