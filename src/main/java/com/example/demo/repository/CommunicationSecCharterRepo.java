package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CommunicationSecCharter;

@Repository
public interface CommunicationSecCharterRepo extends JpaRepository<CommunicationSecCharter, Long> {

	List<CommunicationSecCharter> findAllByStatusOrderByIdDesc(int status);

	List<CommunicationSecCharter> findAllByOrderByIdDesc();

	List<CommunicationSecCharter> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<CommunicationSecCharter> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
