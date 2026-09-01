package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.RPSecurity;

@Repository
public interface RPSecurityRepo extends JpaRepository<RPSecurity, Long> {

	List<RPSecurity> findAllByStatusOrderByIdDesc(int status);

	List<RPSecurity> findAllByOrderByIdDesc();

	List<RPSecurity> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<RPSecurity> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
