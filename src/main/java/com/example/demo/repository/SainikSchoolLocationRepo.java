package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SainikSchoolLocation;

@Repository
public interface SainikSchoolLocationRepo extends JpaRepository<SainikSchoolLocation, Long>{

}
