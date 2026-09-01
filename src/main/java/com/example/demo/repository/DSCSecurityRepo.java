package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DSCSecurity;

@Repository
public interface DSCSecurityRepo extends JpaRepository<DSCSecurity, Long> {

	List<DSCSecurity> findAllByStatusOrderByIdDesc(int status);

	List<DSCSecurity> findAllByOrderByIdDesc();

	List<DSCSecurity> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<DSCSecurity> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
