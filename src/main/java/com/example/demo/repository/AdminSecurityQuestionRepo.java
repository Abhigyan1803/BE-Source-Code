package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SecurityQuestion;

@Repository
@Transactional
public interface AdminSecurityQuestionRepo extends JpaRepository<SecurityQuestion, Long> {

	List<SecurityQuestion> findAllByOrderByIdDesc();

}
