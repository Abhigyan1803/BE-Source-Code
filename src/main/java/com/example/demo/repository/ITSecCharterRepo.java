package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ITSecCharter;

@Repository
public interface ITSecCharterRepo extends JpaRepository<ITSecCharter, Long> {

	List<ITSecCharter> findAllByStatusOrderByIdDesc(int status);

	List<ITSecCharter> findAllByOrderByIdDesc();

	List<ITSecCharter> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<ITSecCharter> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
