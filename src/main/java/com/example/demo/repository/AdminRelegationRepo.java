package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Relegation;

@Repository
public interface AdminRelegationRepo extends JpaRepository<Relegation, Integer> {

	List<Relegation> findByStatusOrderByIdDesc(Integer status);

	List<Relegation> findAllByOrderByIdDesc();

	List<Relegation> findByStatusAndStatusNotInOrderByIdDesc(Integer status, Integer[] deletedStatus);

	List<Relegation> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
