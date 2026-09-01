package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.NominalRole;


@Repository
public interface NominalRoleRepo extends JpaRepository<NominalRole, Long>{

	Page<NominalRole> findAllByOrderById(Pageable pagedData);

	Page<NominalRole> findAllByOrderByIdDesc(Pageable pagedData);

	List<NominalRole> findAllByOrderByIdDesc();

	
}
