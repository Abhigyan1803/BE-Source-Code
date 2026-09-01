package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.RespDetails;

@Repository
public interface RespDetailsRepo extends JpaRepository<RespDetails,Long>{

}
