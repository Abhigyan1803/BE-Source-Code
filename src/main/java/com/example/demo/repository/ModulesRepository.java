package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ModulesEntity;

@Repository
@Transactional
public interface ModulesRepository extends JpaRepository<ModulesEntity, Long> {

	List<ModulesEntity> findAllByIdOrderByIdDesc(Long id);

}
