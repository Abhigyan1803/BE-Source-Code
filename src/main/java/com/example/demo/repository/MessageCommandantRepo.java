package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MessageCommandant;

@Repository
public interface MessageCommandantRepo extends JpaRepository<MessageCommandant, Long> {

	List<MessageCommandant> findByStatus(int status);

	List<MessageCommandant> findAllByOrderByIdDesc();

	List<MessageCommandant> findByStatusOrderByIdDesc(int status);

	List<MessageCommandant> findByStatusAndStatusNotInOrderByIdDesc(int status, Integer[] deletedStatus);

}
