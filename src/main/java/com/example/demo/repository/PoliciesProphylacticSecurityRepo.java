package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PoliciesProphylacticSecurity;

@Repository
public interface PoliciesProphylacticSecurityRepo extends JpaRepository<PoliciesProphylacticSecurity, Long> {

	List<PoliciesProphylacticSecurity> findAllByStatusOrderByIdDesc(int status);

	List<PoliciesProphylacticSecurity> findAllByOrderByIdDesc();

}
