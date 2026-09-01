package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EntryType;

@Repository
public interface EntryTypeRepo extends JpaRepository<EntryType, Long> {

	List<EntryType> findByStatus(Integer status);

	List<EntryType> findAllByStatus(int i);

	EntryType findByType(String type);

}
