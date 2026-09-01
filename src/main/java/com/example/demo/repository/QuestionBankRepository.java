package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.QuestionBank;
@Repository
public interface QuestionBankRepository extends JpaRepository<QuestionBank, Long>{

}
