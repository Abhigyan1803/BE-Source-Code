package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TRG_EQTNResult;
import com.example.demo.model.TRG_EQTNSubjectResult;

@Repository
public interface TRG_EQTNSubjectResultRepo extends JpaRepository<TRG_EQTNSubjectResult, Long> {

	TRG_EQTNSubjectResult save(TRG_EQTNResult tRG_EQTNResult);

}
