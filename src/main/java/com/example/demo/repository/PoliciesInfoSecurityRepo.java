package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PoliciesInfoSecurity;

@Repository
public interface PoliciesInfoSecurityRepo extends JpaRepository<PoliciesInfoSecurity, Long> {

	List<PoliciesInfoSecurity> findAllByStatusOrderByIdDesc(int status);

	List<PoliciesInfoSecurity> findAllByOrderByIdDesc();

}
