package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ITPPP;

@Repository
public interface ITPPPRepo extends JpaRepository<ITPPP, Long> {

	List<ITPPP> findAllByOrderByIdDesc();

	List<ITPPP> findAllByStatusOrderByIdDesc(int status);

	List<ITPPP> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<ITPPP> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
