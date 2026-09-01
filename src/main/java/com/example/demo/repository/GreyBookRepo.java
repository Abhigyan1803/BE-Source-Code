package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GreyBook;

@Repository
public interface GreyBookRepo extends JpaRepository<GreyBook, Long> {
	List<GreyBook> findAllByOrderByIdDesc();

	GreyBook findByEmail(String email);

	GreyBook findByPhoneNumber(String phone);

	List<GreyBook> findByStatusOrderByIdDesc(int status);

	List<GreyBook> findByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

	List<GreyBook> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
