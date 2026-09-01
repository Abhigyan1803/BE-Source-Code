package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SchoolList;

@Repository
@Transactional
public interface AdminSchoolRepo extends JpaRepository<SchoolList, Integer> {

}
