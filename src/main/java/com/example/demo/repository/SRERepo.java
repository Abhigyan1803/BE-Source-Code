package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SRESecurity;

@Repository
public interface SRERepo extends JpaRepository<SRESecurity, Long> {

	List<SRESecurity> findAllByStatusOrderByIdDesc(int status);

	List<SRESecurity> findAllByOrderByIdDesc();

	List<SRESecurity> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<SRESecurity> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
