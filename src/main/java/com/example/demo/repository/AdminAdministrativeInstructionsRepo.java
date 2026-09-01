package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AdministrativeInstructions;

@Repository
public interface AdminAdministrativeInstructionsRepo extends JpaRepository<AdministrativeInstructions, Integer> {

	List<AdministrativeInstructions> findByStatusOrderByIdDesc(Integer status);

	List<AdministrativeInstructions> findAllByOrderByIdDesc();

	List<AdministrativeInstructions> findByStatusAndStatusNotInOrderByIdDesc(Integer status, Integer[] deletedStatus);

	List<AdministrativeInstructions> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
