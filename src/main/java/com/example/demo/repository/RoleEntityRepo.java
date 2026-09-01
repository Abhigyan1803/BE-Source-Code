package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.RoleEntity;

@Repository
public interface RoleEntityRepo extends JpaRepository<RoleEntity, Long> {

	List<RoleEntity> findAllByStatus(int i);

}
