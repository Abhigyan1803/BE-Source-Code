package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PendingCVRCases;

@Repository
public interface PendingCVRCasesRepo extends JpaRepository<PendingCVRCases, Long> {

	List<PendingCVRCases> findAllByStatusOrderByIdDesc(int status);

	List<PendingCVRCases> findAllByOrderByIdDesc();

	List<PendingCVRCases> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<PendingCVRCases> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
