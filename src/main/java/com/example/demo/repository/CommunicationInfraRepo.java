package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CommunicationInfra;

@Repository
public interface CommunicationInfraRepo extends JpaRepository<CommunicationInfra, Long> {

	List<CommunicationInfra> findAllByStatusOrderByIdDesc(int status);

	List<CommunicationInfra> findAllByOrderByIdDesc();

	List<CommunicationInfra> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<CommunicationInfra> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
