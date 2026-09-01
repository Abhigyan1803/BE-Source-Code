package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.CyberPolicyType;

@Repository
public interface CyberPolicyTypeRepo extends JpaRepository<CyberPolicyType,Long> {

	List<CyberPolicyType> findByStatusOrderByIdDesc(int status);

	List<CyberPolicyType> findAllByOrderByIdDesc();

}
