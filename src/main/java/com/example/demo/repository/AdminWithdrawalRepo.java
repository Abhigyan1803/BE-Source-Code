package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Withdrawal;

@Repository
public interface AdminWithdrawalRepo extends JpaRepository<Withdrawal, Integer> {

	List<Withdrawal> findByStatusOrderByIdDesc(Integer status);

	List<Withdrawal> findAllByOrderByIdDesc();

}
