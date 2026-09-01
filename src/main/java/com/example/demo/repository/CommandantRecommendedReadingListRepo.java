package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CommandantRecommendedReadingList;

@Repository
public interface CommandantRecommendedReadingListRepo extends JpaRepository<CommandantRecommendedReadingList, Long> {

	List<CommandantRecommendedReadingList> findByStatusOrderByIdDesc(Integer status);

	List<CommandantRecommendedReadingList> findAllByOrderByIdDesc();

	List<CommandantRecommendedReadingList> findByStatusAndStatusNotInOrderByIdDesc(Integer status,
			Integer[] deletedStatus);

	List<CommandantRecommendedReadingList> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

}
