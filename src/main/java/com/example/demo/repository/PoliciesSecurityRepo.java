package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PoliciesSecurity;
import com.example.demo.model.SRESecurity;

@Repository
public interface PoliciesSecurityRepo  extends JpaRepository<PoliciesSecurity, Long>{

	List<PoliciesSecurity> findAllByStatusOrderByIdDesc(int status);

	List<PoliciesSecurity> findAllByOrderByIdDesc();

}
