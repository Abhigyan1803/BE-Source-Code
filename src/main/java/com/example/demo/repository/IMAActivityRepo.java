package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.IMAActivities;

@Repository
public interface IMAActivityRepo extends JpaRepository<IMAActivities, Long> {

	List<IMAActivities> findByStatus(int status);

//	List<IMAActivities> findByStatusOrderByDate(int status);
	
	List<IMAActivities> findByStatusOrderByCreatedDateDesc(int status);
	
	List<IMAActivities> findAllByOrderByIdDesc();


}
