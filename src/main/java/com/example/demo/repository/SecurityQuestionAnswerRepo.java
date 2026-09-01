package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ForgetPasswordAnswers;

@Repository
@Transactional
public interface SecurityQuestionAnswerRepo extends JpaRepository<ForgetPasswordAnswers, Long> {

	ForgetPasswordAnswers findByServiceIdAndQuestionIdAndAnswerLike(String serviceId, Long id, String answer);

}
