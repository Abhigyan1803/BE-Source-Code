package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OtherSecurityInfra;

@Repository
public interface OtherSecurityInfraRepo extends JpaRepository<OtherSecurityInfra, Long> {

	List<OtherSecurityInfra> findAllByStatusOrderByIdDesc(int status);

	List<OtherSecurityInfra> findAllByOrderByIdDesc();

	List<OtherSecurityInfra> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<OtherSecurityInfra> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
