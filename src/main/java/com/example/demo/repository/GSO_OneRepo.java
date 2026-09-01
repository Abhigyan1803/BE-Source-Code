package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GSO_OneTrg;

@Repository
public interface GSO_OneRepo extends JpaRepository<GSO_OneTrg, Long> {

	List<GSO_OneTrg> findAllByOrderByIdDesc();

}
