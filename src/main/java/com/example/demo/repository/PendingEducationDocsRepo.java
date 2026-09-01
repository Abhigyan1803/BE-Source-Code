package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PendingEducationDocs;

@Repository
public interface PendingEducationDocsRepo extends JpaRepository<PendingEducationDocs, Long> {

	List<PendingEducationDocs> findAllByStatusOrderByIdDesc(int status);

	List<PendingEducationDocs> findAllByOrderByIdDesc();

	List<PendingEducationDocs> findAllByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<PendingEducationDocs> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
