package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MaritalStatus;

@Repository
public interface MaritalStatusRepo extends JpaRepository<MaritalStatus,Long>{

}
