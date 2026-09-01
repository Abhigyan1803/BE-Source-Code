package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Battalion;
import com.example.demo.model.BattalionCompany;

@Repository
public interface BattalionCompanyRepo extends JpaRepository<BattalionCompany, Long> {

	List<BattalionCompany> findByBattalionType(Battalion type);

	BattalionCompany getCompanyByName(String name);

	List<BattalionCompany> findByBattalionTypeId(int id);

}
