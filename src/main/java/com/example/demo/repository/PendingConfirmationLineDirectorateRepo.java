package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PendingConfirmationLineDirectorate;

@Repository
public interface PendingConfirmationLineDirectorateRepo
		extends JpaRepository<PendingConfirmationLineDirectorate, Long> {

	List<PendingConfirmationLineDirectorate> findAllByStatusOrderByIdDesc(int status);

	List<PendingConfirmationLineDirectorate> findAllByOrderByIdDesc();

	List<PendingConfirmationLineDirectorate> findAllByStatusAndStatusNotInOrderByIdDesc(int status,
			Integer[] deletedStatus);

	List<PendingConfirmationLineDirectorate> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
