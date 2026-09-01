package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Officer;

@Repository
@Transactional
public interface AdminRecordOfServiceRepo
		extends JpaRepository<Officer, Integer>, PagingAndSortingRepository<Officer, Integer> {

	Optional<Officer> findById(Long id);

	List<Officer> findAllByStatus(Integer status);

	Officer findByPersonalNumber(String personalNumber);

	List<Officer> findAllByStatusOrderByIdDesc(Integer status);

	List<Officer> findAllByOrderByIdDesc();

	List<Officer> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
