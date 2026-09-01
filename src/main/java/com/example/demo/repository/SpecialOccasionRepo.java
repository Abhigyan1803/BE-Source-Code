package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SpecialOccasion;

@Repository
public interface SpecialOccasionRepo extends JpaRepository<SpecialOccasion, Long> {

	List<SpecialOccasion> findByStatusOrderByIdDesc(Integer status);

	List<SpecialOccasion> findAllByOrderByIdDesc();

	// @Query(value = "SELECT * FROM `special_occasion` WHERE
	// (DATE_FORMAT(`officerdob`,'%m-%d') BETWEEN :startDate AND :endDate) OR
	// (DATE_FORMAT(`spousedob`,'%m-%d') BETWEEN :startDate AND :endDate) OR
	// (DATE_FORMAT(`marriage_anniversary`,'%m-%d') BETWEEN :startDate AND :endDate
	// And status = 1)", nativeQuery = true)
	@Query(value = "SELECT * FROM `special_occasion` WHERE ((DATE_FORMAT(`officerdob`,'%m-%d') BETWEEN :startDate AND :endDate) OR (DATE_FORMAT(`spousedob`,'%m-%d') BETWEEN :startDate AND :endDate) OR (DATE_FORMAT(`marriage_anniversary`,'%m-%d') BETWEEN :startDate AND :endDate)) and status=1", nativeQuery = true)
	List<SpecialOccasion> getDataBetweenDate(@Param("startDate") String date1, @Param("endDate") String date2);

	List<SpecialOccasion> findByStatusAndStatusNotInOrderByIdDesc(Integer status, Integer[] deletedStatus);

	List<SpecialOccasion> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

	SpecialOccasion findByIcNumber(Integer icNumber);

}
